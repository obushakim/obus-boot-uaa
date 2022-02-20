package com.obus.uaa.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obus.uaa.common.exception.BadRequestException;
import com.obus.uaa.common.service.BaseService;
import com.obus.uaa.roleuser.domain.RoleUser;
import com.obus.uaa.roleuser.repository.RoleUserRepository;
import com.obus.uaa.user.domain.User;
import com.obus.uaa.user.repository.UserRepository;
import com.obus.uaa.user.request.UserRequest;
import com.obus.uaa.user.response.UserResponse;

@Service
public class PostRoleToUserService implements BaseService<UserRequest, UserResponse>{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleUserRepository roleUserRepository;
	
	@Override
	public UserResponse execute(UserRequest input) {
		User user = userRepository.findById(input.getId())
				.orElseThrow(() -> new BadRequestException("Cannot find user information"));
		
		List<RoleUser> roleUserList = input.getRoleList().stream().map(role -> {
			RoleUser roleUser = RoleUser.builder()
					.user(user)
					.role(role)
					.build();
			
			return roleUser;
		}).collect(Collectors.toList());
		
		roleUserList = roleUserRepository.saveAll(roleUserList);
		
		user.setRoleUserList(roleUserList);
		
		return UserResponse.toResponse(user);
	}
}
