package com.tirmizee.dao;

import com.tirmizee.mysql.entities.User;

public interface UserDao {
	
	User findByUsername(String username);

}
