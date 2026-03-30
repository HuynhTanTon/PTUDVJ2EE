package com.example.bai8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Câu 5–6: Một dòng trong giỏ session — productId, name, price, quantity.
 * {@link Serializable} để an toàn khi lưu vào {@link jakarta.servlet.http.HttpSession}.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartLine implements Serializable {

    private Long productId;
    private String name;
    private Long price;
    private int quantity;

    /** Thành tiền dòng: price * quantity */
    public long getLineTotal() {
        return price * quantity;
    }
}
