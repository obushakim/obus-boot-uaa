package com.obus.uaa.redis.web.rest;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.obus.uaa.redis.domain.RedisCrud;
import com.obus.uaa.redis.service.RedisHashService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "redis/hash", produces = MediaType.APPLICATION_JSON_VALUE)
public class RedisHashResource {
	private static final String REDIS_CONTENT = "CONTENT";
	
	@Autowired
	private RedisHashService redisService;
	
	@Autowired
	private HttpSession httpSession;
	
	@PostMapping
	public ResponseEntity<RedisCrud> save(@RequestBody String username){
		RedisCrud test = redisService.create(username);
		
		return ResponseEntity.ok(test);
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<RedisCrud> findById(@PathVariable(name = "id") String id){
		RedisCrud test = redisService.get(UUID.fromString(id));
		
		return ResponseEntity.ok(test);
	}
	
	@GetMapping
	public ResponseEntity<Map<UUID,RedisCrud>> getAll(){
		Map<UUID, RedisCrud> test = redisService.getAll();
		
		return ResponseEntity.ok(test);
	}
	
}
