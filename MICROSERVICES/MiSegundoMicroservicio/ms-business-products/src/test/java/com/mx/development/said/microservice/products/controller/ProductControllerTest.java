package com.mx.development.said.microservice.products.controller;

import com.mx.development.said.microservice.products.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.mx.development.said.microservice.products.dto.RequestProduct;
import com.mx.development.said.microservice.products.dto.ResponseProduct;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productController = new ProductController(productService);
    }

    @Test
    void updateProduct() {
        // Arrange
        Long productId = 1L;
        RequestProduct requestProduct = new RequestProduct();
        ResponseProduct responseProduct = new ResponseProduct();

        when(productService.updateProduct(productId, requestProduct)).thenReturn(responseProduct);

        // Act
        ResponseEntity<ResponseProduct> response = productController.updateProduct(productId, requestProduct);

        // Assert
        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(responseProduct, response.getBody());
        verify(productService, times(1)).updateProduct(productId, requestProduct);
    }

    @Test
    void updateProduct_throwsException() {
        // Arrange
        Long productId = 1L;
        RequestProduct requestProduct = new RequestProduct();

        when(productService.updateProduct(productId, requestProduct)).thenThrow(new RuntimeException("Failed to update product"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> productController.updateProduct(productId, requestProduct));
        verify(productService, times(1)).updateProduct(productId, requestProduct);
    }

    @Test
    void getAllProducts() {
        // Arrange
        List<ResponseProduct> products = List.of(new ResponseProduct(), new ResponseProduct());
        when(productService.findAll()).thenReturn(products);

        // Act
        ResponseEntity<List<ResponseProduct>> response = productController.getAllProducts();

        // Assert
        assertNotNull(response);
        assertEquals(products, response.getBody());
        verify(productService, times(1)).findAll();
    }

    @Test
    void createProduct() {
        // Arrange
        RequestProduct requestProduct = new RequestProduct();
        ResponseProduct responseProduct = new ResponseProduct();
        when(productService.createProduct(requestProduct)).thenReturn(responseProduct);

        // Act
        ResponseEntity<ResponseProduct> response = productController.createProduct(requestProduct);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseProduct, response.getBody());
        verify(productService, times(1)).createProduct(requestProduct);
    }

    @Test
    void getProductById() {
        // Arrange
        Long productId = 1L;
        ResponseProduct responseProduct = new ResponseProduct();
        when(productService.getProductById(productId)).thenReturn(responseProduct);

        // Act
        ResponseProduct result = productController.getProductById(productId);

        // Assert
        assertNotNull(result);
        assertEquals(responseProduct, result);
        verify(productService, times(1)).getProductById(productId);
    }

    @Test
    void getProductByStatus() {
        // Arrange
        String status = "active";
        List<ResponseProduct> products = List.of(new ResponseProduct(), new ResponseProduct());
        when(productService.findByStatus(status)).thenReturn(products);

        // Act
        ResponseEntity<List<ResponseProduct>> response = productController.getProductByStatus(status);

        // Assert
        assertNotNull(response);
        assertEquals(products, response.getBody());
        verify(productService, times(1)).findByStatus(status);
    }

    @Test
    void deleteProduct() {
        // Arrange
        Long productId = 1L;

        // Act
        ResponseEntity<Void> response = productController.deleteProduct(productId);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(productService, times(1)).deleteProduct(productId);
    }
}