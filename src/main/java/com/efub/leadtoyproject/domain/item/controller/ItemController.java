package com.efub.leadtoyproject.domain.item.controller;

import com.efub.leadtoyproject.domain.item.service.ItemService;
import com.efub.leadtoyproject.domain.product.dto.MainResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    /**메인페이지 상품 조회*/
    @GetMapping
    public ResponseEntity<MainResponseDto> getItemsByStoreAndProductType(@RequestParam final String store) {
        MainResponseDto response = itemService.findMainProducts(store);
        return ResponseEntity.ok(response);
    }
}