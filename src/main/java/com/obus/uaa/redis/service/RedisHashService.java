package com.obus.uaa.redis.service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.obus.uaa.redis.domain.RedisCrud;

@Service
public class RedisHashService {
	private static final String HASH_VALUE = "TEST";
	
	private HashOperations<String, UUID, RedisCrud> hashOperations;
	
	public RedisHashService(RedisTemplate<String, Object> redisTemplate) {
		this.hashOperations = redisTemplate.opsForHash();
	}
	
	public RedisCrud create(String username) {
		RedisCrud test = new RedisCrud();
		test.setContent(username);
		test.setCreatedDate(LocalDateTime.now());
		
		hashOperations.put(HASH_VALUE, UUID.randomUUID(), test);

		return test;
	}
	
	public RedisCrud get(UUID id) {

		return hashOperations.get(HASH_VALUE, id);
	}
	
	public Map<UUID, RedisCrud> getAll() {
		return hashOperations.entries(HASH_VALUE);
	}
	
	public RedisCrud update(UUID id, String username) {
		RedisCrud test = (RedisCrud) hashOperations.get(HASH_VALUE, id);
		test.setContent(username);
		
		hashOperations.put(HASH_VALUE, test.getId(), test);

		return test;
	}

	public void delete(UUID id) {
		hashOperations.delete(HASH_VALUE, id);
	}
	
}
