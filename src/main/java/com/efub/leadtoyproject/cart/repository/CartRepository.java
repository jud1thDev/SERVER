package com.efub.leadtoyproject.cart.repository;

import com.efub.leadtoyproject.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
