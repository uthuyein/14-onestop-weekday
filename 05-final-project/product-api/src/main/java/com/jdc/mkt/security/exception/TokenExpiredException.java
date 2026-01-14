package com.jdc.mkt.security.exception;

/**
 * TokenExpiredException
 *
 * @author MKT
 * @created Jan 14, 2026
 * @project product-api
 */

public class TokenExpiredException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TokenExpiredException(String message) {
		super(message);
	}

}
