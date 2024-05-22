package com.efub.leadtoyproject.domain.review.dto;

import com.efub.leadtoyproject.domain.review.domain.Review;
import com.efub.leadtoyproject.domain.reviewimg.dto.ReviewImgResponseDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDto {
    private Long reviewId;
    private String content;
    private Integer rating;
    private LocalDateTime createdDate;
    private Long productId; // product필드 넣을지 고민
    private Long memberId;
    private List<String> imgPaths;

    public ReviewResponseDto(Review review) {
        this.reviewId = review.getReviewId();
        this.content = review.getContent();
        this.rating = review.getRating();
        this.createdDate = review.getCreatedDate();
        this.productId = review.getProduct().getProductId();
        this.memberId = review.getMember().getMemberId();
        this.imgPaths = review.getReviewImages().stream()
                .map(reviewImg -> reviewImg.getImgPath())
                .collect(Collectors.toList());
    }

    public static ReviewResponseDto from(Review review) {
        return new ReviewResponseDto(review);
    }

}
