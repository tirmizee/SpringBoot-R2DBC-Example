package com.tirmizee.mysql.repositories;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;

import com.tirmizee.mysql.entities.RolePermission;

public interface RolePermissionRepository extends ReactiveSortingRepository<RolePermission, Integer[]> {

}
