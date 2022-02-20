package com.obus.uaa.redis.service.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.obus.uaa.redis.domain.RedisCrud;
import com.obus.uaa.redis.service.RedisCrudService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RedisCacheService {
	private static final String REDIS_CACHE = "OBUS_CACHE";
	
	@Autowired
	private RedisCrudService redisCrudService;
	
	@CacheEvict(value = REDIS_CACHE, key = "'obus:'+#username")
	public RedisCrud update(String username, String content) {
		log.info("Update cacheEvict example service -> {}", username);
		
		RedisCrud redisCrud = redisCrudService.findByUsername(username);
		redisCrud.setContent(content);
		
		return redisCrudService.save(redisCrud);		
	}
	
	@CacheEvict(value = REDIS_CACHE, key = "'obus:'+#username")
	public RedisCrud delete(String username) {
		log.info("Delete cacheEvict example service -> {}", username);
		
		return redisCrudService.delete(username);		
	}
	
	@Cacheable(value = REDIS_CACHE, key = "'obus:'+#username", unless = "#result==null")
	public RedisCrud getByUsername(String username) {
		log.info("Get by username cacheable example service -> {}", username);
		
		return redisCrudService.findByUsername(username);		
	}
	
	
}
