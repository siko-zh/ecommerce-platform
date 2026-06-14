package com.zholdigaliev.product.dto.mapper;

import com.zholdigaliev.product.dto.CategoryDto.CategoryRequest;
import com.zholdigaliev.product.dto.CategoryDto.CategoryResponse;
import com.zholdigaliev.product.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponse toResponse(Category category);

    Category toEntity(CategoryRequest request);
}
