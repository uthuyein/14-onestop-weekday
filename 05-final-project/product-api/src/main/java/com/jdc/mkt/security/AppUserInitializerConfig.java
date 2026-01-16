package com.jdc.mkt.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jdc.mkt.model.entities.Account;
import com.jdc.mkt.model.entities.Account.Role;
import com.jdc.mkt.model.repositories.AccountRepo;

/**
 * AppUserInitializer
 *
 * @author MKT
 * @created Jan 14, 2026
 * @project product-api
 */

@Configuration
public class AppUserInitializerConfig {

	@Value("${app.admin.username}")
	private String username;
	@Value("${app.admin.password}")
	private String password;

	@Bean
	ApplicationRunner initializer(AccountRepo repo, PasswordEncoder passwordEncoder) {
		return _ -> {
			var acc = new Account();
			acc.setName("Andrew");
			acc.setEmail(username);
			acc.setPassword(passwordEncoder.encode(password));
			acc.setDisabled(false);
			acc.setRole(Role.Admin);
			repo.save(acc);
		};
	}
}
