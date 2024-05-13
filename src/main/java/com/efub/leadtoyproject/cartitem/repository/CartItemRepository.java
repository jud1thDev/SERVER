package com.efub.leadtoyproject.cartitem.repository;

import com.efub.leadtoyproject.cartitem.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
