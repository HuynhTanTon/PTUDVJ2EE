package com.example.bai2.service;

import com.example.bai2.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private final List<Book> books = new ArrayList<>();

    public BookService() {
        // dữ liệu mẫu giống slide
        books.add(new Book(1, "Java", "James"));
        books.add(new Book(2, "Spring Boot", "Pivotal"));
        books.add(new Book(3, "Docker", "Solomon"));
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public Book getBookById(int id) {
        return books.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public Book updateBook(int id, Book updatedBook) {
        Book existing = getBookById(id);
        if (existing == null) return null;

        existing.setTitle(updatedBook.getTitle());
        existing.setAuthor(updatedBook.getAuthor());
        return existing;
    }

    public boolean deleteBook(int id) {
        return books.removeIf(b -> b.getId() == id);
    }
}
