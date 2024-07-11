package com.dogpamines.dogseek.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErrorResponse {
    private HttpStatus status;
    private ErrorCode code;
    private String message;
    private LocalDateTime date = LocalDateTime.now();

    // 기본 생성자
    public ErrorResponse() {
    }

    // 생성자
    public ErrorResponse(HttpStatus status, ErrorCode code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.date = LocalDateTime.now();
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

    public LocalDateTime getDate() {
        return date;
    }

    // toString 메서드
    @Override
    public String toString() {
        return "ErrorResponse{" +
                "status=" + status +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", date=" + date +
                '}';
    }

    // Builder 패턴을 위한 내부 static 클래스
    public static class Builder {
        private HttpStatus status;
        private ErrorCode code;
        private String message;

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

    public static Builder builder() {
        return new Builder();
    }

    public static ResponseEntity<ErrorResponse> toErrorResponseEntity(ErrorCode code, String message) {
        ErrorResponse response = ErrorResponse.builder()
                .status(code.getStatus())
                .code(code)
                .message(message)
                .build();

        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
