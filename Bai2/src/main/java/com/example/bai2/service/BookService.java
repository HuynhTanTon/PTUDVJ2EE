package com.example.bai2.service;

import com.example.bai2.model.Book;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();

    Book getBookById(int id);

    void addBook(Book book);

    Book updateBook(int id, Book book);

    void deleteBook(int id);
}
