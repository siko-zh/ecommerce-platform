package com.zholdigaliev.product.dto.mapper;

import com.zholdigaliev.product.dto.ProductDto.ProductRequest;
import com.zholdigaliev.product.dto.ProductDto.ProductResponse;
import com.zholdigaliev.product.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.name", target = "categoryName")
    ProductResponse toResponse(Product product);

    @Mapping(source = "category.name", target = "categoryName")
    List<ProductResponse> toResponses(List<Product> products);

    Product toEntity(ProductRequest request);
}
