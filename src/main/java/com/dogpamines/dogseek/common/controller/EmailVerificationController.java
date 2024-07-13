package com.dogpamines.dogseek.common.controller;

import com.dogpamines.dogseek.common.model.service.EmailService;
import com.dogpamines.dogseek.common.model.service.VerificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Map;
@Tag(name = "Email 전송 Controller")
@RestController
@RequestMapping("/api/auth")
public class EmailVerificationController {

    private final EmailService emailService;
    private final VerificationService verificationService;
    private final SecureRandom random = new SecureRandom();

    public EmailVerificationController(EmailService emailService, VerificationService verificationService) {
        this.emailService = emailService;
        this.verificationService = verificationService;
    }
    @Operation(summary = "이메일 전송", description = "이메일의 형식과 빈문자열 확인 후 이메일을 전송한다.")
    @PostMapping("/send-verification-email")
    public ResponseEntity<String> sendVerificationEmail(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");
        String type = requestBody.get("type");

        if (email == null || email.isEmpty()) {
            return ResponseEntity.badRequest().body("Email is required");
        }
        if (type == null || type.isEmpty()) {
            return ResponseEntity.badRequest().body("Type is required");
        }

        try {
            String token = String.valueOf(100000 + random.nextInt(900000)); // 6자리 랜덤 숫자
            verificationService.createVerificationToken(email, token);

            String subject = switch (type) {
                case "signup" -> "회원가입";
                case "findId" -> "아이디 찾기";
                case "findPw" -> "비밀번호 찾기";
                case "sleep" -> "휴면계정 해제";
                default -> "이메일 인증번호";
            };

            emailService.sendEmail(email, subject, token);
            return ResponseEntity.ok("Verification email sent successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while sending the email");
        }
    }
    @Operation(summary = "인증번호 확인" ,description = "해당 이메일로 전송되었던 인증번호와 동일한지 확인한다.")
    @PostMapping("/verify-email")
    public ResponseEntity<Boolean> verifyEmail(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");
        String token = requestBody.get("token");

        if (email == null || email.isEmpty() || token == null || token.isEmpty()) {
            return ResponseEntity.badRequest().body(false);
        }

        boolean result = verificationService.validateVerificationToken(email, token);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        headers.set("Access-Control-Expose-Headers", "Result");

        headers.set("Result", String.valueOf(result));
        System.out.println("result = " + result);

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }
}
