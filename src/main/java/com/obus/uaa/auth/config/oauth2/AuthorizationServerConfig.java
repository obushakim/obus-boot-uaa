package com.obus.uaa.auth.config.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

import com.obus.uaa.auth.config.property.ConfigProperty;

@SuppressWarnings("deprecation")
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private ConfigProperty configProperty;
	
//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer
			.tokenKeyAccess("permitAll()")
			.checkTokenAccess("isAuthenticated()");
	}
	
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients
			.inMemory()
			.withClient(configProperty.getOauth().getClientID())
			.secret(passwordEncoder.encode(configProperty.getOauth().getClientSecret()))
			.authorizedGrantTypes("password", "authorization_code", "refresh_token")
			.scopes("user_info")
			.authorities("READ_ONLY_CLIENT")
			.redirectUris(configProperty.getOauth().getRedirectUrls())
			.accessTokenValiditySeconds(configProperty.getOauth().getAccessTokenValidity())
			.refreshTokenValiditySeconds(configProperty.getOauth().getRefreshTokenValidity());
	}
}
