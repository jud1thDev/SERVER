package com.efub.leadtoyproject.productimg.domain;

import com.efub.leadtoyproject.product.domain.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class ProductImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_img_id", updatable = false)
    private Long productImgId;

    @Column(name = "img_path", updatable = false)
    @NotNull
    private String imgPath;

    // FK
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", updatable = false)
    private Product product;
}
