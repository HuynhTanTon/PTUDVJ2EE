package com.example.bai8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /** Trang chủ chuyển thẳng tới catalog sản phẩm. */
    @GetMapping("/")
    public String index() {
        return "redirect:/products";
    }
}
