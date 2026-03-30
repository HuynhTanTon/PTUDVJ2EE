package com.example.bai8.entity;

import jakarta.persistence.*;
import lombok.*;

/** Danh mục sản phẩm — dùng cho Câu 4 (lọc theo category). */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String name;
}
