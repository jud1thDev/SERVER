package com.efub.leadtoyproject.domain.item.dto;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AllItemResponseDto {
    private List<ItemResponseDto> items;
    private int totalItems;
}
