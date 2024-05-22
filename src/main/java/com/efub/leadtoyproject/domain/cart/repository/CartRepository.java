package com.efub.leadtoyproject.domain.cart.repository;

import com.efub.leadtoyproject.domain.cart.domain.Cart;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query("SELECT c FROM Cart c LEFT JOIN FETCH c.cartItems WHERE c.member.memberId = :memberId")
    Optional<Cart> findCartByMemberId(Long memberId);
}