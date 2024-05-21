package com.efub.leadtoyproject.domain.product.dto;

import java.util.List;
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
}