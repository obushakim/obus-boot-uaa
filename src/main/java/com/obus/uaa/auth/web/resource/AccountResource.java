package com.obus.uaa.auth.web.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.obus.uaa.auth.request.AuthenticationRequest;
import com.obus.uaa.auth.response.AuthenticationResponse;
import com.obus.uaa.auth.service.UserDetailsServiceObus;
import com.obus.uaa.auth.util.JWTUtil;
import com.obus.uaa.common.exception.UnauthorizedException;

@RestController
public class AccountResource {
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserDetailsServiceObus userDetailsServiceObus;
	
	@Autowired
	JWTUtil jwtUtil;
	
	@GetMapping
	public ResponseEntity<String> index(){
	
		String response = "test";
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest request) {
		String token = null;
		
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
			
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		
		UserDetails userDetails = userDetailsServiceObus.loadUserByUsername(request.getUsername());

		token = jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(token));
	}
}
