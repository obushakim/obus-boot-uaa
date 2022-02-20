package com.obus.uaa.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories
public class RedisConfig {

	@Value("${spring.redis.host}")
	private String host;
	
	@Value("${spring.redis.port}")
	private int port;
	
	@Value("${spring.redis.database}")
	private int database;
	
	@Value("${spring.redis.password}")
	private String password;
	
	@Value("${spring.redis.timeout}")
	private int timeout;
	
//	@Bean
//	public JedisConnectionFactory jedisConnectionFactory() {
//		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
//		redisStandaloneConfiguration.setHostName(host);
//		redisStandaloneConfiguration.setPort(port);
//		redisStandaloneConfiguration.setDatabase(database);
//		redisStandaloneConfiguration.setPassword(password);
//		
//		JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
////		jedisClientConfiguration.connectTimeout(Duration.ofSeconds(60)); // connection timeout
//		jedisClientConfiguration.connectTimeout(Duration.ofMillis(timeout)); // connection timeout
//		
//		return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration.build());
//	}
	
	@Bean
	public RedisTemplate<String, Object> redisTemplate(
			RedisConnectionFactory redisConnectionFactory){
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		
		return template;
	}
	
//	@Bean
//	public RedisTemplate<String, Object> redisTemplate(){
//		RedisTemplate<String, Object> template = new RedisTemplate<>();
//		template.setConnectionFactory(jedisConnectionFactory());
//		
//		return template;
//	}
}
