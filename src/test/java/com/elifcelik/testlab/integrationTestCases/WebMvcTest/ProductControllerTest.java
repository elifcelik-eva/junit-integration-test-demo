package com.elifcelik.testlab.integrationTestCases.WebMvcTest;

import com.elifcelik.testlab.integrationTestCases.dataJpaTest.Product;
import com.elifcelik.testlab.integrationTestCases.dataJpaTest.ProductController;
import com.elifcelik.testlab.integrationTestCases.dataJpaTest.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    Product product;

    @BeforeEach
    void setUp() {
        product = new Product(1L, "tel", 30000);
    }

    @Test
    void getProduct_whenExists_shouldReturnProduct() throws Exception {
        when(productService.getProductId(1L)).thenReturn(product);
        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("tel"))
                .andExpect(jsonPath("$.price").value(30000));

    }
    @Test
    void saveProduct_shouldReturnSavedProduct() throws Exception {
        when(productService.saveProduct(any(Product.class))).thenReturn(product);
        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("tel"))
                .andExpect(jsonPath("$.price").value(30000));

    }
}
