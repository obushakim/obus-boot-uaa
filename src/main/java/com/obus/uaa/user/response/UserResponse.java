package com.obus.uaa.user.response;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.obus.uaa.common.model.base.BaseResponse;
import com.obus.uaa.roleuser.response.RoleUserResponse;
import com.obus.uaa.user.domain.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonNaming
public class UserResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4514812258929747022L;
	
	private String username;
	private String password;
	private String email;
	private String fullname;
	private List<RoleUserResponse> roleUserList;
	
	public static UserResponse toResponse(User user) {
		
		UserResponse response = new UserResponse();
		
		if(!ObjectUtils.isEmpty(user)) {
			response.setId(user.getId());
			response.setCreatedBy(user.getCreatedBy());
			response.setCreatedDate(user.getCreatedDate());
			response.setLastModifiedBy(user.getLastModifiedBy());
			response.setLastModifiedDate(user.getLastModifiedDate());
			response.setVersion(user.getVersion());
			
			response.setUsername(user.getUsername());
			response.setPassword(user.getPassword());
			response.setEmail(user.getEmail());
			response.setFullname(user.getFullname());
			
			List<RoleUserResponse> roleUserResponseList = user.getRoleUserList().stream().map(roleUser -> {
				return RoleUserResponse.toResponse(roleUser);				
			}).collect(Collectors.toList());
			
			response.setRoleUserList(roleUserResponseList);
		}
		
		return response;
	}
	
}
