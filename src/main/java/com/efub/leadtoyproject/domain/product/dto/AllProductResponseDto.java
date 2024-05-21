package com.efub.leadtoyproject.domain.product.dto;

import com.efub.leadtoyproject.domain.product.domain.Product;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AllProductResponseDto {
    private List<ProductResponseDto> products;
    private int totalProducts;

    public static ProductResponseDto from(Product product) {
        return new ProductResponseDto(
                product.getProductId(),
                product.getCategory() != null ? product.getCategory().getCategoryId() : null,
                product.getProductName(),
                product.getOriginalPrice(),
                product.getInfo(),
                product.getStockQuantity(),
                product.getAvgRating()
        );
    }
}
