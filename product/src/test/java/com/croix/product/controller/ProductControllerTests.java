package com.croix.product.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.croix.product.exception.ResourceNotFoundException;
import com.croix.product.model.Products;
import com.croix.product.service.ProductService;

public class ProductControllerTests {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducts() {
        List<Products> products = Arrays.asList(
            new Products.Builder()
                .setDiscount(0).build(), 
            new Products.Builder()
                .setDiscount(0).build()
        );
        when(productService.getAllProducts()).thenReturn(products);

        ResponseEntity<List<Products>> response = productController.getAllProducts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
    }

    @Test
    void testCreateProduct() {
        Products product = new Products.Builder().setDiscount(0).build();
        Products savedProduct = new Products.Builder().setDiscount(0).build();
        when(productService.saveProduct(product)).thenReturn(savedProduct);

        ResponseEntity<Products> response = productController.createProduct(product);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedProduct, response.getBody());
    }

    @Test
    void testGetProductById() {
        Long productId = 1L;
        Products product = new Products.Builder().setDiscount(0).build();
        when(productService.getProductById(productId)).thenReturn(Optional.of(product));

        ResponseEntity<Products> response = productController.getProduct(productId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    void testGetProductByIdNotFound() {
        Long productId = 1L;
        when(productService.getProductById(productId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productController.getProduct(productId));
    }

    @Test
    void testDeleteProduct() {
        Long productId = 1L;
        Products product = new Products.Builder().setDiscount(0).build();
        when(productService.getProductById(productId)).thenReturn(Optional.of(product));

        ResponseEntity<Void> response = productController.deleteProduct(productId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(productService, times(1)).deleteProductById(productId);
    }

    @Test
    void testUpdateProduct() {
        Long productId = 1L;
        Products existingProduct = new Products.Builder().setDiscount(0).setId_product(productId).build();
        Products updatedProduct = new Products.Builder().setDiscount(0).setId_product(productId).setName("Updated").build();
        when(productService.getProductById(productId)).thenReturn(Optional.of(existingProduct));
        when(productService.saveProduct(any(Products.class))).thenReturn(updatedProduct);

        ResponseEntity<Products> response = productController.updateProduct(productId, updatedProduct);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedProduct, response.getBody());
    }

    @Test
    void testUpdateProductNotFound() {
        Long productId = 1L;
        Products updatedProduct = new Products.Builder().setDiscount(0).setId_product(productId).build();
        when(productService.getProductById(productId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productController.updateProduct(productId, updatedProduct));
    }
}
