package com.efub.leadtoyproject.domain.review.repository;

import com.efub.leadtoyproject.domain.product.domain.Product;
import com.efub.leadtoyproject.domain.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByProduct(Product product);
}