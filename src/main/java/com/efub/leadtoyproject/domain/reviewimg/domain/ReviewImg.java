package com.efub.leadtoyproject.domain.reviewimg.domain;

import com.efub.leadtoyproject.domain.review.domain.Review;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class ReviewImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_img_id", updatable = false)
    private Long reviewImgId;

    @Column(name = "img_path", updatable = false)
    @NotNull
    private String imgPath;

    // FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", updatable = false)
    private Review review;
}
