package com.obus.uaa.redis.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obus.uaa.redis.domain.RedisCrud;
import com.obus.uaa.redis.repository.RedisCrudRepository;

@Service
public class RedisCrudService {

	@Autowired
	private RedisCrudRepository testRepository;
	
	public RedisCrud save(String username) {
		RedisCrud test = new RedisCrud();
		test.setContent(username);
		test.setCreatedDate(LocalDateTime.now());

		return save(test);
	}
	
	public RedisCrud save(RedisCrud test) {
		
		return testRepository.save(test);
	}
	
	public RedisCrud findByUsername(String username) {

		return testRepository.findByContent(username).get();
	}
	
	public List<RedisCrud> findAll() {

		List<RedisCrud> testList = new ArrayList<>();
		
		testRepository.findAll().forEach(testList::add);
		
		return testList;
	}
	
	public RedisCrud delete(String username) {
		RedisCrud test = testRepository.findByContent(username).get();
		
		testRepository.delete(test);
		
		return test;
	}
}
