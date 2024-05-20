package com.efub.leadtoyproject.domain.review.repository;

import com.efub.leadtoyproject.domain.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}