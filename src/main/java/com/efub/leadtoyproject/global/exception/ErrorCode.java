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

    // Member
    ENTITY_NOT_FOUND(404, "요청하신 ID와 일치하는 객체가 존재하지 않습니다."),
    PERMISSION_REJECTED_USER(403, "삭제 권한이 없는 사용자입니다.")
    ;

    private final int status;
    private final String message;
}
