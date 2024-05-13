package com.efub.leadtoyproject.domain.cart.repository;

import com.efub.leadtoyproject.domain.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
