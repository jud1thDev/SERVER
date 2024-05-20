package com.efub.leadtoyproject.domain.review.controller;

import com.efub.leadtoyproject.auth.AuthService;
import com.efub.leadtoyproject.domain.review.domain.Review;
import com.efub.leadtoyproject.domain.review.dto.ReviewRequestDto;
import com.efub.leadtoyproject.domain.review.dto.ReviewResponseDto;
import com.efub.leadtoyproject.domain.review.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ReviewController {

    private final ReviewService reviewService;
    private final AuthService authService;

    // 리뷰등록
    @PostMapping("/{productId}/reviews")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ReviewResponseDto registerReview(@PathVariable Long productId, @RequestBody @Valid final ReviewRequestDto requestDto) {
        Review registeredReview = reviewService.registerReview(requestDto);
        return ReviewResponseDto.from(registeredReview);
    }

    // 리뷰 전체 조회
    @GetMapping("/{productId}/reviews")
    public List<ReviewResponseDto> getAllReviews() {
        List<Review> reviews = reviewService.findAllReviews();
        return reviews.stream()
                .map(ReviewResponseDto::from)
                .collect(Collectors.toList());
    }


    // 리뷰 삭제
    @DeleteMapping("/reviews/{reviewId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReview(@PathVariable Long reviewId, @RequestHeader(value = "Authorization") String authorizationHeader) {
        if (!authService.validateToken(authorizationHeader)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        if (!reviewService.reviewExists(reviewId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (!authService.isAuthorizedToDeleteReview(reviewId, authorizationHeader)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        reviewService.deleteReview(reviewId);
    }


}
