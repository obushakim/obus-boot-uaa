package com.obus.uaa.auth.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "app")
public class ConfigProperty {
	private Oauth oauth;
	private User user;
	
	@Data
	public static class User {
		private String username;
		private String password;
	}
	
	@Data
	public static class Oauth {
		private String clientID;
		private String clientSecret;
		private String redirectUrls;
		private int accessTokenValidity;
		private int refreshTokenValidity;		
	}
}
