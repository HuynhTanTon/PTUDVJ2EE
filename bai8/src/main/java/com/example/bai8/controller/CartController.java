package com.example.bai8.controller;

import com.example.bai8.service.CartService;
import com.example.bai8.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Câu 5: POST /cart/add/{productId} — lưu session, trùng sản phẩm thì +quantity.
 * Câu 6: GET /cart — danh sách, thành tiền dòng, tổng tiền.
 * Câu 7: POST /checkout — lưu Order + OrderDetail, xóa giỏ.
 */
@Controller
public class CartController {

    private final CartService cartService;
    private final OrderService orderService;

    public CartController(CartService cartService, OrderService orderService) {
        this.cartService = cartService;
        this.orderService = orderService;
    }

    @PostMapping("/cart/add/{productId}")
    public String addToCart(@PathVariable Long productId,
                            HttpSession session,
                            RedirectAttributes redirectAttributes) {
        try {
            cartService.addProduct(session, productId);
            redirectAttributes.addFlashAttribute("message", "Đã thêm sản phẩm vào giỏ hàng.");
        } catch (IllegalArgumentException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/products";
    }

    @GetMapping("/cart")
    public String viewCart(HttpSession session, Model model) {
        model.addAttribute("lines", cartService.lines(session));
        model.addAttribute("grandTotal", cartService.grandTotal(session));
        model.addAttribute("cartEmpty", cartService.isEmpty(session));
        return "cart/view";
    }

    @PostMapping("/checkout")
    public String checkout(HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            Long orderId = orderService.checkout(session);
            redirectAttributes.addFlashAttribute("orderId", orderId);
            return "redirect:/order/success";
        } catch (IllegalStateException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
            return "redirect:/cart";
        }
    }

    @GetMapping("/order/success")
    public String orderSuccess() {
        return "order/success";
    }
}
