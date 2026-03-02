package com.example.bai4.service;

import com.example.bai4.model.Product;
import com.example.bai4.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product get(int id) {
        return productRepository.findById(id).orElse(null);
    }

    public void add(Product newProduct) {
        productRepository.save(newProduct);
    }

    public void update(Product editProduct) {
        Product find = get(editProduct.getId());
        if (find != null) {
            find.setPrice(editProduct.getPrice());
            find.setName(editProduct.getName());
            if (editProduct.getImage() != null) {
                find.setImage(editProduct.getImage());
            }
            find.setCategory(editProduct.getCategory());
            productRepository.save(find);
        }
    }

    public void updateImage(Product newProduct, MultipartFile imageProduct) {
        String contentType = imageProduct.getContentType();
        if (contentType == null) {
            throw new IllegalArgumentException("Tệp tải lên không phải là hình ảnh!");
        }

        if (!imageProduct.isEmpty()) {
            try {
                Path dirImages = Paths.get("src/main/resources/static/images");
                if (!Files.exists(dirImages)) {
                    Files.createDirectories(dirImages);
                }

                String originalFilename = imageProduct.getOriginalFilename();
                String newFileName = UUID.randomUUID().toString() + "_" + originalFilename;
                Path pathFileUpload = dirImages.resolve(newFileName);

                Files.copy(imageProduct.getInputStream(), pathFileUpload, StandardCopyOption.REPLACE_EXISTING);
                newProduct.setImage(newFileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(int id) {
        productRepository.deleteById(id);
    }
}