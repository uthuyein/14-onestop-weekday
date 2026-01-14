package com.jdc.mkt.security.exception;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * SecurityExceptionHandler
 *
 * @author MKT
 * @created Jan 14, 2026
 * @project product-api
 */

public class SecurityExceptionHandler implements AuthenticationEntryPoint,AccessDeniedHandler{

	@Autowired
	private HandlerExceptionResolver handlerExceptionResolver;
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		handlerExceptionResolver.resolveException(request, response, null, accessDeniedException);
	}

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		handlerExceptionResolver.resolveException(request, response, null, authException);
	}

}
