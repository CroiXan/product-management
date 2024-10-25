package com.croix.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.croix.product.repository.ProductRepository;
import com.croix.product.model.Products;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Products> getAllProducts(){
        return productRepository.findAll();
    }

    public Optional<Products> getProductById(Long id){
        return productRepository.findById(id);
    }

    public Products saveProduct(Products product){
        return productRepository.save(product);
    }

    public void deleteProductById(Long id){
        productRepository.deleteById(id);
    }
}
