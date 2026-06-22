package com.zholdigaliev.product.service;

import com.zholdigaliev.product.dto.ProductDto.ProductRequest;
import com.zholdigaliev.product.dto.ProductDto.ProductResponse;
import com.zholdigaliev.product.dto.mapper.CategoryMapper;
import com.zholdigaliev.product.dto.mapper.ProductMapper;
import com.zholdigaliev.product.exception.CategoryNotFoundException;
import com.zholdigaliev.product.exception.ProductNotFoundException;
import com.zholdigaliev.product.model.Category;
import com.zholdigaliev.product.model.Product;
import com.zholdigaliev.product.repository.CategoryRepository;
import com.zholdigaliev.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper mapper;

    public List<ProductResponse> findAll() {
        return  productRepository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    public ProductResponse findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Продукт не найден"));
        return mapper.toResponse(product);
    }

    public List<ProductResponse> findAllById(List<Long> productIds){
        List<Product> products = productRepository.findAllById(productIds);
        return mapper.toResponses(products);
    }

    public ProductResponse create(ProductRequest request) {
        Product product = mapper.toEntity(request);
        Product saved = productRepository.save(product);
        return mapper.toResponse(saved);
    }

    public ProductResponse update(Long id, ProductRequest updated) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Продукт не найден"));

        product.setName(updated.getName());
        product.setDescription(updated.getDescription());

        Category category = categoryRepository.findById(updated.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Категория не найдена"));

        product.setCategory(category);
        product.setPrice(updated.getPrice());
        product.setStockQuantity(updated.getStockQuantity());
        product.setUpdatedAt(LocalDateTime.now());

        Product saved = productRepository.save(product);

        return mapper.toResponse(saved);
    }

    public void delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Продукт не найден"));
        productRepository.delete(product);
    }
}
