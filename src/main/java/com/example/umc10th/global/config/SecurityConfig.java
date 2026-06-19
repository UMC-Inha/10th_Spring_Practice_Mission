package com.example.umc10th.global.config;

import com.example.umc10th.global.security.exception.CustomAccessDenied;
import com.example.umc10th.global.security.exception.CustomEntryPoint;
import com.example.umc10th.global.security.filter.JwtAuthFilter;
import com.example.umc10th.global.security.service.CustomUserDetailsService;
import com.example.umc10th.global.security.util.JwtUtil;
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

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

   private final JwtUtil jwtUtil;
   private final CustomUserDetailsService customUserDetailsService;

   private final String[] allowUris = {
            // Swagger 허용
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v3/api-docs/swagger-config", 
            "/v3/api-docs/**",

            // public api
            "/auth/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                // URI 허용 여부
                .authorizeHttpRequests(requests -> requests
                        // Public API 허용
                        .requestMatchers(allowUris).permitAll()
                        // 그 외의 API는 인증 필요
                        .anyRequest().authenticated()
                )
                // 폼 로그인
                .formLogin(form -> form
                        .defaultSuccessUrl("/swagger-ui/index.html", true)
                        .permitAll()
                )
                // 세션
                .sessionManagement(AbstractHttpConfigurer::disable)
                // JWT 필터 -> 순서는 상관없고 시큐리티 필터체인에 들어가면 된다.
                .addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
               // 예외사항 핸들러
               .exceptionHandling(exception ->exception
                   .accessDeniedHandler(customAccessDenied())
                   .authenticationEntryPoint(customEntryPoint())
               )
        ;

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomAccessDenied customAccessDenied(){
       return new CustomAccessDenied();
    }

    @Bean
    public CustomEntryPoint customEntryPoint(){
       return new CustomEntryPoint();
    }

    @Bean
    public JwtAuthFilter jwtAuthFilter() {
       return new JwtAuthFilter(jwtUtil, customUserDetailsService);
    }
}
