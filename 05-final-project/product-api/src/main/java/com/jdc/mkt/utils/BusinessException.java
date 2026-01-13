/**
 * 
 */
package com.jdc.mkt.utils;

/**
 * BusinessException
 *
 * @author MKT
 * @created Jan 13, 2026
 * @project product-api
 */

public class BusinessException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public BusinessException(String message) {
		super(message);
	}

}
