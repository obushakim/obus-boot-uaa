package com.obus.uaa.redis.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.obus.uaa.redis.domain.RedisCrud;

@Service
public class RedisValueService {
	
	private ValueOperations<String, Object> valueOperations;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	public RedisValueService(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
		this.valueOperations = redisTemplate.opsForValue();
	}
	
	public RedisCrud create(String key, String value) {
		RedisCrud test = new RedisCrud();
		test.setContent(value);
		test.setCreatedDate(LocalDateTime.now());
		
		valueOperations.set(key,test);

		return test;
	}

	public RedisCrud create(String key, String value, long time) {
		RedisCrud test = new RedisCrud();
		test.setContent(value);
		test.setCreatedDate(LocalDateTime.now());
		
		valueOperations.set(key,test, time, TimeUnit.SECONDS);

		return test;
	}
	
	public RedisCrud get(String key) {

		return (RedisCrud) valueOperations.get(key);
	}

	public boolean delete(String key) {
		return redisTemplate.delete(key);
	}
	
	public long deleteAll(List<String> keyList) {
		return redisTemplate.delete(keyList);
	}
	
}
