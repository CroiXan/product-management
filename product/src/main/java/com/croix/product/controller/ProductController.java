package com.croix.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.croix.product.exception.ResourceNotFoundException;
import com.croix.product.model.Products;
import com.croix.product.service.ProductService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/management/product")
@Validated
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Products>> getAllProducts() {
        List<Products> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
    
    @PostMapping
    public ResponseEntity<Products> createProduct(@Valid @RequestBody Products product) {
        Products newProduct = productService.saveProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Products> getProduct(@PathVariable Long id) {
        Products product = productService.getProductById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Producto con ID "+ id +" no se encuentra"));
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.getProductById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Producto con ID "+ id +" no se encuentra"));
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Products> updateProduct(@PathVariable Long id, @Valid @RequestBody Products updatedProduct ) {
        Products product = productService.getProductById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Producto con ID "+ id +" no se encuentra"));
        product.setCategory(updatedProduct.getCategory());
        product.setDescription(updatedProduct.getDescription());
        product.setDiscount(updatedProduct.getDiscount());
        product.setName(updatedProduct.getName());
        product.setPrice(updatedProduct.getPrice());
        product.setSku(updatedProduct.getSku());
        product.setStock(updatedProduct.getStock());
        Products resultProduct = productService.saveProduct(product);
        return ResponseEntity.ok(resultProduct);
    }
    
}
