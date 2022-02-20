package com.obus.uaa.redis.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.obus.uaa.redis.domain.RedisCrud;

@Repository
public interface RedisCrudRepository extends CrudRepository<RedisCrud, UUID> {

	Optional<RedisCrud> findByContent(String content);
}
