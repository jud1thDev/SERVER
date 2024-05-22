package com.efub.leadtoyproject.domain.review.dto;

import com.efub.leadtoyproject.domain.member.domain.Member;
import com.efub.leadtoyproject.domain.product.domain.Product;
import com.efub.leadtoyproject.domain.review.domain.Review;
import com.efub.leadtoyproject.domain.reviewimg.domain.ReviewImg;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequestDto {
    private String content;
    private Integer rating;
    private Long productId;

    public Review toEntity(Product product, Member member) {
        return Review.builder()
                .content(this.content)
                .rating(this.rating)
                .product(product)
                .member(member)
                .build();
    }

}
