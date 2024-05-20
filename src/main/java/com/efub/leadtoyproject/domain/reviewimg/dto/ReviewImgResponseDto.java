package com.efub.leadtoyproject.domain.reviewimg.dto;

import com.efub.leadtoyproject.domain.reviewimg.domain.ReviewImg;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewImgResponseDto {
    private Long reviewImgId;
    private String imgPath;

    public static ReviewImgResponseDto from(ReviewImg reviewImg){
        return new ReviewImgResponseDto(
                reviewImg.getReviewImgId(),
                reviewImg.getImgPath()
        );
    }
}
