package com.efub.leadtoyproject.domain.review.service;

import com.efub.leadtoyproject.domain.member.domain.Member;
import com.efub.leadtoyproject.domain.member.service.MemberService;
import com.efub.leadtoyproject.domain.product.domain.Product;
import com.efub.leadtoyproject.domain.product.service.ProductService;
import com.efub.leadtoyproject.domain.review.domain.Review;
import com.efub.leadtoyproject.domain.review.dto.ReviewRequestDto;
import com.efub.leadtoyproject.domain.review.repository.ReviewRepository;
import com.efub.leadtoyproject.domain.reviewimg.domain.ReviewImg;
import com.efub.leadtoyproject.domain.reviewimg.dto.ReviewImgResponseDto;
import com.efub.leadtoyproject.domain.reviewimg.repository.ReviewImgRepository;
import com.efub.leadtoyproject.image.ImgService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductService productService;
    private final MemberService memberService;
    private final ImgService imgService;
    private final ReviewImgRepository reviewImgRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, ProductService productService, MemberService memberService, ImgService imgService, ReviewImgRepository reviewImgRepository) {
        this.reviewRepository = reviewRepository;
        this.productService = productService;
        this.memberService = memberService;
        this.imgService = imgService;
        this.reviewImgRepository = reviewImgRepository;
    }

    public Review registerReview(Long productId, ReviewRequestDto dto, List<MultipartFile> files) throws IOException {
        Product product = productService.findProductById(productId);
        Member member = memberService.findMemberById(dto.getMemberId());

        Review review = dto.toEntity(product, member);
        reviewRepository.save(review);

        for (MultipartFile multipartFile : files) {
            if (multipartFile != null && !multipartFile.isEmpty()) {
                String originalName = multipartFile.getOriginalFilename();
                String storedName = UUID.randomUUID() + "-" + originalName;
                String imgPath = imgService.saveImage(multipartFile, storedName);

                ReviewImg reviewImg = ReviewImg.builder()
                        .originalName(originalName)
                        .storedName(storedName)
                        .imgPath(imgPath)
                        .review(review)
                        .build();

                review.addReviewImage(reviewImg);
                reviewImgRepository.save(reviewImg);
            }
        }

        return review;
    }

    public ReviewImgResponseDto saveReviewImage(Long reviewId, MultipartFile file) throws IOException {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("리뷰를 찾을 수 없습니다."));

        String originalName = file.getOriginalFilename();
        String storedName = UUID.randomUUID() + "-" + originalName;
        String imgPath = imgService.saveImage(file, storedName);

        ReviewImg reviewImg = ReviewImg.builder()
                .originalName(originalName)
                .storedName(storedName)
                .imgPath(imgPath)
                .review(review)
                .build();

        reviewImgRepository.save(reviewImg);

        return new ReviewImgResponseDto(reviewImg);
    }

    @Transactional(readOnly = true)
    public List<Review> findAllReviews(Long productId) {
        Product product = productService.findProductById(productId);
        return reviewRepository.findAllByProduct(product);
    }

    @Transactional(readOnly = true)
    public boolean reviewExists(Long reviewId) {
        return reviewRepository.existsById(reviewId);
    }

    public void deleteReview(Long reviewId) {
        Review review = findReviewById(reviewId);
        reviewRepository.delete(review);
    }

    @Transactional(readOnly = true)
    public Review findReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰가 없습니다. id=" + reviewId));
    }

}
