package com.efub.leadtoyproject.domain.review.service;

import com.efub.leadtoyproject.domain.member.domain.Member;
import com.efub.leadtoyproject.domain.member.service.MemberService;
import com.efub.leadtoyproject.domain.product.domain.Product;
import com.efub.leadtoyproject.domain.product.service.ProductService;
import com.efub.leadtoyproject.domain.review.domain.Review;
import com.efub.leadtoyproject.domain.review.dto.ReviewRequestDto;
import com.efub.leadtoyproject.domain.review.repository.ReviewRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private MemberService memberService;

    public Review registerReview(Long productId, ReviewRequestDto dto) {
        Product product = productService.findProductById(productId);
        Member member = memberService.findMemberById(dto.getMemberId());

        Review review = dto.toEntity(product, member);
        reviewRepository.save(review);

        return review;
    }

    @Transactional(readOnly = true)
    public List<Review> findAllReviews(Long productId) {
        Product product = productService.findProductById(productId);
        return reviewRepository.findAllByProduct(product);
    }

    @Transactional(readOnly = true)
    public boolean reviewExists(Long reviewId) {
        return reviewRepository.existsById(reviewId);
    }

    public void deleteReview(Long reviewId) {
        Review review = findReviewById(reviewId);
        reviewRepository.delete(review);
    }

    @Transactional(readOnly = true)
    public Review findReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰가 없습니다. id=" + reviewId));
    }

}
