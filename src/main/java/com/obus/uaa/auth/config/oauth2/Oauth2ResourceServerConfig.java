package com.obus.uaa.auth.config.oauth2;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class Oauth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
//			.authorizeRequests()
//			.antMatchers("/authenticate", "/oauth/authorize**", "/login**", "/error**").permitAll()
//			.anyRequest().authenticated();
		
			.authorizeRequests()
			.antMatchers("/user**").authenticated()
			.antMatchers("/").permitAll();
		
//        http.antMatcher("/**").authorizeRequests().anyRequest().authenticated();
	}
}
