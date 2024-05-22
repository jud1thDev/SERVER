package com.efub.leadtoyproject.domain.category.domain;

import lombok.Getter;

@Getter
public enum ProductType {
    BOOK("도서"),
    RECORD("음반 및 비디오"),
    GOODS("굿즈");

    private final String displayName;

    ProductType(String displayName) {
        this.displayName = displayName;
    }
}
