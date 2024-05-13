package com.efub.leadtoyproject.domain.product.domain;

import com.efub.leadtoyproject.domain.category.domain.Category;
import com.efub.leadtoyproject.domain.item.domain.Item;
import com.efub.leadtoyproject.domain.productimg.domain.ProductImg;
import com.efub.leadtoyproject.domain.review.domain.Review;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", updatable = false)
    private Long productId;

    @Column(name = "product_name")
    @NotNull
    private String productName;

    @Column(name = "original_price")
    @NotNull
    private Long originalPrice;

    @Column(name = "info")
    private String info;

    @Column(name = "stock_quantity")
    @Builder.Default
    private Long stockQuantity = 0L;

    @Column(name = "avg_rating", precision = 2, scale = 1)
    @Builder.Default
    private BigDecimal avgRating = BigDecimal.valueOf(0.0);

    // FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    // 양방향
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private ProductImg productImg; // 영속성 추후 확인 필요

    // 양방향
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Review> reviews = new ArrayList<>();

    // 양방향
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Item> items = new ArrayList<>();
}