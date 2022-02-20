package com.obus.uaa.redis.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import lombok.Data;

@Data
@RedisHash("Test")
public class RedisCrud implements Serializable {

	@Id
	@GeneratedValue
	private UUID id;
	
	@Indexed
	private String content;
	
	private LocalDateTime createdDate;
}
