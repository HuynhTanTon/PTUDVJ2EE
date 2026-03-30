package com.example.bai8.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Câu 7: Chi tiết đơn — orderId (qua quan hệ), productId, price, quantity.
 * Giá tại thời điểm đặt hàng được lưu tại đây (snapshot).
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "order_details")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Integer quantity;
}
