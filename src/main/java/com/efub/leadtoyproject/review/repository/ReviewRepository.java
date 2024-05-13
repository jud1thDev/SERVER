package com.efub.leadtoyproject.review.repository;

import com.efub.leadtoyproject.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}