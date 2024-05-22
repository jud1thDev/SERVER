package com.efub.leadtoyproject.domain.item.service;

import com.efub.leadtoyproject.domain.category.domain.ProductType;
import com.efub.leadtoyproject.domain.item.domain.Item;
import com.efub.leadtoyproject.domain.item.repository.ItemRepository;
import com.efub.leadtoyproject.domain.product.domain.Product;
import com.efub.leadtoyproject.domain.product.dto.MainResponseDto;
import com.efub.leadtoyproject.domain.product.dto.ProductTypeDto;
import com.efub.leadtoyproject.domain.product.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
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
    private final ProductRepository productRepository;

    public List<Item> findItemsByProductId(Long productId) {
        return itemRepository.findByProductProductId(productId);
    }

    public MainResponseDto findMainProducts(String store) {
        List<ProductTypeDto> productTypes = new ArrayList<>();

        for (ProductType productType : ProductType.values()) {
            List<Product> products = productRepository.findProductsByStoreAndProductType(store, productType);
            products = products.size() > 4 ? products.subList(0, 4) : products; // 결과를 4개로 제한
            ProductTypeDto productTypeDto = ProductTypeDto.from(productType, products);
            productTypes.add(productTypeDto);
        }

        return new MainResponseDto(productTypes);
    }

}
