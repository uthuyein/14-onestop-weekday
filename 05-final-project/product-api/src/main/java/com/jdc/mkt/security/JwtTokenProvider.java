/**
 * 
 */
package com.jdc.mkt.security;

import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;

/**
 * JwtTokenProvider
 *
 * @author MKT
 * @created Jan 13, 2026
 * @project product-api
 */

@Service
public class JwtTokenProvider {
	
	@Value("${app.jwt.issuer}")
	private String issuer;
	
	@Value("${app.jwt.access-life}")
	private int accessLife;
	
	@Value("${app.jwt.refresh-life}")
	private int refreshLife;
	
	private SecretKey key = Jwts.SIG.HS256.key().build();
	
	public enum Type {
		Access,Refresh
	}

	/**
	 * @param auth
	 * @return
	 */
	public String generateAccessToken(Authentication auth) {
		return generate(Type.Access,auth);
	}

	/**
	 * @param auth
	 * @return
	 */
	public String generateRefreshToken(Authentication auth) {
		return generate(Type.Refresh,auth);
	}
	
	/**
	 * @param token
	 * @return
	 */
	public Authentication parseAccess(String token) {
		return parse(Type.Access,token);
	}

	/**
	 * @param token
	 * @return
	 */
	public Authentication parseRefresh(String token) {
		return parse(Type.Refresh,token);
	}
	
	/**
	 * @param refresh
	 * @param auth
	 * @return
	 */
	private Authentication parse(Type refresh, String token) {
		return null;
	}

	/**
	 * @param access
	 * @param auth
	 * @return
	 */
	private String generate(Type type, Authentication auth) {
		
		var issueAt = new Date();
		
		return Jwts.builder()
				.subject(auth.getName())
				.claim("role",auth.getAuthorities()
						.stream().map(a -> a.getAuthority())
						.collect(Collectors.joining(",")))
				.claim("type", type.name())
				.issuer(issuer)
				.issuedAt(issueAt)
				.expiration(getExpiration(type,issueAt))
				.signWith(key)
				.compact();
	}

	/**
	 * @param type
	 * @param issueAt
	 * @return
	 */
	private Date getExpiration(Type type, Date issueAt) {
		var calendar = Calendar.getInstance();
		calendar.setTime(issueAt);
		calendar.add(Calendar.MINUTE, type == Type.Access ? accessLife : refreshLife);
		return calendar.getTime();
	}

}
