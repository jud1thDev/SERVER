package com.efub.leadtoyproject.domain.product.dto;

import com.efub.leadtoyproject.domain.item.dto.AllItemResponseDto;
import com.efub.leadtoyproject.domain.item.dto.ItemResponseDto;
import com.efub.leadtoyproject.domain.product.domain.Product;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
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
    private String imgPath;
    private AllItemResponseDto items;

    public static ProductResponseDto from(Product product) {
        List<ItemResponseDto> itemDtos = product.getItems().stream()
                .map(item -> new ItemResponseDto(item.getItemId(), item.getPrice(), item.getStatus(), item.getStore(), item.getLocation()))
                .toList();

        return new ProductResponseDto(
                product.getProductId(),
                product.getCategory().getCategoryId(),
                product.getProductName(),
                product.getOriginalPrice(),
                product.getInfo(),
                product.getStockQuantity(),
                product.getAvgRating(),
                product.getProductImg().getImgPath(),
                new AllItemResponseDto(itemDtos, itemDtos.size())
                );
    }
}
