package com.tirmizee.mysql.repositories;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;

import com.tirmizee.mysql.entities.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveSortingRepository<User, Integer> {

	Mono<User> findByUsername(String username);
	
	Flux<User> findByRoleId(Integer roleId);
	
}
