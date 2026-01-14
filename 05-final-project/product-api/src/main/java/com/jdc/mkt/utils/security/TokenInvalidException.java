package com.jdc.mkt.utils.security;

/**
 * TokenInvalidException
 *
 * @author MKT
 * @created Jan 14, 2026
 * @project product-api
 */

public class TokenInvalidException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public TokenInvalidException(String message) {
		super(message);
	}

}
