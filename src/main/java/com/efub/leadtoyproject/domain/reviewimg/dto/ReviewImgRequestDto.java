package com.efub.leadtoyproject.domain.reviewimg.dto;

import com.efub.leadtoyproject.domain.member.domain.Member;
import com.efub.leadtoyproject.domain.product.domain.Product;
import com.efub.leadtoyproject.domain.review.domain.Review;
import com.efub.leadtoyproject.domain.reviewimg.domain.ReviewImg;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewImgRequestDto {
    @NotNull
    private String imgPath;

    public ReviewImg toEntity(Review review){
        return ReviewImg.builder()
                .review(review)
                .imgPath(this.imgPath)
                .build();
    }

}
