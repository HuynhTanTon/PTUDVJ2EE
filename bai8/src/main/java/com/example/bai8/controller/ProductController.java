package com.example.bai8.controller;

import com.example.bai8.entity.Product;
import com.example.bai8.service.ProductCatalogService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Câu 1–4: Danh sách sản phẩm — tìm kiếm, phân trang, sort, lọc category.
 *
 * <p>Ví dụ request:
 * <ul>
 *   <li>{@code GET /products} — trang 1, sort giá tăng, không lọc</li>
 *   <li>{@code GET /products?keyword=phone&page=0&sort=desc&categoryId=1}</li>
 * </ul>
 */
@Controller
public class ProductController {

    private final ProductCatalogService catalogService;

    public ProductController(ProductCatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("/products")
    public String list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(defaultValue = "asc") String sort,
            Model model) {

        Page<Product> productPage = catalogService.search(keyword, categoryId, sort, page);

        model.addAttribute("productPage", productPage);
        model.addAttribute("categories", catalogService.allCategories());
        model.addAttribute("keyword", keyword != null ? keyword : "");
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("sort", sort);
        model.addAttribute("currentPage", page);

        return "products/list";
    }
}
