package com.efub.leadtoyproject.domain.product.service;

import com.efub.leadtoyproject.domain.category.domain.ProductType;
import com.efub.leadtoyproject.domain.item.repository.ItemRepository;
import com.efub.leadtoyproject.domain.product.domain.Product;
import com.efub.leadtoyproject.domain.product.dto.ProductResponseDto;
import com.efub.leadtoyproject.domain.product.repository.ProductRepository;
import com.efub.leadtoyproject.domain.review.domain.Review;
import com.efub.leadtoyproject.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ItemRepository itemRepository;
    private final ReviewRepository reviewRepository;

    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }

    public Product findProductById(Long productId) {
        return productRepository.findByProductId(productId);
    }

    public List<Product> findProductsByType(ProductType productType) {
        return productRepository.findByProductType(productType);
    }

    public List<ProductResponseDto> searchProductsByName(String productName) {
        return productRepository.findByProductNameContaining(productName).stream()
                .map(ProductResponseDto::from)
                .collect(Collectors.toList());
    }

    public void updateAverageRating(Long productId) {
        Product product = productRepository.findByProductId(productId);
        List<Review> reviews = reviewRepository.findAllByProduct(product);
        if (reviews.isEmpty()) {
            return;
        }

        double sum = 0.0;
        for (Review review : reviews) {
            sum += review.getRating();
        }
        double averageRating = sum / reviews.size();
        BigDecimal avgRatingBigDecimal = BigDecimal.valueOf(averageRating).setScale(1, BigDecimal.ROUND_HALF_UP);

        product.setAvgRating(avgRatingBigDecimal);
        productRepository.save(product);
    }
}
