package com.example.bai8.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Sản phẩm — dùng cho danh sách, tìm kiếm, giỏ hàng (lấy id, name, price).
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String name;

    /** Giá (VND) — dùng Long cho đơn giản, tránh lỗi làm tròn float */
    @Column(nullable = false)
    private Long price;

    @Column(length = 500)
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}
