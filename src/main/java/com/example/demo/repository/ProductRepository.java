package com.example.demo.repository;

import com.example.demo.model.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(String category);
    // List<Product> findByBrand(String brand);
    List<Product>findByPriceAndBrand(Double price, String brand);
    // List<Product> findByNameStartsWith(String name);
    // List<Product>findByPriceGreaterThan(Double price);
    // List<Product>findByPriceBetween(Double firstPrice, Double secondPrice);

}
