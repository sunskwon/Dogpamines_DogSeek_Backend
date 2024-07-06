package com.dogpamines.dogseek.auth.filter;

import com.dogpamines.dogseek.auth.model.DetailsUser;
import com.dogpamines.dogseek.auth.model.service.RefreshTokenService;
import com.dogpamines.dogseek.common.AuthConstants;
import com.dogpamines.dogseek.common.utils.TokenUtils;
import com.dogpamines.dogseek.user.model.dto.UserDTO;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final RefreshTokenService refreshTokenService;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager, RefreshTokenService refreshTokenService) {
        super.setAuthenticationManager(authenticationManager);
        this.refreshTokenService = refreshTokenService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authRequest;

        try {
            authRequest = getAuthRequest(request);
            setDetails(request, authRequest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private UsernamePasswordAuthenticationToken getAuthRequest(HttpServletRequest request) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);

        UserDTO user = objectMapper.readValue(request.getInputStream(), UserDTO.class);
        return new UsernamePasswordAuthenticationToken(user.getUserId(), user.getUserPass());
    }

}
