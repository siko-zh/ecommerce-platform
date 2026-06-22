package com.zholdigaliev.orderservice.service;

import com.zholdigaliev.orderservice.dto.ProductDto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceClient {
    private final RestClient restClient;

    public List<ProductResponse> getProducts(List<Long> productIds) {
        List<String> ids = productIds.stream()
                .map(String::valueOf)
                .toList();

        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/products/batch")
                        .queryParam("ids", String.join(",", ids))
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<List<ProductResponse>>() {});
    }
}
