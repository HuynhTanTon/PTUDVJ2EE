package com.example.bai8.service;

import com.example.bai8.entity.Category;
import com.example.bai8.entity.Product;
import com.example.bai8.repository.CategoryRepository;
import com.example.bai8.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Câu 1–4: Tìm kiếm tên (LIKE), phân trang 5/trang, sort giá ASC/DESC, lọc category.
 *
 * <p>Ví dụ: {@code GET /products?page=0&keyword=phone&sort=desc&categoryId=1}
 */
@Service
public class ProductCatalogService {

    /** Câu 2: mỗi trang 5 sản phẩm */
    public static final int PAGE_SIZE = 5;

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductCatalogService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Category> allCategories() {
        return categoryRepository.findAll();
    }

    /**
     * Kết hợp keyword + category + sort price + page (0-based).
     */
    public Page<Product> search(String keyword, Integer categoryId, String sort, int page) {
        boolean hasKeyword = StringUtils.hasText(keyword);
        boolean hasCategory = categoryId != null;

        Sort.Direction direction = "desc".equalsIgnoreCase(sort) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(Math.max(page, 0), PAGE_SIZE, Sort.by(direction, "price"));

        String kw = hasKeyword ? keyword.trim() : "";

        if (hasKeyword && hasCategory) {
            return productRepository.findByNameContainingIgnoreCaseAndCategory_Id(kw, categoryId, pageable);
        }
        if (hasKeyword) {
            return productRepository.findByNameContainingIgnoreCase(kw, pageable);
        }
        if (hasCategory) {
            return productRepository.findByCategory_Id(categoryId, pageable);
        }
        return productRepository.findAll(pageable);
    }
}
