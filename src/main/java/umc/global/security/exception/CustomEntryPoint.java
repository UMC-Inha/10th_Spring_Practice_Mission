package umc.global.security.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import umc.global.apiPayload.code.GeneralErrorCode;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomEntryPoint implements AuthenticationEntryPoint {

    private final SecurityErrorResponseWriter securityErrorResponseWriter;

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {
        securityErrorResponseWriter.write(response, GeneralErrorCode.UNAUTHORIZED);
    }
}