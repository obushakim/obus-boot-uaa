package com.obus.uaa.user.response;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.obus.uaa.common.model.base.BaseResponse;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@JsonNaming
public class UserListResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 763124134541796209L;
	
	private List<UserResponse> contents;
}
