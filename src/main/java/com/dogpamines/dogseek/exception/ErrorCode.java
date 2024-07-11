package com.dogpamines.dogseek.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    /* 공통 */
    // 기본 - C0***
    RUNTIME_EXCEPTION(HttpStatus.BAD_REQUEST, "C0001", "런타임 예외"),
    METHOD_NOT_ALLOWED(HttpStatus.BAD_REQUEST, "C0002", "허용되지 않은 메소드"),

    // 캠페인 - C1***
    CAMPAIGN_NOT_FOUND(HttpStatus.NOT_FOUND, "C1002", "캠페인을 찾을 수 없음"),
    BAD_DATE_REQUEST(HttpStatus.BAD_REQUEST, "C1003", "잘못된 날짜 요청"),

    // 이미지 - C11**
    IMAGE_MISSING(HttpStatus.BAD_REQUEST, "C1101", "이미지 누락"),
    IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "C1102", "이미지를 찾을 수 없음"),
    IMAGE_SIZE_CHECK_FAILURE(HttpStatus.INTERNAL_SERVER_ERROR, "C1103", "이미지 크기 확인 실패"),
    IMAGE_UNSUPPORTED_EXTENSION(HttpStatus.BAD_REQUEST, "C1104", "지원되지 않는 확장자"),
    FILE_NAME_EMPTY(HttpStatus.BAD_REQUEST, "C1105", "파일 이름이 비어 있음"),
    FILE_UPLOAD_FAILURE(HttpStatus.INTERNAL_SERVER_ERROR, "C1106", "파일 업로드 실패"),
    IMAGE_UNSUPPORTED_FILETYPE(HttpStatus.BAD_REQUEST, "C1104", "지원되지 않는 파일 형식"),

    // 사용자 - C2***
    NICKNAME_DUPLICATE(HttpStatus.CONFLICT, "C2002", "중복된 닉네임 존재"),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "C2003", "회원 찾을 수 없음"),

    // 게시물 - C3***
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "C3001", "게시물을 찾을 수 없음"),

    // 배지 - C4***
    BADGE_NOT_FOUND(HttpStatus.NOT_FOUND, "C4001", "배지 찾을 수 없음"),
    PROFILE_NOT_FOUND(HttpStatus.NOT_FOUND, "C4002", "프로필 찾을 수 없음"),
    BADGE_INSTANCE_NOT_FOUND(HttpStatus.NOT_FOUND, "C4003", "배지 인스턴스를 찾을 수 없음"),

    // 토큰 - E1***
    TOKEN_VALIDATE_FAILURE(HttpStatus.BAD_REQUEST, "E1001", "유효하지 않은 토큰"),
    REFRESHTOKEN_NOT_FOUND(HttpStatus.BAD_REQUEST, "E1002", "리프레시 토큰을 찾을 수 없음");

    private final HttpStatus status;
    private final String code;
    private final String message;

    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
