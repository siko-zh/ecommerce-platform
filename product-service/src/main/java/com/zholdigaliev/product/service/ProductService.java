package com.zholdigaliev.product.service;

import com.zholdigaliev.common.exception.ResourceNotFoundException;
import com.zholdigaliev.product.model.Product;
import com.zholdigaliev.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
