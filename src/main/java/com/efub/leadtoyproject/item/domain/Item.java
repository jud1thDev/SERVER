package com.efub.leadtoyproject.item.domain;

import com.efub.leadtoyproject.product.domain.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id", updatable = false)
    private Long itemId;

    @Column(name = "price")
    @NotNull
    private Long price;

    @Column(name = "status")
    @NotNull
    private ItemStatus status;

    @Column(name = "store")
    @NotNull
    private String store;

    @Column(name = "location")
    @NotNull
    private String location;

    // FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product productId;
}
