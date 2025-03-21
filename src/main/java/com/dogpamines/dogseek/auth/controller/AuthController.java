package com.dogpamines.dogseek.auth.controller;

import com.dogpamines.dogseek.auth.model.service.RefreshTokenService;
import com.dogpamines.dogseek.common.AuthConstants;
import com.dogpamines.dogseek.common.utils.TokenUtils;
import com.dogpamines.dogseek.user.model.dto.UserDTO;
import com.dogpamines.dogseek.user.model.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
@Tag(name = "Token(토큰) Controller")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final RefreshTokenService refreshTokenService;
    private UserService userService;

    @Autowired
    public AuthController(RefreshTokenService refreshTokenService, UserService userService) {
        this.refreshTokenService = refreshTokenService;
        this.userService = userService;
    }

    @Operation(summary = "토큰 재발급", description = "사용자에게 토큰을 재발급 한다.")
    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestHeader(name = "Refresh-Token") String request) {
        String refreshToken = TokenUtils.splitHeader(request);
        String userCode = TokenUtils.getClaimsFromToken(refreshToken).get("userCode").toString();

        if (TokenUtils.isValidToken(refreshToken)
                && (refreshToken.equals(refreshTokenService.getRefreshToken(userCode)) || refreshToken.equals(userService.selectRefreshToken(Integer.parseInt(userCode))))) {
            System.out.println("디비토큰 boolean : " + refreshToken.equals(userService.selectRefreshToken(Integer.parseInt(userCode))));
            UserDTO user = userService.selectUserByCode(Integer.parseInt(userCode));
            user.setUserCode(Integer.parseInt(userCode));
            System.out.println("user = " + user);
            String newAccessToken = TokenUtils.generateJwtToken(user);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

            headers.set(AuthConstants.AUTH_HEADER, AuthConstants.TOKEN_TYPE + " " + newAccessToken);

            return new ResponseEntity<>(headers, HttpStatus.OK);
        } else {
            return ResponseEntity.status(403).body(null);
        }
    }
    @Operation(summary = "로그아웃", description = "회원이 로그아웃 시 토큰을 지운다.")
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            @RequestHeader(name = "Authorization") String authToken,
            @RequestHeader(name = "Refresh-Token") String refreshToken) {

        String token = TokenUtils.splitHeader(refreshToken);

        try {

            String userCode = TokenUtils.getClaimsFromToken(token).get("userCode").toString();
            System.out.println("userCode = " + userCode);

            refreshTokenService.deleteRefreshToken(userCode);

            return ResponseEntity.ok().build();
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

