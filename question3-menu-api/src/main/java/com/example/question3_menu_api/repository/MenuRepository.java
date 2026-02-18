package com.example.question3_menu_api.repository;

import com.example.question3_menu_api.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MenuRepository extends JpaRepository<MenuItem, Long> {
    List<MenuItem> findByCategoryIgnoreCase(String category);
    List<MenuItem> findByAvailable(boolean available);
    List<MenuItem> findByNameContainingIgnoreCase(String name);

}
