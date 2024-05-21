package com.efub.leadtoyproject.domain.item.service;

import com.efub.leadtoyproject.domain.item.domain.Item;
import com.efub.leadtoyproject.domain.item.repository.ItemRepository;
import com.efub.leadtoyproject.domain.product.dto.ItemInfoDto;
import com.efub.leadtoyproject.domain.product.dto.MainProductsResponseDto;
import com.efub.leadtoyproject.domain.product.dto.ProductDto;
import com.efub.leadtoyproject.domain.product.dto.ProductTypeDto;
import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public List<Item> findItemsByProductId(Long productId) {
        return itemRepository.findByProductProductId(productId);
    }

//    public MainProductsResponseDto getItemsByStore(String store) {
//        List<ProductTypeDto> productTypes = new ArrayList<>();
//
//        List<String> productTypesList = Arrays.asList("도서", "음반 및 비디오", "굿즈");
//        for (String productType : productTypesList) {
//            productTypes.add(getProductsByType(store, productType));
//        }
//
//        return new MainProductsResponseDto(productTypes);
//    }

//    private ProductTypeDto getProductsByType(String store, String productType) {
//        Pageable limit = (Pageable) PageRequest.of(0, 4);
//        List<Item> items = itemRepository.findItemsByStoreAndProductType(store, productType, limit);
//        Map<String, ProductDto> productMap = new HashMap<>();
//
//        for (Item item : items) {
//            ProductDto productDto = productMap.getOrDefault(item.getProduct().getProductName(),
//                    new ProductDto(item.getProduct().getProductName(), item.getProduct().getCategory().getCategoryId(),
//                            new ArrayList<>()));
//
//            productDto.getItems().add(new ItemInfoDto(item.getItemId(), item.getPrice(), store,
//                    item.getProduct().getProductImg().getImgPath()));
//            productMap.put(item.getProduct().getProductName(), productDto);
//        }
//
//        return new ProductTypeDto(productType, new ArrayList<>(productMap.values()));
//    }
}
