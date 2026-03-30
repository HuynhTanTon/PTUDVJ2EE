package com.example.bai4.dto;

import java.io.Serializable;

/**
 * Câu 5: Một dòng trong giỏ hàng (lưu session).
 * productId, name, price, quantity
 */
public class CartLine implements Serializable {

    private static final long serialVersionUID = 1L;

    private int productId;
    private String name;
    private long price;
    private int quantity;

    public CartLine() {
    }

    public CartLine(int productId, String name, long price, int quantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public long getLineTotal() {
        return price * quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
