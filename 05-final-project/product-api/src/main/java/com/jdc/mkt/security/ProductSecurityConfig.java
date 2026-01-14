/**
 * 
 */
package com.jdc.mkt.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;

import com.jdc.mkt.model.entities.Account.Role;
import com.jdc.mkt.security.exception.SecurityExceptionHandler;
import com.jdc.mkt.security.token.JwtTokenFilter;

/**
 * ProductSecurityConfig
 *
 * @author MKT
 * @created Jan 13, 2026
 * @project product-api
 */
@Configuration
@EnableMethodSecurity
public class ProductSecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) {
		
		http.csrf(csrf -> csrf.disable());
		http.cors(Customizer.withDefaults());
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		http.authorizeHttpRequests(req ->{
				 req.requestMatchers("/auth/**").permitAll();
				 req.requestMatchers("/admin/**").hasAuthority(Role.Admin.name());
				 req.requestMatchers("/member/**").hasAnyAuthority(Role.Admin.name(),Role.Member.name());
				 req.anyRequest().authenticated();
			
		});
		
		http.exceptionHandling(exception ->{
			exception.accessDeniedHandler(securityExceptionHandler());
			exception.authenticationEntryPoint(securityExceptionHandler());
		});
		
		http.addFilterAfter(jwtTokenFilter(), ExceptionTranslationFilter.class);
		
		return http.build();
	}
	
	@Bean
	SecurityExceptionHandler securityExceptionHandler() {
		return new SecurityExceptionHandler();
	}
	
	@Bean
	JwtTokenFilter jwtTokenFilter() {
		return new JwtTokenFilter();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	
}
