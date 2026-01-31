package com.example.bai2.repository;

import com.example.bai2.model.Book;

import java.util.List;

public interface BookRepository {

    List<Book> findAll();

    Book findById(int id);

    Book save(Book book);

    void deleteById(int id);
}
