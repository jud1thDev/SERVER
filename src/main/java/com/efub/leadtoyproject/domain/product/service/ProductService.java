package com.efub.leadtoyproject.domain.product.service;

import com.efub.leadtoyproject.domain.category.domain.ProductType;
import com.efub.leadtoyproject.domain.item.repository.ItemRepository;
import com.efub.leadtoyproject.domain.product.domain.Product;
import com.efub.leadtoyproject.domain.product.dto.ProductDto;
import com.efub.leadtoyproject.domain.product.repository.ProductRepository;
import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ItemRepository itemRepository;

    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }

    public Product findProductById(Long productId) {
        return productRepository.findByProductId(productId);
    }

    public List<Product> findProductsByType(ProductType productType) {
        return productRepository.findByProductType(productType);
    }

}
