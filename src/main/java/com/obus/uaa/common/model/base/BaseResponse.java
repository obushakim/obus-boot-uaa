package com.obus.uaa.common.model.base;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5337136510774558297L;

	private UUID id;
	
	private String createdBy;
		
	private LocalDateTime createdDate;
	
	private String lastModifiedBy;
		
	private LocalDateTime lastModifiedDate;
	
	private Long version;
	
	private boolean deleted;

}
