/**
 * 
 */
package com.jdc.mkt.api.inputs;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import jakarta.validation.constraints.NotBlank;

/**
 * SignInForm
 *
 * @author MKT
 * @created Jan 13, 2026
 * @project product-api
 */

public record SignInForm(
		@NotBlank(message = "Login id must not be empty !")
		String email,
		@NotBlank(message = "Invalid Password !")
		String password
		) {

	public Authentication authenticate() {
		return UsernamePasswordAuthenticationToken.unauthenticated(email,password);
	}

}
