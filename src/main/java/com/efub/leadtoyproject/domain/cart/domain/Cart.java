package com.efub.leadtoyproject.domain.cart.domain;


import com.efub.leadtoyproject.domain.cartitem.domain.CartItem;
import com.efub.leadtoyproject.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

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
    @Builder.Default
    private Long count = 0L;

    @Column(name = "total_price")
    @Builder.Default
    private Long totalPrice = 0L;

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
    }
}
