package com.dogpamines.dogseek.common.controller;

import com.dogpamines.dogseek.common.model.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> methodValidException(MethodArgumentNotValidException e) {

        String code = "";
        String description = "";
        String detail = "";

        /* 에러 발생 */
        if (e.getBindingResult().hasErrors()) {

            detail = e.getBindingResult().getFieldError().getDefaultMessage();
        }

        String bindResultCode = e.getBindingResult().getFieldError().getCode();

        switch (bindResultCode) {

            case "NotBlank" :
                code = "ERROR_CODE_0001";
                description = "필수 값이 누락되었습니다.";

            case "Pattern" :
                code = "ERROR_CODE_0002";
                description = "올바르지 않은 형태입니다.";
                break;
        }

        return new ResponseEntity<>(new ErrorDTO(code, description, detail), HttpStatus.BAD_REQUEST);
    }
}
