package com.efub.leadtoyproject.domain.product.dto;

import com.efub.leadtoyproject.domain.product.domain.Product;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProductResponseDto {
    private Long productId;
    private Long categoryId;
    private String productName;
    private Long originalPrice;
    private String info;
    private Long stockQuantity;
    private BigDecimal avgRating;

    public static ProductResponseDto from(Product product) {
        return new ProductResponseDto(
                product.getProductId(),
                product.getCategory().getCategoryId(),
                product.getProductName(),
                product.getOriginalPrice(),
                product.getInfo(),
                product.getStockQuantity(),
                product.getAvgRating()
                );
    }
}
