package com.dogpamines.dogseek.auth.handler;

import com.dogpamines.dogseek.auth.model.DetailsUser;
import com.dogpamines.dogseek.auth.model.service.RefreshTokenService;
import com.dogpamines.dogseek.common.AuthConstants;
import com.dogpamines.dogseek.common.utils.TokenUtils;
import com.dogpamines.dogseek.user.model.dto.UserDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import java.io.IOException;

public class CustomAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final RefreshTokenService refreshTokenService;

    public CustomAuthSuccessHandler(RefreshTokenService refreshTokenService) {
        this.refreshTokenService = refreshTokenService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        DetailsUser detailsUser = (DetailsUser) authentication.getPrincipal();
        UserDTO user = detailsUser.getUser();

        String auth = String.valueOf(user.getUserAuth());
        System.out.println("auth = " + auth);

        if (auth.equals("USER") || auth.equals("ADMIN")) {
            String accessToken = TokenUtils.generateJwtToken(user);
            String refreshToken = TokenUtils.generateRefreshToken(user);

            refreshTokenService.saveRefreshToken(String.valueOf(user.getUserCode()), refreshToken, TokenUtils.refreshTokenValidateTime);

            response.addHeader(AuthConstants.AUTH_HEADER, AuthConstants.TOKEN_TYPE + " " + accessToken);
            response.addHeader("Refresh-Token", AuthConstants.TOKEN_TYPE + " " + refreshToken);

        } else if (auth.equals("SLEEP")) {
            response.setStatus(204);
//            response.addHeader("Result", String.valueOf(user.getUserCode()));
        } else {
            response.setStatus(403);
        }

    }
}
