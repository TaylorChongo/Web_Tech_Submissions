package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository repo;
    private final ProductService productService;

    public ProductController(ProductRepository repo, ProductService productService) {
        this.repo = repo;
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Product create(@RequestBody Product p) {
        if (p == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        return repo.save(p);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product updated) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        return repo.findById(id).map(existing -> {
            existing.setName(updated.getName());
            existing.setDescription(updated.getDescription());
            existing.setPrice(updated.getPrice());
            existing.setCategory(updated.getCategory());
            existing.setStockQuantity(updated.getStockQuantity());
            existing.setBrand(updated.getBrand());
            return ResponseEntity.ok(repo.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        return repo.findById(id).map(p -> {
            if (p != null) {
                repo.delete(p);
            }
            return ResponseEntity.noContent().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/priceandbrand")
    public ResponseEntity<List<Product>> searchProductByPriceAndBrand(@RequestParam Double price, @RequestParam String brand){
        List<Product> getProducts = productService.searchByPriceAndBrand(price, brand);
        if (getProducts != null && !getProducts.isEmpty()){
            return new ResponseEntity<>(getProducts, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(List.of(), HttpStatus.NOT_FOUND);
        }
    }


}
