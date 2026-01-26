package com.example.bai2.controller;

import com.example.bai2.model.Book;
import com.example.bai2.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // GET: /api/books
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    // GET: /api/books/{id}
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) {
        return bookService.getBookById(id);
    }

    // POST: /api/books
    @PostMapping
    public String addBook(@RequestBody Book book) {
        bookService.addBook(book);
        return "Added successfully!";
    }

    // PUT: /api/books/{id}
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable int id, @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }

    // DELETE: /api/books/{id}
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) {
        boolean ok = bookService.deleteBook(id);
        return ok ? "Deleted successfully!" : "Book not found!";
    }
}
