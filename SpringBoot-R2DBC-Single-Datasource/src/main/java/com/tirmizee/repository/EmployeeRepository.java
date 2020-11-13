package com.tirmizee.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

import com.tirmizee.entity.Employee;

import reactor.core.publisher.Flux;

public interface EmployeeRepository extends ReactiveSortingRepository<Employee, Long> {
	
	@Query("SELECT * FROM customer WHERE last_name = :lastname")
    Flux<Employee> findByLastName(String lastName);

}
