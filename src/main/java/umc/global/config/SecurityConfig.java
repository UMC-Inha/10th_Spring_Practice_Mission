package umc.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import umc.global.security.filter.JwtAuthFilter;
import umc.global.security.handler.CustomAccessDenied;
import umc.global.security.handler.CustomEntryPoint;
import umc.global.security.service.CustomUserDetailsService;
import umc.global.security.util.JwtUtil;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor  // 추가
public class SecurityConfig {

    // 추가
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    private final String[] allowUris = {
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",

            // 로그인, 회원가입은 인증 없이 가능
            "/api/members/signup",
            "/api/members/login"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(allowUris).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(AbstractHttpConfigurer::disable)      // 변경
                .sessionManagement(AbstractHttpConfigurer::disable) // 추가
                // JWT 필터 추가
                .addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(customAccessDenied())
                        .authenticationEntryPoint(customEntryPoint())
                );

        return http.build();
    }

    // 추가
    @Bean
    public JwtAuthFilter jwtAuthFilter() {
        return new JwtAuthFilter(jwtUtil, customUserDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomAccessDenied customAccessDenied() {
        return new CustomAccessDenied();
    }

    @Bean
    public CustomEntryPoint customEntryPoint() {
        return new CustomEntryPoint();
    }
}