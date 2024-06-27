package com.dogpamines.dogseek.auth.filter;

import com.dogpamines.dogseek.auth.model.DetailsUser;
import com.dogpamines.dogseek.common.AuthConstants;
import com.dogpamines.dogseek.common.UserRole;
import com.dogpamines.dogseek.common.utils.TokenUtils;
import com.dogpamines.dogseek.user.model.dto.UserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("doFilterInternal...1");

        // 권한 없이 접근 허용 url List
        List<String> roleLessList = Arrays.asList("/signup", "/redistest/count");

        if (roleLessList.contains(request.getRequestURI())) {
            chain.doFilter(request, response);
            return;
        }

        String header = request.getHeader(AuthConstants.AUTH_HEADER);
        System.out.println("header = " + header);

        try {
            if (header != null && !header.equalsIgnoreCase("")) {
                String token = TokenUtils.splitHeader(header);

                if (TokenUtils.isValidToken(token)) {

                    Claims claims = TokenUtils.getClaimsFromToken(token);

                    // 유저 정보 가져오기
                    DetailsUser authentication = new DetailsUser();
                    UserDTO user = new UserDTO();

                    user.setUserCode((Integer) claims.get("userCode"));
                    user.setUserNick(claims.get("userNick").toString());
                    user.setUserId(claims.get("userId").toString());
                    user.setUserAuth(UserRole.valueOf(claims.get("userAuth").toString()));
                    authentication.setUser(user);

                    AbstractAuthenticationToken authenticationToken
                            = UsernamePasswordAuthenticationToken
                            .authenticated(authentication, token, authentication.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                    chain.doFilter(request, response);

                } else {
                    System.out.println("Token이 유효하지 않다니까?");
                }
            } else {
                System.out.println("token이 유효하지 않아");
                chain.doFilter(request, response);
                return;
            }
        } catch (Exception e) {
            System.out.println("Token 검증 중 예외 발생: " + e.getMessage());
            response.setContentType("application/json");
            PrintWriter printWriter = response.getWriter();

            JSONObject jsonObject = jsonResponseWrapper(e);

            printWriter.flush();
            printWriter.close();
        }

        System.out.println("doFilterInternal...2");
    }

    private JSONObject jsonResponseWrapper(Exception e) {

        String resultMsg = "";

        if (e instanceof ExpiredJwtException) {
            resultMsg = "Token Expired";
        } else if (e instanceof SignatureException) {
            resultMsg = "Token SignatureException";
        } else if (e instanceof JwtException) {
            resultMsg = "Token Parsing JwtException";
        } else {
            resultMsg = "other Token error";
        }

        HashMap<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("status", 401);
        jsonMap.put("message", resultMsg);
        jsonMap.put("reason", e.getMessage());

        return new JSONObject(jsonMap);
    }
}
