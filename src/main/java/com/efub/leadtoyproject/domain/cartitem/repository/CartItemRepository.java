package com.efub.leadtoyproject.domain.cartitem.repository;

import com.efub.leadtoyproject.domain.cartitem.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
