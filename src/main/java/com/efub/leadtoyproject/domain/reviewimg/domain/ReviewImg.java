package com.efub.leadtoyproject.domain.reviewimg.domain;

import com.efub.leadtoyproject.image.Image;
import com.efub.leadtoyproject.domain.review.domain.Review;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class ReviewImg extends Image {
    // FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Review review;

    @Builder
    public ReviewImg(String originalName, String storedName, String imgPath, Review review) {
        this.originalName = originalName;
        this.storedName = storedName;
        this.imgPath = imgPath;
        this.review = review;
    }

    public ReviewImg(MultipartFile file) {
        this.originalName = file.getOriginalFilename();
        this.storedName = generateStoreName(originalName);
        this.imgPath = "";
    }

    // 초기화
    public void initReview(Review review) {
        if (this.review == null) {
            this.review = review;
        }
    }

    public void setReview(Review review) {
        this.review = review;
    }
}
