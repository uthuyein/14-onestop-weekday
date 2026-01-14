package com.jdc.mkt.security.token;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * JwtTokenFilter
 *
 * @author MKT
 * @created Jan 14, 2026
 * @project product-api
 */


public class JwtTokenFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtTokenProvider tokenProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		var token = request.getHeader("Authorization");
			
		if(StringUtils.hasLength(token)) {
			SecurityContextHolder.getContext().setAuthentication(tokenProvider.parseAccess(token));
		}
		
		filterChain.doFilter(request, response);
	}

}
