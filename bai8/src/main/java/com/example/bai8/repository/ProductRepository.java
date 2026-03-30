package com.example.bai8.repository;

import com.example.bai8.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Câu 1: LIKE %keyword% → {@link #findByNameContainingIgnoreCase}.
 * Câu 4: lọc category → các overload kèm {@code categoryId}.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Product> findByCategory_Id(Integer categoryId, Pageable pageable);

    Page<Product> findByNameContainingIgnoreCaseAndCategory_Id(String name, Integer categoryId, Pageable pageable);
}
