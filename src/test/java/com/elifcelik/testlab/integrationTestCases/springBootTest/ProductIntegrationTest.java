package com.elifcelik.testlab.integrationTestCases.springBootTest;

import com.elifcelik.testlab.integrationTestCases.dataJpaTest.Product;
import com.elifcelik.testlab.integrationTestCases.dataJpaTest.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Product product;

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
        product = productRepository.save(new Product(null, "tel", 30000));
    }

    @Test
    void getProduct_whenExists_shouldReturnProduct() throws Exception {
        mockMvc.perform(get("/products/" + product.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("tel"))
                .andExpect(jsonPath("$.price").value(30000));
    }

    @Test
    void saveProduct_shouldReturnSavedProduct() throws Exception {
        Product newProduct = new Product(null, "pc", 50000);
        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newProduct)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("pc"))
                .andExpect(jsonPath("$.price").value(50000));
    }
}
