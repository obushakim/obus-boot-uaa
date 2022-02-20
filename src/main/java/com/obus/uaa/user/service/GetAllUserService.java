package com.obus.uaa.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obus.uaa.common.service.BaseService;
import com.obus.uaa.user.domain.User;
import com.obus.uaa.user.repository.UserRepository;
import com.obus.uaa.user.request.UserRequest;
import com.obus.uaa.user.response.UserListResponse;
import com.obus.uaa.user.response.UserResponse;

@Service
public class GetAllUserService implements BaseService<UserRequest, UserListResponse>{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserListResponse execute(UserRequest input) {

		List<User> userList = userRepository.findAll();
		
		List<UserResponse> list = userList.stream().map(this::toResponse).collect(Collectors.toList());
		
		return UserListResponse.builder().contents(list).build();
	}

	private UserResponse toResponse(User obj) {
		return UserResponse.toResponse(obj);
	}
}
