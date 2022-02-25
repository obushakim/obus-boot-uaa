package com.obus.uaa.role.response;

import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.obus.uaa.common.model.base.BaseResponse;
import com.obus.uaa.role.domain.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonNaming
public class RoleResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4514812258929747022L;
	
	private String code;
	private String name;
	
	public static RoleResponse toResponse(Role role) {
		
		RoleResponse response = new RoleResponse();
		
		if(!ObjectUtils.isEmpty(role)) {
			response.setId(role.getId());
			response.setCreatedBy(role.getCreatedBy());
			response.setCreatedDate(role.getCreatedDate());
			response.setLastModifiedBy(role.getLastModifiedBy());
			response.setLastModifiedDate(role.getLastModifiedDate());
			response.setVersion(role.getVersion());
			
			response.setCode(role.getCode());
			response.setName(role.getName());
		}
		
		return response;
	}
	
}
