package com.obus.uaa.role.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obus.uaa.common.service.BaseService;
import com.obus.uaa.role.domain.Role;
import com.obus.uaa.role.repository.RoleRepository;
import com.obus.uaa.role.request.RoleRequest;
import com.obus.uaa.role.response.RoleResponse;

@Service
public class PostRoleService implements BaseService<RoleRequest, RoleResponse>{

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public RoleResponse execute(RoleRequest input) {

		Role role = Role.builder()
				.code(input.getCode())
				.name(input.getName())
				.build();
		
		role = roleRepository.save(role);
		
		RoleResponse response = RoleResponse.toResponse(role);
		
		return response;
	}

}
