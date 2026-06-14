package com.zholdigaliev.product.dto.mapper;

import com.zholdigaliev.product.dto.ProductDto.ProductRequest;
import com.zholdigaliev.product.dto.ProductDto.ProductResponse;
import com.zholdigaliev.product.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.name", target = "categoryName")
    ProductResponse toResponse(Product product);

    Product toEntity(ProductRequest request);
}
