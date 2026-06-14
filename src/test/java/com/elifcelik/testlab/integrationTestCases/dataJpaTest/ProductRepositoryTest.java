package com.elifcelik.testlab.integrationTestCases.dataJpaTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product(null, "tel", 25);
    }

    @Test
    void save_shouldAssignId() {
        Product saved = productRepository.save(product);
        assertNotNull(saved.getId());
        assertEquals("tel", saved.getName());
        assertEquals(25, saved.getPrice());
    }

    @Test
    void findById_whenProductExists_shouldReturnProduct() {
        Product saved = productRepository.save(product);
        Optional<Product> found = productRepository.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals("tel", found.get().getName());
        assertEquals(25, found.get().getPrice());
    }
    @Test
    void findById_whenProductNotExists_shouldReturnEmpty() {
        Optional<Product> found = productRepository.findById(99L);
        assertTrue(found.isEmpty());
    }
}
