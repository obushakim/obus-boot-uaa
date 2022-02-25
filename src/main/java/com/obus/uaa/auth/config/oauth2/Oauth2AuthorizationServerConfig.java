package com.obus.uaa.auth.config.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

import com.obus.uaa.auth.config.property.ConfigProperty;

@SuppressWarnings("deprecation")
@Configuration
@EnableAuthorizationServer
public class Oauth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private ConfigProperty configProperty;
	
	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;
	
	@Autowired
	@Qualifier("passwordEncoder")
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer
			.tokenKeyAccess("permitAll()")
			.checkTokenAccess("isAuthenticated()");
	}
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients
			.inMemory()
			.withClient(configProperty.getOauth().getClientID())
			.secret(passwordEncoder.encode(configProperty.getOauth().getClientSecret()))
			.authorizedGrantTypes("password", "refresh_token")
			.scopes("read", "write", "user_info")
//			.authorities("READ_ONLY_CLIENT")
//			.redirectUris(configProperty.getOauth().getRedirectUrls())
			.accessTokenValiditySeconds(configProperty.getOauth().getAccessTokenValidity())
			.refreshTokenValiditySeconds(configProperty.getOauth().getRefreshTokenValidity())
			.autoApprove(true);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
			.authenticationManager(authenticationManager);
	}
}
