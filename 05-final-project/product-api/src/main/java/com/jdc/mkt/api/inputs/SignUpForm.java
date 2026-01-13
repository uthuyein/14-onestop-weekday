/**
 * 
 */
package com.jdc.mkt.api.inputs;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.jdc.mkt.model.entities.Account;
import com.jdc.mkt.model.entities.Account.Role;

import jakarta.validation.constraints.NotBlank;

/**
 * SignUpForm
 *
 * @author MKT
 * @created Jan 13, 2026
 * @project product-api
 */

public record SignUpForm(
		
		@NotBlank(message = "user name must not be empty !")
		String name,
		@NotBlank(message = "Login id must not be empty !")
		String email,
		@NotBlank(message = "Invalid Password !")
		String password,
		Role role
		) {

	/**
	 * @param passwordEncoder 
	 * @return
	 */
	public Account entity(PasswordEncoder passwordEncoder) {
		var acc = new Account();
		acc.setName(name);
		acc.setEmail(email);
		acc.setPassword(passwordEncoder.encode(password));
		acc.setRole(Role.Member);	
		return acc;
	}
	
}
