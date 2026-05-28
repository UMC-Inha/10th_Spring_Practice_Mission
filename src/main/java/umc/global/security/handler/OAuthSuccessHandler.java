package umc.global.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import umc.domain.member.converter.MemberConverter;
import umc.domain.member.dto.MemberResDTO;
import umc.domain.member.exception.code.MemberSuccessCode;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.BaseSuccessCode;
import umc.global.security.entity.AuthMember;
import umc.global.security.entity.OAuthMember;
import umc.global.security.util.JwtUtil;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {

        BaseSuccessCode code = MemberSuccessCode.MEMBER_LOGIN;

        // Content-Type, Status 설정
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(code.getStatus().value());

        // 인증 객체 컨테이너에서 OAuth 인증 객체 가져오기
        OAuthMember member = (OAuthMember) authentication.getPrincipal();

        // 토큰 제작을 위해 OAuth 인증 객체에서 Member 추출 -> AuthMember 제작
        String accessToken = jwtUtil.createAccessToken(
                new AuthMember(member.getMember())
        );

        // 응답 통일 객체 래핑
        ApiResponse<MemberResDTO.Login> responseBody = ApiResponse.onSuccess(
                code,
                MemberConverter.toLogin(accessToken)
        );

        // 응답 출력
        objectMapper.writeValue(response.getWriter(), responseBody);
    }
}
