package com.croix.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.croix.product.model.Products;

public interface ProductRepository extends JpaRepository<Products,Long>{

}
