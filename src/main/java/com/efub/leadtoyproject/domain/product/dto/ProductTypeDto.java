package com.efub.leadtoyproject.domain.product.dto;

import com.efub.leadtoyproject.domain.category.domain.ProductType;
import com.efub.leadtoyproject.domain.product.domain.Product;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProductTypeDto {
    private String productType;
    private List<ProductDto> products;

    public static ProductTypeDto from(ProductType productType, List<Product> products) {
        List<ProductDto> productDtos = products.stream()
                .map(ProductDto::from)
                .collect(Collectors.toList());
        return new ProductTypeDto(productType.getDisplayName(), productDtos);
    }
}
