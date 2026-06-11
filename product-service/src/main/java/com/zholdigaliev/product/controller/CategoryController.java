package com.zholdigaliev.product.controller;

import com.zholdigaliev.common.dto.ApiResponse;
import com.zholdigaliev.product.model.Category;
import com.zholdigaliev.product.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Category>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success(categoryService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Category>> getById (@PathVariable Long id){
        return ResponseEntity.ok(ApiResponse.success(categoryService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Category>> create(@RequestBody Category category) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(categoryService.create(category)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Category>> update(@PathVariable Long id, @RequestBody Category category) {
        return ResponseEntity.ok(ApiResponse.success(categoryService.update(id, category)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
