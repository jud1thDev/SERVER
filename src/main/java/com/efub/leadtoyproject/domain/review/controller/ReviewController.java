package com.efub.leadtoyproject.domain.review.controller;

import com.efub.leadtoyproject.auth.AuthService;
import com.efub.leadtoyproject.domain.review.domain.Review;
import com.efub.leadtoyproject.domain.review.dto.ReviewRequestDto;
import com.efub.leadtoyproject.domain.review.dto.ReviewResponseDto;
import com.efub.leadtoyproject.domain.review.service.ReviewService;
import com.efub.leadtoyproject.domain.reviewimg.dto.ReviewImgResponseDto;
import com.efub.leadtoyproject.image.ImgService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
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
    public ReviewResponseDto registerReview(@PathVariable("productId") final Long productId,
                                            @RequestPart("review")  final String reviewJson,
                                            @RequestPart("files") List<MultipartFile> files) throws IOException {

        // JSON 문자열을 ReviewRequestDto 객체로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        ReviewRequestDto requestDto = objectMapper.readValue(reviewJson, ReviewRequestDto.class);

        Review registeredReview = reviewService.registerReview(productId, requestDto, files);
        return ReviewResponseDto.from(registeredReview);
    }

    // 이미지 업로드
    @PostMapping("/reviews/{reviewId}/images")
    public ReviewImgResponseDto uploadReviewImage(
            @PathVariable Long reviewId,
            @RequestParam("file") MultipartFile file) throws IOException {
        return reviewService.saveReviewImage(reviewId, file);
    }

    // 리뷰 전체 조회
    @GetMapping("/{productId}/reviews")
    public List<ReviewResponseDto> getAllReviews(@PathVariable("productId") final Long productId) {
        List<Review> reviews = reviewService.findAllReviews(productId);
        return reviews.stream()
                .map(ReviewResponseDto::from)
                .collect(Collectors.toList());
    }


    // 리뷰 삭제
    @DeleteMapping("/reviews/{reviewId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReview(@PathVariable("reviewId") Long reviewId, @RequestHeader(value = "Authorization") String authorizationHeader) {
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
