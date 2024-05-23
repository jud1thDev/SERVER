package com.efub.leadtoyproject.global.jwt;

import com.efub.leadtoyproject.global.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenException extends RuntimeException {
    private final ErrorCode errorCode;
}
