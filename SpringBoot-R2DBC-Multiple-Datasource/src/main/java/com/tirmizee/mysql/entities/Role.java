package com.tirmizee.mysql.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table(value = "ROLES")
public class Role {

	@Id
	private Integer id;
	private String roleName;
	
}
