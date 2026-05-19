package umc.global.security.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import umc.global.apiPayload.code.GeneralErrorCode;

import java.io.IOException;

import static umc.global.security.util.SecurityResponseUtil.writeErrorResponse;

public class CustomAccessDenied implements AccessDeniedHandler {

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException
    ) throws IOException {
        writeErrorResponse(response, GeneralErrorCode.FORBIDDEN);
    }
}
