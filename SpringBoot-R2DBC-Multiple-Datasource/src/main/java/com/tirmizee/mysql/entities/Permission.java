package com.tirmizee.mysql.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table(value = "PERMISSIONS")
public class Permission {

	@Id
	private Integer id;
	private String perCode;
	private String perName;
	
}
