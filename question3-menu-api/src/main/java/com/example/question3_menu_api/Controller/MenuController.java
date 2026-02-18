package com.example.question3_menu_api.Controller;

import com.example.question3_menu_api.model.MenuItem;
import com.example.question3_menu_api.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    private MenuRepository menuItemRepository;

     // GET all menu items
     @GetMapping
     public ResponseEntity<List<MenuItem>> getAllMenuItems() {
         return ResponseEntity.ok(menuItemRepository.findAll());
     }
 
     // GET item by ID
     @GetMapping("/{id}")
     public ResponseEntity<MenuItem> getMenuItemById(@PathVariable Long id) {
         Optional<MenuItem> item = menuItemRepository.findById(id);
         return item.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
     }
 
     // GET by category
     @GetMapping("/category/{category}")
     public ResponseEntity<List<MenuItem>> getByCategory(@PathVariable String category) {
         return ResponseEntity.ok(menuItemRepository.findByCategoryIgnoreCase(category));
     }
 
     // GET available items
     @GetMapping("/available")
     public ResponseEntity<List<MenuItem>> getAvailable(@RequestParam boolean available) {
         return ResponseEntity.ok(menuItemRepository.findByAvailable(available));
     }
 
     // GET search by name
     @GetMapping("/search")
     public ResponseEntity<List<MenuItem>> searchByName(@RequestParam String name) {
         return ResponseEntity.ok(menuItemRepository.findByNameContainingIgnoreCase(name));
     }
 
     // POST add new item
     @PostMapping
     public ResponseEntity<MenuItem> addMenuItem(@RequestBody MenuItem item) {
         MenuItem saved = menuItemRepository.save(item);
         return ResponseEntity.status(HttpStatus.CREATED).body(saved);
     }
 
     // PUT toggle availability
     @PutMapping("/{id}/availability")
     public ResponseEntity<MenuItem> toggleAvailability(@PathVariable Long id) {
         Optional<MenuItem> existing = menuItemRepository.findById(id);
         if (existing.isEmpty()) {
             return ResponseEntity.notFound().build();
         }
         MenuItem item = existing.get();
         item.setAvailable(!item.isAvailable());
         return ResponseEntity.ok(menuItemRepository.save(item));
     }
 
     // PUT update full item
     @PutMapping("/{id}")
     public ResponseEntity<MenuItem> updateMenuItem(@PathVariable Long id, @RequestBody MenuItem updatedItem) {
         Optional<MenuItem> existing = menuItemRepository.findById(id);
         if (existing.isEmpty()) {
             return ResponseEntity.notFound().build();
         }
         MenuItem item = existing.get();
         item.setName(updatedItem.getName());
         item.setDescription(updatedItem.getDescription());
         item.setPrice(updatedItem.getPrice());
         item.setCategory(updatedItem.getCategory());
         item.setAvailable(updatedItem.isAvailable());
         return ResponseEntity.ok(menuItemRepository.save(item));
     }
 
     // DELETE item
     @DeleteMapping("/{id}")
     public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
         if (!menuItemRepository.existsById(id)) {
             return ResponseEntity.notFound().build();
         }
         menuItemRepository.deleteById(id);
         return ResponseEntity.noContent().build();
     }
   
}
