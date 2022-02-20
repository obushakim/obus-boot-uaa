package com.obus.uaa.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obus.uaa.common.service.BaseService;
import com.obus.uaa.user.domain.User;
import com.obus.uaa.user.repository.UserRepository;
import com.obus.uaa.user.request.UserRequest;
import com.obus.uaa.user.response.UserResponse;

@Service
public class GetUserByIdService implements BaseService<UserRequest, UserResponse>{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserResponse execute(UserRequest input) {

		User user = userRepository.findById(input.getId())
				.orElseThrow(() -> new IllegalArgumentException("User not found"));
		
		return UserResponse.toResponse(user);
	}

}
