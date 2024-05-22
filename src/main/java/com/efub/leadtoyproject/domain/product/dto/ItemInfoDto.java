package com.efub.leadtoyproject.domain.product.dto;

import com.efub.leadtoyproject.domain.item.domain.Item;
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

    public static ItemInfoDto from(Item item) {
        return new ItemInfoDto(item.getItemId(), item.getPrice(), item.getStore(), item.getProduct().getProductImg().getImgPath());
    }
}
