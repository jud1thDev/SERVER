package com.efub.leadtoyproject.domain.product.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ItemInfoDto {
    private Long itemId;
    private Long price;
    private String store;
    private String imgPath;
}
