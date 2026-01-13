/**
 * 
 */
package com.jdc.mkt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.jdc.mkt.model.entities.Account.Role;

/**
 * ProductSecurityConfig
 *
 * @author MKT
 * @created Jan 13, 2026
 * @project product-api
 */
@Configuration
public class ProductSecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) {
		
		http.csrf(csrf -> csrf.disable());
		http.cors(Customizer.withDefaults());
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		http.authorizeHttpRequests(req ->{
				 req.requestMatchers("/auth/**").permitAll();
				 req.requestMatchers("/admin/**").hasAuthority(Role.Admin.name());
				 req.requestMatchers("/cashier/**").hasAnyAuthority(Role.Admin.name(),Role.Member.name());
				 req.anyRequest().authenticated();
			
		});
		
		return http.build();
	}
}
