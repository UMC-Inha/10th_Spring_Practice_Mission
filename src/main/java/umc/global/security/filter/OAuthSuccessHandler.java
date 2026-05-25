package umc.global.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import umc.domain.auth.dto.AuthResDTO;
import umc.domain.auth.exception.code.AuthSuccessCode;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.BaseSuccessCode;
import umc.global.security.entity.AuthMember;
import umc.global.security.entity.OAuthMember;
import umc.global.security.util.JwtUtil;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {
        BaseSuccessCode code = AuthSuccessCode.LOGIN_SUCCESS;

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(code.getStatus().value());

        OAuthMember member = (OAuthMember) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String accessToken = jwtUtil.createAccessToken(new AuthMember(member.getMember()));

        ApiResponse<AuthResDTO.LoginDTO> responseBody = ApiResponse.onSuccess(
                code,
                new AuthResDTO.LoginDTO(accessToken)
        );

        objectMapper.writeValue(response.getOutputStream(), responseBody);
    }
}
