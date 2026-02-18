package com.example.question1_library_api.controller;

import com.example.question1_library_api.model.Book;
import com.example.question1_library_api.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    // GET all books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookRepository.findAll());
    }

    // GET book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    // SEARCH by title
    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchByTitle(@RequestParam String title) {
        return ResponseEntity.ok(bookRepository.findByTitleContainingIgnoreCase(title));
    }

    // POST add new book
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book saved = bookRepository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    // UPDATE book by ID
   @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
       Optional<Book> existing = bookRepository.findById(id);
     if (existing.isEmpty()) {
           return ResponseEntity.notFound().build();
      }
       Book book = existing.get();
       book.setTitle(updatedBook.getTitle());
       book.setAuthor(updatedBook.getAuthor());
       book.setIsbn(updatedBook.getIsbn());
        book.setPublicationYear(updatedBook.getPublicationYear());
    
      Book saved = bookRepository.save(book);
       return ResponseEntity.ok(saved);
    }

    // DELETE book by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (!bookRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


