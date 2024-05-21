package com.efub.leadtoyproject.domain.product.dto;

import com.efub.leadtoyproject.domain.category.domain.ProductType;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MainProductsResponseDto {
    // 메인페이지 물품 4개씩 조회를 위해 ProductDto, ItemInfoDto, ProductTypeDto 3개의 Dto 이용하여 반환
    private List<ProductTypeDto> productTypes;
}
