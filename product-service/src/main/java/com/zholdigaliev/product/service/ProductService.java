package com.zholdigaliev.product.service;

import com.zholdigaliev.common.exception.ResourceNotFoundException;
import com.zholdigaliev.product.model.Product;
import com.zholdigaliev.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return  productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Продукт не найден"));
    }

    public Product create(Product product) {
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    public Product update(Long id, Product updated) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Продукт не найден"));

        product.setName(updated.getName());
        product.setDescription(updated.getDescription());
        product.setCategory(updated.getCategory());
        product.setPrice(updated.getPrice());
        product.setStockQuantity(updated.getStockQuantity());
        product.setUpdatedAt(LocalDateTime.now());

        return productRepository.save(product);
    }

    public void delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Продукт не найден"));
        productRepository.delete(product);
    }
}
