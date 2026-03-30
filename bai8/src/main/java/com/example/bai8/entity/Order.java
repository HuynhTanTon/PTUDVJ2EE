package com.example.bai8.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Câu 7: Đơn hàng — id, ngày đặt, tổng tiền.
 * Chi tiết nằm trong {@link OrderDetail}.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Thời điểm đặt hàng */
    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;

    /** Tổng tiền toàn đơn */
    @Column(name = "total_amount", nullable = false)
    private Long totalAmount;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> details = new ArrayList<>();
}
