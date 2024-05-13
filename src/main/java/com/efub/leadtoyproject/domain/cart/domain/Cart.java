package com.efub.leadtoyproject.domain.cart.domain;


import com.efub.leadtoyproject.domain.cartitem.domain.CartItem;
import com.efub.leadtoyproject.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor // Member 만들 때 함께 생성되어야 함
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
    @Builder.Default
    private List<CartItem> cartItems = new ArrayList<>();
}
