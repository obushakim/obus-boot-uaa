package com.obus.uaa.user.web.resource;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.obus.uaa.user.request.UserRequest;
import com.obus.uaa.user.response.UserListResponse;
import com.obus.uaa.user.response.UserResponse;
import com.obus.uaa.user.service.GetAllUserService;
import com.obus.uaa.user.service.GetUserByIdService;
import com.obus.uaa.user.service.PostUserService;

@RestController
@RequestMapping(path = "user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserResource {

	@Autowired
	private PostUserService postUserService;
	
	@Autowired
	private GetUserByIdService getUserByIdService;
	
	@Autowired
	private GetAllUserService getAllUserService;
	
	@PostMapping
	public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest input) {
		UserResponse response = postUserService.execute(input);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<UserResponse> findById(@PathVariable(value = "id") String id) {
		UserResponse response = getUserByIdService.execute(UserRequest.builder().id(UUID.fromString(id)).build());
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping
	public ResponseEntity<UserListResponse> findAll(UserRequest input) {
		UserListResponse response = getAllUserService.execute(input);
		
		return ResponseEntity.ok(response);
	}
}
