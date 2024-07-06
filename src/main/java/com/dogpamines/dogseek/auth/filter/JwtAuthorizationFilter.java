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

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private TokenUtils tokenUtils;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, TokenUtils tokenUtils) {
        super(authenticationManager);
        this.tokenUtils = tokenUtils;
    }

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        // 권한 없이 접근 허용 url List
        List<String> roleLessList = Arrays.asList("/signup", "/redistest/count", "/user/check", "/products/search", "/products", "/products/comparison", "/lastProds", "/user/change/pwd", "/auth/refresh",
                "/api/auth/send-verification-email", "/api/auth/verify-email", "/products/mostProducts", "/mycurationresult", "/animalRegist", "/dict", "/user/match/phone", "/user/find/email", "/boards/**", "/boards", "/mypage", "/mypage/check");

        if (roleLessList.contains(request.getRequestURI())) {
            chain.doFilter(request, response);
            return;
        }

        String header = request.getHeader(AuthConstants.AUTH_HEADER);

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
                    user.setUserAuth(UserRole.valueOf(claims.get("userAuth").toString()));
                    authentication.setUser(user);

                    AbstractAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authentication, null, authentication.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                    chain.doFilter(request, response);

                } else {
                    throw new JwtException("Invalid token.");
                }
            } else {
                throw new JwtException("Missing token.");
            }
        } catch (JwtException e) {
            handleJwtException(response, e);
        }
    }

    private void handleJwtException(HttpServletResponse response, JwtException e) throws IOException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        jsonResponse.put("message", "Unauthorized");
        jsonResponse.put("reason", e.getMessage());

        PrintWriter writer = response.getWriter();
        writer.println(jsonResponse.toJSONString());
        writer.flush();
        writer.close();
    }
}
