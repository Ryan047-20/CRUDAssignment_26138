package com.example.question4_product_api.repository;

import com.example.question4_product_api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface productrepo extends JpaRepository<Product, Long> {
    List<Product> findByCategoryIgnoreCase(String category);
    List<Product> findByBrandIgnoreCase(String brand);
    List<Product> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);
    List<Product> findByPriceBetween(Double min, Double max);
    List<Product> findByStockQuantityGreaterThan(int quantity);

}
