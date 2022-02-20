package com.obus.uaa.redis.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.obus.uaa.redis.domain.RedisCrud;
import com.obus.uaa.redis.service.cache.RedisCacheService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "redis/cache", produces = MediaType.APPLICATION_JSON_VALUE)
public class RedisCacheResource {
	private static final String REDIS_CONTENT = "CONTENT";
	
	@Autowired
	private RedisCacheService redisService;
	
	@PutMapping
	public ResponseEntity<RedisCrud> update(@RequestBody String username){
		RedisCrud test = redisService.update(username, "obus");
		
		return ResponseEntity.ok(test);
	}
	
	@GetMapping(value = "{username}")
	public ResponseEntity<RedisCrud> findById(@PathVariable(name = "username") String username){
		RedisCrud test = redisService.getByUsername(username);
		
		return ResponseEntity.ok(test);
	}	
	
	@DeleteMapping(value = "{username}")
	public ResponseEntity<RedisCrud> delete(@PathVariable(value = "username") String username){
		RedisCrud test = redisService.delete(username);
		
		return ResponseEntity.ok(test);
	}
}
