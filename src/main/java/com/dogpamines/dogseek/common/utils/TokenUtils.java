package com.dogpamines.dogseek.common.utils;

import com.dogpamines.dogseek.user.model.dto.UserDTO;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtils {

    private static String jwtSecretKey;
    private static Long tokenValidateTime;
    public static Long refreshTokenValidateTime;

    @Value("${jwt.key}")
    public void setJwtSecretKey(String jwtSecretKey) {
        TokenUtils.jwtSecretKey = jwtSecretKey;
    }

    @Value("${jwt.time}")
    public void setTokenValidateTime(Long tokenValidateTime) {
        TokenUtils.tokenValidateTime = tokenValidateTime;
    }

    @Value("${jwt.refreshTime}")
    public void setRefreshTokenValidateTime(Long refreshTokenValidateTime) {
        TokenUtils.refreshTokenValidateTime = refreshTokenValidateTime;
    }

    public static String splitHeader(String header) {
        if (!header.equals("")) {
            return header.split(" ")[1];
        } else {
            return null;
        }
    }

    public static boolean isValidToken(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            return true;
        } catch (JwtException | NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (ExpiredJwtException e) {
            return true; // 토큰이 만료된 경우
        } catch (JwtException | NullPointerException e) {
            e.printStackTrace();
            return true; // 예외 발생 시도 토큰을 만료된 것으로 처리
        }
    }

    public static String getRefreshTokenFromClaims(Claims claims) {
        return (String) claims.get("refreshToken");
    }


    public static Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(jwtSecretKey))
                .parseClaimsJws(token)
                .getBody();
    }

    public static String generateJwtToken(UserDTO user) {
        Date expireTime = new Date(System.currentTimeMillis() + tokenValidateTime);
        JwtBuilder builder = Jwts.builder()
                .setHeader(createHeader())
                .setClaims(createClaims(user))
                .setSubject("Dogpamines token : " + user.getUserCode())
                .signWith(SignatureAlgorithm.HS256, createSignature())
                .setExpiration(expireTime);
        return builder.compact();
    }

    public static String generateRefreshToken(UserDTO user) {
        Date expireTime = new Date(System.currentTimeMillis() + refreshTokenValidateTime);
        JwtBuilder builder = Jwts.builder()
                .setHeader(createHeader())
                .setClaims(createClaims(user))
                .setSubject("Dogpamines refresh token : " + user.getUserCode())
                .signWith(SignatureAlgorithm.HS256, createSignature())
                .setExpiration(expireTime);
        return builder.compact();
    }

    private static Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();
        header.put("type", "jwt");
        header.put("alg", "HS256");
        header.put("date", System.currentTimeMillis());
        return header;
    }

    private static Map<String, Object> createClaims(UserDTO user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userCode", user.getUserCode());
        claims.put("userNick", user.getUserNick());
        claims.put("userAuth", user.getUserAuth());
        return claims;
    }

    private static Key createSignature() {
        byte[] secretBytes = DatatypeConverter.parseBase64Binary(jwtSecretKey);
        return new SecretKeySpec(secretBytes, SignatureAlgorithm.HS256.getJcaName());
    }
}
