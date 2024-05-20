package com.efub.leadtoyproject.auth;

import com.efub.leadtoyproject.domain.review.repository.ReviewRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Value("${SECRET_KEY}")
    private String secretKey;

    @Autowired
    private ReviewRepository reviewRepository;

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getMemberIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }


    // 리뷰 삭제 권한 검사
    public boolean isAuthorizedToDeleteReview(Long reviewId, String token) {
        Long memberId = getMemberIdFromToken(token);
        return reviewRepository.findById(reviewId)
                .map(review -> review.getMember().getMemberId().equals(memberId))
                .orElse(false);
    }
}
