/**
 * 
 */
package com.jdc.mkt.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.mkt.api.inputs.SignInForm;
import com.jdc.mkt.api.inputs.SignUpForm;
import com.jdc.mkt.api.inputs.TokenForm;
import com.jdc.mkt.api.outputs.AuthResult;
import com.jdc.mkt.model.entities.Account;
import com.jdc.mkt.model.services.AccountService;
import com.jdc.mkt.security.token.JwtTokenProvider;

/**
 * AuthApi
 *
 * @author MKT
 * @created Jan 13, 2026
 * @project product-api
 */

@RestController
@RequestMapping("auth")
public class AuthApi {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenProvider tokenProvider;
	@Autowired
	private AccountService accService;

	@PostMapping("signIn")
	AuthResult signIn(@Validated @RequestBody SignInForm form) {
		return signIn(form.authenticate());
	}
	
	@PostMapping("signUp")
	AuthResult signUp(@Validated @RequestBody SignUpForm form) {		
		var acc = accService.create(form);
		return signIn(UsernamePasswordAuthenticationToken.unauthenticated(acc.getEmail(), form.password()));
	}
	
	@GetMapping
	Account findByEmail(@RequestParam String email) {
		return accService.findByEmail(email);
	}
	
	@PostMapping("refresh")
	AuthResult refresh(@Validated @RequestBody TokenForm form) {		
		var auth = tokenProvider.parseRefresh(form.token());
		return getResult(auth);
	}
	
	
	private AuthResult signIn(Authentication authenticate) {
		
		var auth = authenticationManager.authenticate(authenticate);
		return getResult(auth);
	}

	private AuthResult getResult(Authentication auth) {
		var acc = accService.findByEmail(auth.getName());
	
		return AuthResult.with(acc)
				.accessToken(tokenProvider.generateAccessToken(auth))
				.refreshToken(tokenProvider.generateRefreshToken(auth))
				.build();
	}
}
