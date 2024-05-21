package com.efub.leadtoyproject.domain.item.controller;

import com.efub.leadtoyproject.domain.item.service.ItemService;
import com.efub.leadtoyproject.domain.product.dto.MainProductsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

    private ItemService itemService;

    /**메인페이지 상품 조회*/
//    @GetMapping
//    @ResponseStatus(value = HttpStatus.OK)
//    public MainProductsResponseDto getItems(
//            @RequestParam("store") String store) {
//
//        MainProductsResponseDto response = itemService.getItemsByStore(store);
//        return response;
//    }
}