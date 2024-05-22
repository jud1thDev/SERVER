package com.efub.leadtoyproject.domain.member.domain;

import com.efub.leadtoyproject.domain.cart.domain.Cart;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", updatable = false)
    private Long memberId;

    @Column(name = "email")
    @NotBlank
    private String email;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "refresh_token")
    private String refreshToken;

    // 양방향
    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;

    @Builder
    public Member(Long memberId, @NotBlank String email, String accessToken, String refreshToken) {
        this.memberId = memberId;
        this.email = email;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.cart = new Cart(this);
    }
}
