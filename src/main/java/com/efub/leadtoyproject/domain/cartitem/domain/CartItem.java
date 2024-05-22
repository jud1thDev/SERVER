package com.efub.leadtoyproject.domain.cartitem.domain;

import com.efub.leadtoyproject.domain.cart.domain.Cart;
import com.efub.leadtoyproject.domain.item.domain.Item;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id", updatable = false)
    private Long cartItemId;

    // FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", updatable = false)
    private Cart cart;
    // FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", updatable = false)
    private Item item;

    public CartItem(Cart cart, Item item) {
        this.cart = cart;
        this.item = item;
    }
}
