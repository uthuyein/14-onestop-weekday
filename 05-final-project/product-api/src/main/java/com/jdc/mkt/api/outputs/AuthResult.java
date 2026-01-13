/**
 * 
 */
package com.jdc.mkt.api.outputs;

import com.jdc.mkt.model.entities.Account;
import com.jdc.mkt.model.entities.Account.Role;

import lombok.Builder;

/**
 * AuthResult
 *
 * @author MKT
 * @created Jan 13, 2026
 * @project product-api
 */

@Builder
public record AuthResult(
		String name,
		String email,
		Role role,
		String accessToken,
		String refreshToken
		) {

	/**
	 * @param acc
	 * @return
	 */
	public static AuthResultBuilder with(Account acc) {
		return AuthResult.builder()
				.name(acc.getName())
				.email(acc.getEmail())
				.role(acc.getRole());
				
				
	}

}
