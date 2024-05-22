package com.efub.leadtoyproject.domain.reviewimg.dto;

import com.efub.leadtoyproject.domain.reviewimg.domain.ReviewImg;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewImgResponseDto {
    private Long id;
    private String originalName;
    private String storedName;
    private String imgPath;

    public ReviewImgResponseDto(ReviewImg reviewImg) {
        this.id = reviewImg.getId();
        this.originalName = reviewImg.getOriginalName();
        this.storedName = reviewImg.getStoredName();
        this.imgPath = reviewImg.getImgPath();
    }
}
