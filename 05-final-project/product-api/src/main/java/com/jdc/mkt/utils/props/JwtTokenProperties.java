package com.jdc.mkt.utils.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * JwtTokenProperties
 *
 * @author MKT
 * @created Jan 13, 2026
 * @project product-api
 */

@Data
@ConfigurationProperties(prefix = "app.jwt")
public class JwtTokenProperties {

	private String issuer;
	private String accessLife;
	private String refreshLife;
}
