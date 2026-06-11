package com.zholdigaliev.product.service;

import com.zholdigaliev.common.exception.ResourceNotFoundException;
import com.zholdigaliev.product.model.Category;
import com.zholdigaliev.product.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Категория не найдена"));
    }

    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    public Category update(Long id, Category updated) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Категория не найдена"));

        category.setName(updated.getName());
        category.setDescription(updated.getDescription());

        return categoryRepository.save(category);
    }

    public void delete(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Категория не найден"));

        categoryRepository.delete(category);
    }
}
