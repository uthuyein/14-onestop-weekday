package com.jdc.mkt.security.token;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppAuthenticationProvider extends DaoAuthenticationProvider {

	public AppAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
		super(userDetailsService);
		setPasswordEncoder(passwordEncoder);
		setHideUserNotFoundExceptions(false);
	}

}
