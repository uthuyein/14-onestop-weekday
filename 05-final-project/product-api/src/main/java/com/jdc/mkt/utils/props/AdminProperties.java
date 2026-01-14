package com.jdc.mkt.utils.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * AdminProperties
 *
 * @author MKT
 * @created Jan 14, 2026
 * @project product-api
 */

@Data
@ConfigurationProperties(prefix = "app.admin")
public class AdminProperties {

	private String username;
	private String password;
}
