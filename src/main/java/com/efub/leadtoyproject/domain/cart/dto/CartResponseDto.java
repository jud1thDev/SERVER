package com.efub.leadtoyproject.domain.cart.dto;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CartResponseDto {
    private Integer count;
    private Long totalPrice;
    private List<CartItemResponseDto> cartItemList;
}
