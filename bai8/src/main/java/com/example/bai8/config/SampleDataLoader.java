package com.example.bai8.config;

import com.example.bai8.entity.Category;
import com.example.bai8.entity.Product;
import com.example.bai8.repository.CategoryRepository;
import com.example.bai8.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Nạp category + product mẫu khi DB trống (demo tuần 5–6).
 * Không chạy khi profile {@code test}.
 */
@Configuration
@Profile("!test")
public class SampleDataLoader {

    @Bean
    CommandLineRunner loadSampleProducts(CategoryRepository categoryRepository, ProductRepository productRepository) {
        return args -> {
            if (productRepository.count() > 0) {
                return;
            }
            Category phone = categoryRepository.save(new Category(null, "Điện thoại"));
            Category laptop = categoryRepository.save(new Category(null, "Laptop"));
            Category accessory = categoryRepository.save(new Category(null, "Phụ kiện"));

            productRepository.save(Product.builder()
                    .name("iPhone 15 Pro")
                    .price(28_990_000L)
                    .image("https://picsum.photos/seed/iphone/200/150")
                    .category(phone)
                    .build());
            productRepository.save(Product.builder()
                    .name("Samsung Galaxy S24")
                    .price(19_500_000L)
                    .image("https://picsum.photos/seed/samsung/200/150")
                    .category(phone)
                    .build());
            productRepository.save(Product.builder()
                    .name("Dell XPS 15")
                    .price(42_000_000L)
                    .image("https://picsum.photos/seed/dell/200/150")
                    .category(laptop)
                    .build());
            productRepository.save(Product.builder()
                    .name("MacBook Air M3")
                    .price(27_990_000L)
                    .image("https://picsum.photos/seed/macbook/200/150")
                    .category(laptop)
                    .build());
            productRepository.save(Product.builder()
                    .name("Tai nghe Bluetooth")
                    .price(890_000L)
                    .image("https://picsum.photos/seed/headphone/200/150")
                    .category(accessory)
                    .build());
            productRepository.save(Product.builder()
                    .name("Sạc nhanh USB-C")
                    .price(350_000L)
                    .image("https://picsum.photos/seed/charger/200/150")
                    .category(accessory)
                    .build());
            productRepository.save(Product.builder()
                    .name("iPhone 14")
                    .price(17_900_000L)
                    .image("https://picsum.photos/seed/ip14/200/150")
                    .category(phone)
                    .build());
        };
    }
}
