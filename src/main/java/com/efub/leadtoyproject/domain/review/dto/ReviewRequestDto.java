package com.efub.leadtoyproject.domain.review.dto;

import com.efub.leadtoyproject.domain.member.domain.Member;
import com.efub.leadtoyproject.domain.product.domain.Product;
import com.efub.leadtoyproject.domain.review.domain.Review;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequestDto {
    @NotNull
    @Size(min=1, max=50)
    private String content;

    @NotNull
    private Integer rating;

    public Review toEntity(Member member, Product product){
        return Review.builder()
                .member(member)
                .product(product)
                .content(this.content)
                .rating(this.rating)
                .build();
    }

}
