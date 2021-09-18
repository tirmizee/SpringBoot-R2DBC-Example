package com.tirmizee.mysql.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table(value = "ROLE_PERMISSION")
public class RolePermission implements Persistable<Integer[]>{

	private transient boolean persisted;
	
	@Id
	private Integer roleId;
	private Integer perId;
	
	
	@Override
	public Integer[] getId() {
		return new Integer[] {roleId, perId};
	}
	
	@Override
	public boolean isNew() {
		return !persisted;
	}
	
}
