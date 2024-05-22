package com.efub.leadtoyproject.domain.product.dto;

import com.efub.leadtoyproject.domain.item.dto.ItemResponseDto;
import com.efub.leadtoyproject.domain.product.domain.Product;
import java.math.BigDecimal;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SearchProductResponseDto {
    private Long productId;
    private Long categoryId;
    private String productName;
    private Long originalPrice;
    private String info;
    private Long stockQuantity;
    private BigDecimal avgRating;
    private List<ItemResponseDto> items;

    public static SearchProductResponseDto from(Product product, List<ItemResponseDto> items) {
        return new SearchProductResponseDto(
                product.getProductId(),
                product.getCategory().getCategoryId(),
                product.getProductName(),
                product.getOriginalPrice(),
                product.getInfo(),
                product.getStockQuantity(),
                product.getAvgRating(),
                items
        );
    }
}
