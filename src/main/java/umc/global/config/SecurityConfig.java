package umc.global.config;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import umc.global.security.exception.CustomAccessDenied;
import umc.global.security.exception.CustomEntryPoint;
import umc.global.security.exception.SecurityErrorResponseWriter;
import umc.global.security.filter.JwtAuthFilter;
import umc.global.security.handler.OAuthSuccessHandler;
import umc.global.security.service.CustomOAuthService;
import umc.global.security.service.CustomUserDetailsService;
import umc.global.security.util.JwtUtil;


@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomAccessDenied customAccessDenied;
    private final CustomEntryPoint customEntryPoint;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;
    private final SecurityErrorResponseWriter securityErrorResponseWriter;
    private final OAuthSuccessHandler oAuthSuccessHandler;

    private final String[] allowUris = {
            // Swagger 허용
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",

            "/public/**",
            "/oauth/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomOAuthService customOAuthService) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests
                        // public API 허용
                        .requestMatchers(allowUris).permitAll()
                        // 그 이외 API는 인증 필요
                        .anyRequest().authenticated()
                )
                // 폼 로그인
                .formLogin(form -> form
                        .defaultSuccessUrl("/swagger-ui/index.html", true)
                        .permitAll()
                )
                // JWT 필터
                .addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class)
                // 세션
                .sessionManagement(
                        AbstractHttpConfigurer::disable
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                // 예외 상황 핸들러
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(customAccessDenied)
                        .authenticationEntryPoint(customEntryPoint) // 전역 설정
                )
                // OAuth
                .oauth2Login(oauth -> oauth
                        .authorizationEndpoint(auth -> auth
                                .baseUri("/oauth/authorize")
                        )
                        .redirectionEndpoint(redirect -> redirect
                                .baseUri("/oauth/callback/**")
                        )
                        // 인증 완료 후 정보 활용
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuthService)
                        )
                        // 성공 시 JWT 토큰 발행할 핸들러
                        .successHandler(oAuthSuccessHandler)
                )
        ;

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthFilter jwtAuthFilter(){
        return new JwtAuthFilter(jwtUtil, customUserDetailsService, securityErrorResponseWriter);
    }

}
