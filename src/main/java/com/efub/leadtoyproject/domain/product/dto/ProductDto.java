package com.efub.leadtoyproject.domain.product.dto;

import com.efub.leadtoyproject.domain.item.domain.Item;
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
public class ProductDto {
    private String productName;
    private Long categoryId;
    private List<ItemInfoDto> items;

    public static ProductDto from(Product product){
        List<ItemInfoDto> items = product.getItems().stream()
                .map(ItemInfoDto::from)
                .collect(Collectors.toList());
        return new ProductDto(product.getProductName(), product.getCategory().getCategoryId(), items);
    }
}