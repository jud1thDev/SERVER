package com.efub.leadtoyproject.domain.cart.domain;


import com.efub.leadtoyproject.domain.cartitem.domain.CartItem;
import com.efub.leadtoyproject.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id", updatable = false)
    private Long cartId;

    @Column(name = "count")
    private Long count;

    @Column(name = "total_price")
    private Long totalPrice;

    // FK
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", updatable = false)
    private Member member;

    // 양방향
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems;

    @Builder
    public Cart(Member member) {
        this.member = member;
        this.cartItems = new ArrayList<>();
        this.count = 0L;
        this.totalPrice = 0L;
    }
}