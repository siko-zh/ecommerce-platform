package com.zholdigaliev.product.controller;

import com.zholdigaliev.common.dto.ApiResponse;
import com.zholdigaliev.product.model.Product;
import com.zholdigaliev.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Product>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success(productService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> getById (@PathVariable Long id){
        return ResponseEntity.ok(ApiResponse.success(productService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Product>> create(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(productService.create(product)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> update(@PathVariable Long id, @RequestBody Product product) {
        return ResponseEntity.ok(ApiResponse.success(productService.update(id, product)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
