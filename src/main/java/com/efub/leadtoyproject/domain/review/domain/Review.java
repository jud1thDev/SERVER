package com.efub.leadtoyproject.domain.review.domain;

import com.efub.leadtoyproject.domain.member.domain.Member;
import com.efub.leadtoyproject.domain.product.domain.Product;
import com.efub.leadtoyproject.domain.reviewimg.domain.ReviewImg;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", updatable = false)
    private Long reviewId;

    @Column(name = "content")
    @NotNull
    private String content;

    @Column(name = "created_date", updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "rating")
    @Min(value = 1) @Max(value = 5)
    @NotNull
    private Integer rating; // 별점: 1~5 사이의 값

    // FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", updatable = false)
    private Product product;

    // FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", updatable = false)
    private Member member; // Member 쪽에 단방향으로 구현

    // 양방향
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ReviewImg> reviewImgs = new ArrayList<>();
}
