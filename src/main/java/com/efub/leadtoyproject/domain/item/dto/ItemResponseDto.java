package com.efub.leadtoyproject.domain.item.dto;

import com.efub.leadtoyproject.domain.item.domain.Item;
import com.efub.leadtoyproject.domain.item.domain.ItemStatus;
import com.efub.leadtoyproject.domain.product.domain.Product;
import com.efub.leadtoyproject.domain.product.dto.ProductResponseDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ItemResponseDto {
    private Long itemId;
    private Long price;
    private ItemStatus status;
    private String store;
    private String location;

    public static ItemResponseDto from(Item item) {
        return new ItemResponseDto(
                item.getItemId(),
                item.getPrice(),
                item.getStatus(),
                item.getStore(),
                item.getLocation()
        );
    }
}
