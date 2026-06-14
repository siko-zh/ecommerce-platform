package com.zholdigaliev.product.dto.ProductDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {
    @NotNull
    @NotBlank(message = "Имя продукта не может быть пустым!")
    private String name;

    private String description;

    @NotNull
    @Positive(message = "Цена не может быть отрицательным!")
    private BigDecimal price;

    @NotNull
    @Positive(message = "Количество не может быть отрицательным!")
    private Integer stockQuantity;

    @NotNull(message = "Не был указан категория продукта!")
    private Long categoryId;
}
