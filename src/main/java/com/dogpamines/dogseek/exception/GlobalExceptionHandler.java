package com.dogpamines.dogseek.exception;

import java.time.DateTimeException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /*================== Basic Exception ==================*/
    @ExceptionHandler(RuntimeException.class)
    protected final ResponseEntity<ErrorResponse> handleRunTimeException(RuntimeException e) {
        final ErrorResponse response = new ErrorResponse.Builder()
                .status(HttpStatus.BAD_REQUEST)
                .code(ErrorCode.RUNTIME_EXCEPTION)
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(DateTimeException.class)
    protected final ResponseEntity<ErrorResponse> handleDateTimeException(DateTimeException e) {
        final ErrorResponse response = new ErrorResponse.Builder()
                .status(HttpStatus.BAD_REQUEST)
                .code(ErrorCode.BAD_DATE_REQUEST)
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    /**
     * 지원하지 않은 HTTP method 호출 할 경우 발생
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException", e);
        return ErrorResponse.toErrorResponseEntity(ErrorCode.METHOD_NOT_ALLOWED, e.getMessage());
    }

    // vaild 오류
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

}
