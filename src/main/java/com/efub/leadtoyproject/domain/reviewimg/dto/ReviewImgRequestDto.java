package com.efub.leadtoyproject.domain.reviewimg.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewImgRequestDto {
    private Long reviewId;
    private MultipartFile file;

}
