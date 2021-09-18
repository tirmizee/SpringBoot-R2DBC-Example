package com.tirmizee.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("employee")
public class Employee {
	
	@Id
    private Long id;
    private String firstName;
    private String lastName;

}
