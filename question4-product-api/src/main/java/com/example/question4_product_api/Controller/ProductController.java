package com.example.question4_product_api.Controller;

import com.example.question4_product_api.model.Product;
import com.example.question4_product_api.repository.productrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private productrepo productRepository;

    // GET all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    // GET product by ID
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        return product.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    // GET by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable String category) {
        return ResponseEntity.ok(productRepository.findByCategoryIgnoreCase(category));
    }

    // GET by brand
    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<Product>> getByBrand(@PathVariable String brand) {
        return ResponseEntity.ok(productRepository.findByBrandIgnoreCase(brand));
    }

    // GET search by keyword
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchByKeyword(@RequestParam String keyword) {
        return ResponseEntity.ok(
            productRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword)
        );
    }

    // GET by price range
    @GetMapping("/price-range")
    public ResponseEntity<List<Product>> getByPriceRange(@RequestParam Double min, @RequestParam Double max) {
        return ResponseEntity.ok(productRepository.findByPriceBetween(min, max));
    }

    // GET in-stock products
    @GetMapping("/in-stock")
    public ResponseEntity<List<Product>> getInStock() {
        return ResponseEntity.ok(productRepository.findByStockQuantityGreaterThan(0));
    }

    // POST add new product
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product saved = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // PUT update product
    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product updatedProduct) {
        Optional<Product> existing = productRepository.findById(productId);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Product product = existing.get();
        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());
        product.setCategory(updatedProduct.getCategory());
        product.setStockQuantity(updatedProduct.getStockQuantity());
        product.setBrand(updatedProduct.getBrand());
        return ResponseEntity.ok(productRepository.save(product));
    }

    // PATCH update stock
    @PatchMapping("/{productId}/stock")
    public ResponseEntity<Product> updateStock(@PathVariable Long productId, @RequestParam int quantity) {
        Optional<Product> existing = productRepository.findById(productId);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Product product = existing.get();
        product.setStockQuantity(quantity);
        return ResponseEntity.ok(productRepository.save(product));
    }

    // DELETE product
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        if (!productRepository.existsById(productId)) {
            return ResponseEntity.notFound().build();
        }
        productRepository.deleteById(productId);
        return ResponseEntity.noContent().build();
    }
}



