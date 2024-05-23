package com.efub.leadtoyproject.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // 도메인
    // 타입(상태 코드, "메시지");

    // Default
    ERROR(400, "요청 처리에 실패했습니다."),
    ENTITY_NOT_FOUND(404, "해당 엔티티를 찾을 수 없습니다."),

    // Oauth2, JWT
    ILLEGAL_REGISTRATION_ID(500, "잘못된 registrationId입니다."),
    INVALID_TOKEN(401, "잘못된 토큰입니다."),
    NO_AUTHORIZATION(401, "인증 정보를 가지고 있지 않습니다."),
    INVALID_JWT_SIGNATURE(401, "토큰이 만료되었습니다."),
    // Member
    MEMBER_NOT_FOUND(404, "해당 사용자를 찾을 수 없습니다."),

    ;

    private final int status;
    private final String message;
}
