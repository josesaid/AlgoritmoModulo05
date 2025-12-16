package com.mx.development.said.microservice.products.service;

import com.mx.development.said.microservice.products.dto.RequestProduct;
import com.mx.development.said.microservice.products.dto.ResponseProduct;
import com.mx.development.said.microservice.products.entity.ProductEntity;
import com.mx.development.said.microservice.products.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    void createProduct_ShouldReturnCreatedProductResponse() {
        // Given
        RequestProduct requestProduct = new RequestProduct();
        requestProduct.setName("Test Product");
        requestProduct.setDescription("Test Description");
        requestProduct.setPrice("100.50");

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1L);
        productEntity.setName("Test Product");
        productEntity.setDescription("Test Description");
        productEntity.setPrice("100.50");

        Mockito.when(productRepository.save(any(ProductEntity.class))).thenReturn(productEntity);

        // When
        ResponseProduct responseProduct = productService.createProduct(requestProduct);

        // Then
        assertThat(responseProduct).isNotNull();
        assertThat(responseProduct.getName()).isEqualTo("Test Product");
        assertThat(responseProduct.getDescription()).isEqualTo("Test Description");
        assertThat(responseProduct.getPrice()).isEqualTo("100.50");

        Mockito.verify(productRepository, Mockito.times(4)).save(any(ProductEntity.class));
    }
}