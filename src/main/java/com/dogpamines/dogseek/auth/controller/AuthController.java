package com.dogpamines.dogseek.auth.controller;

import com.dogpamines.dogseek.auth.model.service.RefreshTokenService;
import com.dogpamines.dogseek.common.AuthConstants;
import com.dogpamines.dogseek.common.utils.TokenUtils;
import com.dogpamines.dogseek.user.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final RefreshTokenService refreshTokenService;

    @Autowired
    public AuthController(RefreshTokenService refreshTokenService) {
        this.refreshTokenService = refreshTokenService;
    }


    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestHeader(name = "Refresh-Token") String request) {
        String refreshToken = TokenUtils.splitHeader(request);
        String userCode = TokenUtils.getClaimsFromToken(refreshToken).get("userCode").toString();

        if (TokenUtils.isValidToken(refreshToken) && refreshToken.equals(refreshTokenService.getRefreshToken(userCode))) {
            UserDTO user = new UserDTO();
            user.setUserCode(Integer.parseInt(userCode));
            String newAccessToken = TokenUtils.generateJwtToken(user);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
            headers.set("Access-Control-Expose-Headers", AuthConstants.AUTH_HEADER);
            headers.set(AuthConstants.AUTH_HEADER, AuthConstants.TOKEN_TYPE + " " + newAccessToken);

            return new ResponseEntity<>(headers, HttpStatus.OK);
        }
        return ResponseEntity.status(403).body(null);
    }

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

