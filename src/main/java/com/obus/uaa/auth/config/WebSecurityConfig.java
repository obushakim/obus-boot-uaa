package com.obus.uaa.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.obus.uaa.auth.config.filter.TokenRequestFilter;
import com.obus.uaa.auth.config.provider.CustomAuthenticationProvider;
import com.obus.uaa.auth.service.UserDetailsServiceObus;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsServiceObus userDetailsServiceObus;
	
	@Autowired
	CustomAuthenticationProvider customAuthenticationProvider;

	@Autowired
	TokenRequestFilter tokenRequestFilter;
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(customAuthenticationProvider);
//		auth.userDetailsService(userDetailsServiceObus);
		
		auth.inMemoryAuthentication()
			.withUser("obus").password(passwordEncoder().encode("password")).roles("USER")
			.and()
			.withUser("admin")
			.password(passwordEncoder().encode("password"))
			.roles("ADMIN");

	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		/* buat basic auth */
//		http
//		.csrf().disable()
//		.authorizeRequests()
//		.antMatchers("/account*").hasAnyRole("USER","ADMIN")
////		.antMatchers("/user").hasRole("ADMIN")
//		.antMatchers("/").permitAll()
////		.anyRequest().authenticated()
//		.and()
//		.httpBasic();
		
		http.authorizeRequests()
			.antMatchers("/authenticate", "/oauth/authorize**", "/login**", "/error**").permitAll()
			.anyRequest().authenticated();
		
//		http.httpBasic();
			
		/* avoid multiple browser login */
		http.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
			.maximumSessions(1)
			.maxSessionsPreventsLogin(true);
		
		/* authorization token filter */
		http.addFilterBefore(tokenRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() { 
	    return NoOpPasswordEncoder.getInstance(); 
	}
	
	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}
}
