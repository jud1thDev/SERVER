package com.efub.leadtoyproject.image;


import jakarta.persistence.*;
import lombok.*;

import java.util.Arrays;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public abstract class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String originalName;
    protected String storedName;
    protected String imgPath;

    private final static String[] extensionArr={"jpg","jpeg","bmp","gif","png"};
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    // 이미지 파일의 확장자를 추출하는 메서드
    public String extractExtension(String originalName) {
        int index = originalName.lastIndexOf('.');
        return originalName.substring(index + 1);
    }

    // 저장할 파일 이름을 생성하는 메서드
    public String generateStoreName(String originalName) {
        String extension = extractExtension(originalName);
        if (!checkValidation(extension))
            throw new RuntimeException(extension + " 은 지원하지 않는 확장자입니다.");
        return UUID.randomUUID() + "." + extension;
    }

    public boolean checkValidation(String extension) {
        return Arrays.stream(extensionArr).anyMatch(value -> value.equals(extension));
    }

}

