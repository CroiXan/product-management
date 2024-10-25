package com.croix.product.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_product;

    @NotNull(message = "")
    @Size(min = 1, max = 100, message = "")
    private String sku;

    @NotNull(message = "")
    @Size(min = 1, max = 100, message = "")
    private String name;

    @Min(value = 0, message = "")
    private Long price;

    @Min(value = 0, message = "")
    @Max(value = 100, message = "")
    private int discount;

    @NotNull(message = "")
    @Size(min = 1, max = 100, message = "")
    private String category;

    @NotNull(message = "")
    @Size(min = 1, max = 1000, message = "")
    private String description;

    @Min(value = 0, message = "")
    private int stock;

    public Long getId_product() {
        return id_product;
    }

    public void setId_product(Long id_product) {
        this.id_product = id_product;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
}
