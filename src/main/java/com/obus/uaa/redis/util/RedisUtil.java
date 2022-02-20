package com.obus.uaa.redis.util;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	public boolean expire(String key, long time) {
		return redisTemplate.expire(key, time, TimeUnit.SECONDS);
	}
	
	public long getExpire(String key) {
		return redisTemplate.getExpire(key, TimeUnit.SECONDS);
	}
	
	public boolean hasKey(String key) {
		return redisTemplate.hasKey(key);
	}
	
	public long incr(String key, long delta) {
		return redisTemplate.opsForValue().increment(key, delta);
	}
	
	public long decr(String key, long delta) {
		return redisTemplate.opsForValue().increment(key, -delta);
	}
	
	
	
}
