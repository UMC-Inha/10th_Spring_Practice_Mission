package com.example.umc10th.global.security;

import com.example.umc10th.global.apiPayload.code.GeneralErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private final SecurityErrorResponseWriter responseWriter;

	public CustomAuthenticationEntryPoint(SecurityErrorResponseWriter responseWriter) {
		this.responseWriter = responseWriter;
	}

	@Override
	public void commence(
		HttpServletRequest request,
		HttpServletResponse response,
		AuthenticationException authException
	) throws IOException, ServletException {
		responseWriter.write(response, GeneralErrorCode.UNAUTHORIZED);
	}
}
