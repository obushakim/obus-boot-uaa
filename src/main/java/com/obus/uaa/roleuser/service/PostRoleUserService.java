package com.obus.uaa.roleuser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obus.uaa.common.exception.BadRequestException;
import com.obus.uaa.common.service.BaseService;
import com.obus.uaa.role.repository.RoleRepository;
import com.obus.uaa.roleuser.domain.RoleUser;
import com.obus.uaa.roleuser.repository.RoleUserRepository;
import com.obus.uaa.roleuser.request.RoleUserRequest;
import com.obus.uaa.roleuser.response.RoleUserResponse;
import com.obus.uaa.user.repository.UserRepository;

@Service
public class PostRoleUserService implements BaseService<RoleUserRequest, RoleUserResponse>{

	@Autowired
	private RoleUserRepository roleUserRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public RoleUserResponse execute(RoleUserRequest input) {

		RoleUser roleUser = RoleUser.builder()
				.user(userRepository.findById(input.getIdUser())
						.orElseThrow(() -> new BadRequestException("Cannot get user information")))
				.role(roleRepository.findById(input.getIdRole())
						.orElseThrow(() -> new BadRequestException("Cannot get role information")))
				.build();
		
		roleUser = roleUserRepository.save(roleUser);
		
		RoleUserResponse response = RoleUserResponse.toResponse(roleUser);
		
		return response;
	}

}
