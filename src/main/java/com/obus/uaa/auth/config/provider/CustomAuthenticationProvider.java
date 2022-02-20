package com.obus.uaa.auth.config.provider;

import java.util.Collections;

import javax.annotation.Resource;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.obus.uaa.auth.service.UserDetailsServiceObus;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Resource
	UserDetailsServiceObus userDetailsServiceObus;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		return pakeHardcoded(authentication);
	}

	@Override
	public boolean supports(Class<?> authentication) {

		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	private UsernamePasswordAuthenticationToken pakeHardcoded(Authentication authentication) {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		if(username.equals("user") && password.equals("password")) {
			return new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList());
		} else {
			throw new BadCredentialsException("Invalid login");
		}	
	}

	private UsernamePasswordAuthenticationToken pakeUserDetailsService(Authentication authentication) {
		String username = authentication.getName();

		UserDetails user = null;
		
		user = userDetailsServiceObus.loadUserByUsername(username);
		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				user.getUsername(), user.getPassword(), user.getAuthorities()); 
		token.setDetails(authentication.getDetails());
		
		return token;
	}
}
