package auca.ac.rw.restfullApiAssignment.controller.ecommerce;

import auca.ac.rw.restfullApiAssignment.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private List<Product> products = new ArrayList<>();

    public ProductController() {
        products.add(new Product(1L, "iPhone 14", "Apple smartphone", 999.0, "Electronics", 10, "Apple"));
        products.add(new Product(2L, "Galaxy S22", "Samsung smartphone", 899.0, "Electronics", 15, "Samsung"));
        products.add(new Product(3L, "MacBook Air", "M1 laptop", 1200.0, "Computers", 8, "Apple"));
        products.add(new Product(4L, "Dell XPS 13", "Compact laptop", 1100.0, "Computers", 5, "Dell"));
        products.add(new Product(5L, "Nike Air Max", "Running shoes", 150.0, "Fashion", 20, "Nike"));
        products.add(new Product(6L, "Adidas Ultraboost", "Sport shoes", 160.0, "Fashion", 12, "Adidas"));
        products.add(new Product(7L, "Sony Headphones", "Noise cancelling", 300.0, "Electronics", 7, "Sony"));
        products.add(new Product(8L, "Coffee Maker", "Home appliance", 80.0, "Home", 18, "Philips"));
        products.add(new Product(9L, "Office Chair", "Ergonomic", 200.0, "Furniture", 3, "Ikea"));
        products.add(new Product(10L, "Blender", "Kitchen appliance", 60.0, "Home", 9, "Kenwood"));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll(@RequestParam(required = false) Integer page,
                                                @RequestParam(required = false) Integer limit) {
        if (page != null && limit != null) {
            int start = page * limit;
            int end = Math.min(start + limit, products.size());
            if (start >= products.size()) return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
            return new ResponseEntity<>(products.subList(start, end), HttpStatus.OK);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getById(@PathVariable Long productId) {
        for (Product p : products) {
            if (p.getProductId().equals(productId)) {
                return new ResponseEntity<>(p, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable String category) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getCategory().equalsIgnoreCase(category)) {
                result.add(p);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<Product>> getByBrand(@PathVariable String brand) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getBrand().equalsIgnoreCase(brand)) {
                result.add(p);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> search(@RequestParam String keyword) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                p.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(p);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/price-range")
    public ResponseEntity<List<Product>> priceRange(@RequestParam Double min, @RequestParam Double max) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getPrice() >= min && p.getPrice() <= max) {
                result.add(p);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/in-stock")
    public ResponseEntity<List<Product>> inStock() {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getStockQuantity() > 0) {
                result.add(p);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> add(@RequestBody Product product) {
        products.add(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> update(@PathVariable Long productId, @RequestBody Product data) {
        for (Product p : products) {
            if (p.getProductId().equals(productId)) {
                p.setName(data.getName());
                p.setDescription(data.getDescription());
                p.setPrice(data.getPrice());
                p.setCategory(data.getCategory());
                p.setStockQuantity(data.getStockQuantity());
                p.setBrand(data.getBrand());
                return new ResponseEntity<>(p, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{productId}/stock")
    public ResponseEntity<Product> updateStock(@PathVariable Long productId, @RequestParam int quantity) {
        for (Product p : products) {
            if (p.getProductId().equals(productId)) {
                p.setStockQuantity(quantity);
                return new ResponseEntity<>(p, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> delete(@PathVariable Long productId) {
        Product target = null;
        for (Product p : products) {
            if (p.getProductId().equals(productId)) {
                target = p;
                break;
            }
        }
        if (target != null) {
            products.remove(target);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

