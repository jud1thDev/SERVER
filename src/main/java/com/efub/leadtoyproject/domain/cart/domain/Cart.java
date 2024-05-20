package com.efub.leadtoyproject.domain.cart.domain;


import com.efub.leadtoyproject.domain.cartitem.domain.CartItem;
import com.efub.leadtoyproject.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", updatable = false)
    private Member member;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<CartItem> cartItems = new ArrayList<>();
}
