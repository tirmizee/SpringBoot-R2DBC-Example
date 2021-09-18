package com.tirmizee.dao;

import javax.management.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;

import com.tirmizee.mysql.entities.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private DatabaseClient mysqlClient;
	
	@Autowired
	private R2dbcEntityTemplate mysqlEntityTemplate;

	@Override
	public User findByUsername(String username) {
		return null;
	}
	
}
