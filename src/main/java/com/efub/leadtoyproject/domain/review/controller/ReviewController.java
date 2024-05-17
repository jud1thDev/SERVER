package com.efub.leadtoyproject.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ReviewController {

    @PostMapping
    public ResponseEntity<reviewResponseDto>
}
