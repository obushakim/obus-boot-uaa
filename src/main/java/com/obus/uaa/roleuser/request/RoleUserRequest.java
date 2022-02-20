package com.obus.uaa.roleuser.request;

import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.obus.uaa.common.model.base.BaseRequest;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@JsonNaming
public class RoleUserRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4514812258929747022L;
	
	private UUID id;
	
	private UUID idUser;

	private UUID idRole;
}
