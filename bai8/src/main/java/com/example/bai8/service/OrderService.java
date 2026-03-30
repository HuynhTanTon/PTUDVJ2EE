package com.example.bai8.service;

import com.example.bai8.dto.CartLine;
import com.example.bai8.entity.Order;
import com.example.bai8.entity.OrderDetail;
import com.example.bai8.repository.OrderRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Câu 7: Checkout — tạo Order (date, totalAmount), lưu OrderDetail cho từng dòng giỏ,
 * rồi xóa session giỏ hàng.
 */
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;

    public OrderService(OrderRepository orderRepository, CartService cartService) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
    }

    @Transactional
    public Long checkout(HttpSession session) {
        List<CartLine> lines = cartService.lines(session);
        if (lines.isEmpty()) {
            throw new IllegalStateException("Giỏ hàng trống");
        }

        long total = cartService.grandTotal(session);

        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setTotalAmount(total);

        for (CartLine line : lines) {
            OrderDetail detail = OrderDetail.builder()
                    .order(order)
                    .productId(line.getProductId())
                    .price(line.getPrice())
                    .quantity(line.getQuantity())
                    .build();
            order.getDetails().add(detail);
        }

        Order saved = orderRepository.save(order);
        cartService.clear(session);
        return saved.getId();
    }
}
