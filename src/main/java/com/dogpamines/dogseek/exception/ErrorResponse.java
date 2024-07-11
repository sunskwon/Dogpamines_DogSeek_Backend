package com.dogpamines.dogseek.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErrorResponse {
    private HttpStatus status;  // HTTP 상태 코드
    private ErrorCode code;     // 에러 코드
    private String message;     // 에러 메시지
    private String date;        // 에러 발생 시간 문자열 (포맷팅된 문자열)

    // 기본 생성자
    public ErrorResponse() {
        this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    // 생성자
    public ErrorResponse(HttpStatus status, ErrorCode code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    // Getter 메서드
    public HttpStatus getStatus() {
        return status;
    }

    public ErrorCode getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }

    // toString 메서드
    @Override
    public String toString() {
        return "ErrorResponse{" +
                "status=" + status +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    // Builder 패턴을 위한 내부 static 클래스
    public static class Builder {
        private HttpStatus status;  // HTTP 상태 코드
        private ErrorCode code;     // 에러 코드
        private String message;     // 에러 메시지

        public Builder status(HttpStatus status) {
            this.status = status;
            return this;
        }

        public Builder code(ErrorCode code) {
            this.code = code;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public ErrorResponse build() {
            return new ErrorResponse(status, code, message);
        }
    }

    // 빌더 인스턴스 반환 메서드
    public static Builder builder() {
        return new Builder();
    }

    // ErrorResponse를 ResponseEntity로 변환하는 메서드
    public static ResponseEntity<ErrorResponse> toErrorResponseEntity(ErrorCode code, String message) {
        ErrorResponse response = ErrorResponse.builder()
                .status(code.getStatus())
                .code(code)
                .message(message)
                .build();

        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
