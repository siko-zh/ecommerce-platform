package com.zholdigaliev.product.controller;

import com.zholdigaliev.common.dto.ApiResponse;
import com.zholdigaliev.product.dto.ProductDto.ProductRequest;
import com.zholdigaliev.product.dto.ProductDto.ProductResponse;
import com.zholdigaliev.product.service.ProductService;
import jakarta.validation.Valid;
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
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success(productService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> getById (@PathVariable Long id){
        return ResponseEntity.ok(ApiResponse.success(productService.findById(id)));
    }

    @GetMapping("/batch")
    public ResponseEntity<List<ProductResponse>> getAllById (@RequestParam List<Long> ids){
        return ResponseEntity.ok(productService.findAllById(ids));
    }


    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> create(@Valid @RequestBody ProductRequest product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(productService.create(product)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> update(@PathVariable Long id, @Valid @RequestBody ProductRequest product) {
        return ResponseEntity.ok(ApiResponse.success(productService.update(id, product)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
