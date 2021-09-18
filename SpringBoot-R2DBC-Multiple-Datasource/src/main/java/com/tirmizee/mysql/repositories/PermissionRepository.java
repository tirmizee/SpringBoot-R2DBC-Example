package com.tirmizee.mysql.repositories;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;

import com.tirmizee.mysql.entities.Permission;

public interface PermissionRepository extends ReactiveSortingRepository<Permission, Integer> {

}
