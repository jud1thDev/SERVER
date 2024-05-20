package com.efub.leadtoyproject.auth;

import com.efub.leadtoyproject.domain.review.repository.ReviewRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Service
public class AuthService {

    @Value("${jwt.publicKey}")
    private String publicKeyStr;

    @Value("${jwt.privateKey}")
    private String privateKeyStr;

//    @Autowired
//    private ResourceLoader resourceLoader;

    private PublicKey publicKey;
    private PrivateKey privateKey;

    @Autowired
    private ReviewRepository reviewRepository;

    @PostConstruct
    public void init() throws Exception {
        this.publicKey = loadPublicKey(publicKeyStr);
        this.privateKey = loadPrivateKey(privateKeyStr);
    }
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(publicKey).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getMemberIdFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
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

//    private PublicKey loadPublicKey(String path) throws Exception {
//        Resource resource = resourceLoader.getResource(path);
//        byte[] keyBytes = Files.readAllBytes(resource.getFile().toPath());
//        String publicKeyPEM = new String(keyBytes)
//                .replace("-----BEGIN PUBLIC KEY-----", "")
//                .replace("-----END PUBLIC KEY-----", "")
//                .replaceAll("\\s", "");
//        byte[] encoded = Base64.getDecoder().decode(publicKeyPEM);
//        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encoded);
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//        return keyFactory.generatePublic(keySpec);
//    }

//    private PrivateKey loadPrivateKey(String path) throws Exception {
//        Resource resource = resourceLoader.getResource(path);
//        byte[] keyBytes = Files.readAllBytes(resource.getFile().toPath());
//        String privateKeyPEM = new String(keyBytes)
//                .replace("-----BEGIN PRIVATE KEY-----", "")
//                .replace("-----END PRIVATE KEY-----", "")
//                .replaceAll("\\s", "");
//        byte[] encoded = Base64.getDecoder().decode(privateKeyPEM);
//        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//        return keyFactory.generatePrivate(keySpec);
//    }

    private PublicKey loadPublicKey(String key) throws Exception {
        String publicKeyPEM = key
                .replace("-----BEGIN RSA PUBLIC KEY-----", "")
                .replace("-----END RSA PUBLIC KEY-----", "")
                .replaceAll("\\s", "");
        byte[] encoded = Base64.getDecoder().decode(publicKeyPEM);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encoded);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    private PrivateKey loadPrivateKey(String key) throws Exception {
        String privateKeyPEM = key
                .replace("-----BEGIN RSA PRIVATE KEY-----", "")
                .replace("-----END RSA PRIVATE KEY-----", "")
                .replaceAll("\\s", "");
        byte[] encoded = Base64.getDecoder().decode(privateKeyPEM);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }


    public String createToken(Long memberId) {
        return Jwts.builder()
                .setSubject(String.valueOf(memberId))
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }
}
