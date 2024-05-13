package com.efub.leadtoyproject.category.domain;

import com.efub.leadtoyproject.product.domain.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", updatable = false)
    private Long categoryId;

    @Column(name = "category_name")
    @NotNull
    private String categoryName;

    @Column(name = "product_type", updatable = false)
    @NotNull
    private ProductType productType;

    // 양방향
    @OneToMany(mappedBy = "product_id")
    @Builder.Default
    private List<Product> products = new ArrayList<>();
}
