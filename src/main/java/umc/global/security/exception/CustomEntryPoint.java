package umc.global.security.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import umc.global.apiPayload.code.GeneralErrorCode;
import umc.global.security.util.SecurityResponseUtil;

import java.io.IOException;

public class CustomEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {
        SecurityResponseUtil.writeErrorResponse(response, GeneralErrorCode.UNAUTHORIZED);
    }
}