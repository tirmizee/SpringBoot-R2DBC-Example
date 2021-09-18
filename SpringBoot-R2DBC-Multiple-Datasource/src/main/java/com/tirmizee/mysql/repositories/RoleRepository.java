package com.tirmizee.mysql.repositories;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;

import com.tirmizee.mysql.entities.Role;

public interface RoleRepository extends ReactiveSortingRepository<Role, Integer> {

}
