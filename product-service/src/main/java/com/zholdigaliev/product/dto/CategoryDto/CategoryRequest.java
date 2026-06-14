package com.zholdigaliev.product.dto.CategoryDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequest {
    @NotBlank(message = "Название продукта не может быть пустым!")
    private String name;
    private String description;
}
