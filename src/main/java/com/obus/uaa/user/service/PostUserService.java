package com.obus.uaa.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obus.uaa.common.service.BaseService;
import com.obus.uaa.user.domain.User;
import com.obus.uaa.user.repository.UserRepository;
import com.obus.uaa.user.request.UserRequest;
import com.obus.uaa.user.response.UserResponse;

@Service
public class PostUserService implements BaseService<UserRequest, UserResponse>{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserResponse execute(UserRequest input) {

		User user = User.builder()
				.username(input.getUsername())
				.password(input.getPassword())
				.email(input.getEmail())
				.fullname(input.getFullname())
				.build();
		
		user = userRepository.save(user);
		
		UserResponse response = new UserResponse();
		response.setId(user.getId());
		
		return response;
	}

}
