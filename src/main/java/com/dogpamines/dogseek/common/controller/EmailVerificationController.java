package com.dogpamines.dogseek.common.controller;

import com.dogpamines.dogseek.common.model.service.EmailService;
import com.dogpamines.dogseek.common.model.service.VerificationService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Map;

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

    @PostMapping("/send-verification-email")
    public ResponseEntity<String> sendVerificationEmail(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");
        String token = String.valueOf(100000 + random.nextInt(900000)); // 6자리 랜덤 숫자
        verificationService.createVerificationToken(email, token);
        emailService.sendEmail(email, "이메일 인증번호", "인증번호는 " + token + " 입니다.");
        return ResponseEntity.ok("Verification email sent successfully");
    }

    @PostMapping("/verify-email")
    public ResponseEntity<Boolean> verifyEmail(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");
        String token = requestBody.get("token");
        boolean result = verificationService.validateVerificationToken(email, token);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        headers.set("Access-Control-Expose-Headers", "Result");

        headers.set("Result", String.valueOf(result));
        System.out.println("result = " + result);

        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}


