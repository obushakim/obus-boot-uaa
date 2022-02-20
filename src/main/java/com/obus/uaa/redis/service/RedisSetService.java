package com.obus.uaa.redis.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisSetService {
	
	private SetOperations<String, Object> setOperations;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	public RedisSetService(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
		this.setOperations = redisTemplate.opsForSet();
	}
	
	public Set<Object> members(String key) {
		
		return setOperations.members(key);
	}

//	public RedisCrud create(String key, Object... values) {
//		RedisCrud test = new RedisCrud();
//		test.setContent(value);
//		test.setCreatedDate(LocalDateTime.now());
//		
//		setOperations.set(key,test, time, TimeUnit.SECONDS);
//
//		return test;
//	}
	
//	public RedisCrud get(String key) {
//
//		return (RedisCrud) setOperations.get(key);
//	}

	public boolean delete(String key) {
		return redisTemplate.delete(key);
	}
	
	public long deleteAll(List<String> keyList) {
		return redisTemplate.delete(keyList);
	}
	
}
