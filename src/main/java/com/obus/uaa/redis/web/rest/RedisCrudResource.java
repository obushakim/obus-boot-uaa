package com.obus.uaa.redis.web.rest;

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
import com.obus.uaa.redis.service.RedisCrudService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "redis/crud", produces = MediaType.APPLICATION_JSON_VALUE)
public class RedisCrudResource {
	private static final String REDIS_CONTENT = "CONTENT";
	
	@Autowired
	private RedisCrudService testService;
	
	@Autowired
	private HttpSession httpSession;
	
	@PostMapping
	public ResponseEntity<RedisCrud> save(@RequestBody String username){
		RedisCrud test = testService.save(username);

		httpSession.setAttribute(REDIS_CONTENT, test.getContent());
		
		return ResponseEntity.ok(test);
	}
	
	@GetMapping(value = "{username}")
	public ResponseEntity<RedisCrud> findByUsername(@PathVariable(name = "username") String username){
		RedisCrud test = testService.findByUsername(username);
		
		String content = (String) httpSession.getAttribute(REDIS_CONTENT);
		
		log.info("CONTENT ======> {}", content);
		
		return ResponseEntity.ok(test);
	}
	
}
