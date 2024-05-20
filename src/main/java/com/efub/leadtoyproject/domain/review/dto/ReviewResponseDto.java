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
    private List<ReviewImgResponseDto> reviewImgs;

    public static ReviewResponseDto from (Review review){
        List <ReviewImgResponseDto> imgDtos = review.getReviewImgs().stream()
                .map(ReviewImgResponseDto::from)
                .collect(Collectors.toList());

        return new ReviewResponseDto(
                review.getReviewId(),
                review.getContent(),
                review.getRating(),
                review.getCreatedDate(),
                review.getProduct().getProductId(),
                review.getMember().getMemberId(),
                imgDtos
                );
    }

}
