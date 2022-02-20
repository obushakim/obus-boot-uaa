package com.obus.uaa.roleuser.response;

import java.util.UUID;

import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.obus.uaa.common.model.base.BaseResponse;
import com.obus.uaa.roleuser.domain.RoleUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonNaming
public class RoleUserResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4514812258929747022L;
	
	private UUID idUser;
	private UUID idRole;
	
	public static RoleUserResponse toResponse(RoleUser roleUser) {
		
		RoleUserResponse response = new RoleUserResponse();
		
		if(!ObjectUtils.isEmpty(roleUser)) {
			response.setId(roleUser.getId());
			response.setCreatedBy(roleUser.getCreatedBy());
			response.setCreatedDate(roleUser.getCreatedDate());
			response.setLastModifiedBy(roleUser.getLastModifiedBy());
			response.setLastModifiedDate(roleUser.getLastModifiedDate());
			response.setVersion(roleUser.getVersion());
			
			response.setIdUser(roleUser.getUser().getId());
			response.setIdRole(roleUser.getRole().getId());
		}
		
		return response;
	}
}
