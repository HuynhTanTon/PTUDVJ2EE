package com.example.bai2.repository;

import com.example.bai2.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryBookRepository implements BookRepository {

    private final List<Book> books = new ArrayList<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    public InMemoryBookRepository() {
        // Dữ liệu mẫu 2–5 quyển
        books.add(new Book(1, "Java Core", "James Gosling"));
        books.add(new Book(2, "Spring Boot in Action", "Craig Walls"));
        books.add(new Book(3, "Docker Deep Dive", "Nigel Poulton"));
        books.add(new Book(4, "Clean Code", "Robert C. Martin"));
        books.add(new Book(5, "Thymeleaf Tutorial", "Pivotal"));
        idGenerator.set(6);
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(books);
    }

    @Override
    public Book findById(int id) {
        return books.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == 0) {
            book.setId(idGenerator.getAndIncrement());
            books.add(book);
            return book;
        }
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == book.getId()) {
                books.set(i, book);
                return book;
            }
        }
        books.add(book);
        return book;
    }

    @Override
    public void deleteById(int id) {
        books.removeIf(b -> b.getId() == id);
    }
}
