package com.efub.leadtoyproject.product.repository;

import com.efub.leadtoyproject.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
