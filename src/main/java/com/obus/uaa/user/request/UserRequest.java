package com.obus.uaa.user.request;

import java.util.UUID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.obus.uaa.common.model.base.BaseRequest;
import com.obus.uaa.common.validator.email.ValidEmail;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@JsonNaming
public class UserRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4514812258929747022L;
	
	private UUID id;
	
	@NotNull
	@NotEmpty
	private String username;
	
	private String password;

	private String matchingPassword;

	private String fullname;
	
	@ValidEmail
	private String email;
}
