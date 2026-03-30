package com.example.bai8.service;

import com.example.bai8.dto.CartLine;
import com.example.bai8.entity.Product;
import com.example.bai8.repository.ProductRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Câu 5–6: Giỏ hàng lưu trong session (Map productId → CartLine).
 * Đã có sản phẩm → tăng quantity; chưa có → thêm mới quantity = 1.
 */
@Service
public class CartService {

    private static final String SESSION_CART = "BAI8_SHOP_CART";

    private final ProductRepository productRepository;

    public CartService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @SuppressWarnings("unchecked")
    private Map<Long, CartLine> cartMap(HttpSession session) {
        Object raw = session.getAttribute(SESSION_CART);
        if (raw instanceof Map<?, ?> m) {
            return (Map<Long, CartLine>) m;
        }
        Map<Long, CartLine> map = new LinkedHashMap<>();
        session.setAttribute(SESSION_CART, map);
        return map;
    }

    /** Thêm 1 sản phẩm (hoặc +1 quantity nếu đã có). */
    public void addProduct(HttpSession session, Long productId) {
        Product p = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy sản phẩm: " + productId));
        Map<Long, CartLine> map = cartMap(session);
        map.merge(productId,
                new CartLine(p.getId(), p.getName(), p.getPrice(), 1),
                (existing, ignored) -> {
                    existing.setQuantity(existing.getQuantity() + 1);
                    return existing;
                });
    }

    public List<CartLine> lines(HttpSession session) {
        return new ArrayList<>(cartMap(session).values());
    }

    public long grandTotal(HttpSession session) {
        return lines(session).stream().mapToLong(CartLine::getLineTotal).sum();
    }

    public boolean isEmpty(HttpSession session) {
        return cartMap(session).isEmpty();
    }

    /** Câu 7: sau checkout xóa giỏ */
    public void clear(HttpSession session) {
        session.removeAttribute(SESSION_CART);
    }
}
