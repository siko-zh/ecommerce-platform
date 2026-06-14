package com.zholdigaliev.product.service;

import com.zholdigaliev.product.dto.CategoryDto.CategoryRequest;
import com.zholdigaliev.product.dto.CategoryDto.CategoryResponse;
import com.zholdigaliev.product.dto.mapper.CategoryMapper;
import com.zholdigaliev.product.exception.CategoryNotFoundException;
import com.zholdigaliev.product.model.Category;
import com.zholdigaliev.product.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper mapper;

    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    public CategoryResponse findById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Категория не найдена"));
        return mapper.toResponse(category);
    }

    public CategoryResponse create(CategoryRequest request) {
        Category category = mapper.toEntity(request);
        Category saved = categoryRepository.save(category);
        return mapper.toResponse(saved);
    }

    public CategoryResponse update(Long id, CategoryRequest updated) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Категория не найдена"));

        category.setName(updated.getName());
        category.setDescription(updated.getDescription());

        Category saved = categoryRepository.save(category);
        return mapper.toResponse(saved);
    }

    public void delete(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Категория не найден"));

        categoryRepository.delete(category);
    }
}
