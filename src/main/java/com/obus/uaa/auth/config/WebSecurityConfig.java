package com.obus.uaa.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources**");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
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

		http.authorizeRequests()
			.antMatchers("/authenticate", "/oauth/token**", "/oauth/authorize**", "/login**", "/error**").permitAll()
//			.antMatchers("/account*").hasAnyRole("USER","ADMIN")
////			.antMatchers("/user").hasRole("ADMIN")
//			.antMatchers("/").permitAll()
			.anyRequest().authenticated();
		
//		http.httpBasic();
			
		/* avoid multiple browser login */
		http.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
			.maximumSessions(1)
			.maxSessionsPreventsLogin(true);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() { 
	    return NoOpPasswordEncoder.getInstance(); 
	}
	
//	@Bean("bcryptPasswordEncoder")
//	public BCryptPasswordEncoder bcryptPasswordEncoder() { 
//	    return new BCryptPasswordEncoder(8);
//	}
	
	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}
	
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	@Bean
//    public FilterRegistrationBean corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("*");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        source.registerCorsConfiguration("/**", config);
//        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
//        bean.setOrder(0);
//        return bean;
//    }	
}
