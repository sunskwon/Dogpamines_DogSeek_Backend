package com.dogpamines.dogseek.config;

import com.dogpamines.dogseek.auth.filter.CustomAuthenticationFilter;
import com.dogpamines.dogseek.auth.filter.JwtAuthorizationFilter;
import com.dogpamines.dogseek.auth.handler.CustomAuthFailureHandler;
import com.dogpamines.dogseek.auth.handler.CustomAuthSuccessHandler;
import com.dogpamines.dogseek.auth.handler.CustomAuthenticationProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    /* 정적 자원에 대한 이증된 사용자의 접근을 설정하는 메소드 */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    /* Security filter chain 설정 메소드 */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtAuthorizationFilter(), BasicAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(form -> form.disable())
                .addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .httpBasic(basic -> basic.disable());

        http.authorizeHttpRequests((auth) ->
                auth
//                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() // resources 접근 허용 설정
                        .requestMatchers("/**").permitAll() // 메인 페이지 요청 허가
                        .anyRequest().authenticated() // 그 외 모든 요청 허가 (인증으로 변경 필요)
        );

        http.cors((corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                CorsConfiguration corsConfiguration = new CorsConfiguration();

                corsConfiguration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
                corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
                corsConfiguration.setAllowCredentials(true);
                corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
                corsConfiguration.setMaxAge(3600L);

                // cors에 "Location" 항목 추가
                corsConfiguration.setExposedHeaders(Collections.singletonList("Location, Authorization"));


                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

//                source.registerCorsConfiguration()

                return corsConfiguration;
            }
        })));


        return http.build();
    }


    /* 사용자 요청(request) 시 수행되는 메소드 */
    private JwtAuthorizationFilter jwtAuthorizationFilter() {   // jwtAuthorizationFilter은 authenticationManager가 필요함
        return new JwtAuthorizationFilter(authenticationManager());
    }

    /* Authentication의 인증 메소드를 제공하는 매니저(= Provider의 인터페이스)를 반환하는 메소드 */
    @Bean
    public AuthenticationManager authenticationManager() {  // authenticationManager은 authenticationProvider가 필요함
        return new ProviderManager(customAuthenticationProvider()); // authenticationProvider는 제공되지만 custom 할 것
    }

    @Bean
    public CustomAuthenticationProvider customAuthenticationProvider() {
        return new CustomAuthenticationProvider();
    }

    /* 비밀번호를 암호화하는 인코더를 반환하는 메소드 */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /* 사용자의 인증 요청을 가로채서 로그인 로직을 수행하는 필터를 반환하는 메소드 */
    @Bean
    public CustomAuthenticationFilter customAuthenticationFilter() {

        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager());
        customAuthenticationFilter.setFilterProcessesUrl("/login");
        customAuthenticationFilter.setAuthenticationSuccessHandler(customAuthLoginSuccessHandler());
        customAuthenticationFilter.setAuthenticationFailureHandler(customAuthLoginFailureHandler());
        customAuthenticationFilter.afterPropertiesSet();
        return customAuthenticationFilter;
    }

    /* 사용자 정보가 맞을 경우 (=로그인 성공 시) 수행하는 핸들러를 반환하는 메소드 */
    private CustomAuthSuccessHandler customAuthLoginSuccessHandler() {
        return new CustomAuthSuccessHandler();
    }

    private CustomAuthFailureHandler customAuthLoginFailureHandler() {
        return new CustomAuthFailureHandler();
    }
}

