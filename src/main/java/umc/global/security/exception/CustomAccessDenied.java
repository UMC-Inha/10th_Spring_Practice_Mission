package umc.global.security.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import umc.global.apiPayload.code.GeneralErrorCode;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAccessDenied implements AccessDeniedHandler {

    private final SecurityErrorResponseWriter securityErrorResponseWriter;

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException
    ) throws IOException {
        securityErrorResponseWriter.write(response, GeneralErrorCode.FORBIDDEN);
    }
}
