package com.efub.leadtoyproject.domain.product.dto;

import com.efub.leadtoyproject.domain.product.domain.Product;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AllProductResponseDto {
    private List<ProductResponseDto> products;
    private int totalProducts;
}
