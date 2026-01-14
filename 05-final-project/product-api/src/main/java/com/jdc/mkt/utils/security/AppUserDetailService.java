package com.jdc.mkt.utils.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jdc.mkt.model.repositories.AccountRepo;

/**
 * AppUserDetailService
 *
 * @author MKT
 * @created Jan 14, 2026
 * @project product-api
 */

@Service
public class AppUserDetailService implements UserDetailsService{

	@Autowired
	private AccountRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return repo.findOneByEmail(username)
				.map(acc -> User.builder()
						.username(acc.getEmail())
						.password(acc.getPassword())
						.authorities(acc.getRole().name())
						.disabled(acc.isDisabled())
						.build())
						.orElseThrow(() -> new UsernameNotFoundException(username));
	}

}
