package com.jdc.mkt.utils;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jdc.mkt.security.exception.TokenExpiredException;

@RestControllerAdvice
public class ExceptionHandlers {

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	List<String> handle(MethodArgumentNotValidException e) {
		return e.getFieldErrors().stream().map(error -> error.getDefaultMessage()).toList();
	}

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	List<String> handle(BusinessException e) {
		return List.of(e.getMessage());
	}

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	List<String> handle(AuthenticationException e) {
		return List.of( switch (e) {
		case UsernameNotFoundException _ -> "Please check your loginId !";
		case BadCredentialsException _ -> "Please check your password !";
		case DisabledException _-> "Your account is disable ";
		default -> "Authentication error";
	});
	}

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.GONE)
	List<String> handle(TokenExpiredException e) {
		return List.of(e.getMessage());
	}
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	List<String> handle(Throwable e) {
		return List.of(e.getMessage());
	}

}
