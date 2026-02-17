package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> searchByPriceAndBrand(Double price, String brand) {
        List<Product> products = productRepository.findByPriceAndBrand(price, brand);

        if (products != null && !products.isEmpty()) {
            return products;
        } else {
            return null; 
        }  
        
    }

}
