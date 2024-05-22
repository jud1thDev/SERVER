package com.efub.leadtoyproject.domain.cart.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CartItemResponseDto {
    private String productType;
    private Long productId;
    private Long cartItemId;
    private String productName;
    private String info;
    private Long price;
    private String location;
    private String status;
}
