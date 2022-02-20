package com.obus.uaa.common.api.response;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response<T> {

	@JsonProperty("status")
	private HttpStatus httpStatus;
	
	@JsonProperty("code")
	private int statusCode;
	
	@JsonProperty("message")
	private String message;
	
	@JsonProperty("data")
	private T body;
	
	public static <T> Response<T> success(T body, String message){
		return Response.<T>builder()
				.body(body)
				.message(message)
				.httpStatus(HttpStatus.OK)
				.statusCode(HttpStatus.OK.value())
				.build();
	}
}
