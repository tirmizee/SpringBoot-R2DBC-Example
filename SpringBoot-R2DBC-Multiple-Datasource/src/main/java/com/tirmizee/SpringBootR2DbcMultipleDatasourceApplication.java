package com.tirmizee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.util.Assert;

import com.tirmizee.mysql.entities.RolePermission;
import com.tirmizee.mysql.entities.User;
import com.tirmizee.mysql.repositories.RolePermissionRepository;
import com.tirmizee.mysql.repositories.UserRepository;

import io.r2dbc.spi.ConnectionFactory;

@SpringBootApplication
public class SpringBootR2DbcMultipleDatasourceApplication implements CommandLineRunner {

	@Autowired
	ApplicationContext applicationContext;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootR2DbcMultipleDatasourceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		R2dbcEntityTemplate postgesEntityTemplate = applicationContext.getBean("postgesEntityTemplate", R2dbcEntityTemplate.class);
		R2dbcEntityTemplate mysqlEntityTemplate = applicationContext.getBean("mysqlEntityTemplate", R2dbcEntityTemplate.class);
		R2dbcEntityTemplate mssqlEntityTemplate = applicationContext.getBean("mssqlEntityTemplate", R2dbcEntityTemplate.class);
		
		ConnectionFactory mysqlConnectionFactory = applicationContext.getBean("mysqlConnectionFactory", ConnectionFactory.class);
		ConnectionFactory mssqlConnectionFactory = applicationContext.getBean("mssqlConnectionFactory", ConnectionFactory.class);
		ConnectionFactory postgesConnectionFactory = applicationContext.getBean("postgesConnectionFactory", ConnectionFactory.class);
		
		DatabaseClient postgesClient = applicationContext.getBean("postgesClient", DatabaseClient.class);
		DatabaseClient mysqlClient = applicationContext.getBean("mysqlClient", DatabaseClient.class);
		DatabaseClient mssqlClient = applicationContext.getBean("mssqlClient", DatabaseClient.class);
		
		UserRepository userRepository = applicationContext.getBean(UserRepository.class);
		RolePermissionRepository rolePermissionRepository = applicationContext.getBean(RolePermissionRepository.class);
		
		Assert.notNull(postgesClient, "postgesClient is null");
		Assert.notNull(mysqlClient, "mysqlClient is null");
		Assert.notNull(mssqlClient, "mssqlClient is null");
		Assert.notNull(postgesEntityTemplate, "postgesEntityTemplate is null");
		Assert.notNull(mysqlEntityTemplate, "mysqlEntityTemplate is null");
		Assert.notNull(mssqlEntityTemplate, "mssqlEntityTemplate is null");
		Assert.notNull(mysqlConnectionFactory, "mysqlConnectionFactory is null");
		Assert.notNull(mssqlConnectionFactory, "mssqlConnectionFactory is null");
		Assert.notNull(postgesConnectionFactory, "postgesConnectionFactory is null");
		
		System.out.println(userRepository.findByUsername("pratya").block());
		
		List<User> users = userRepository.findAll().collectList().block();
		for (User user : users) {
			System.out.println(user);
		}
		
		RolePermission rolePermission = new RolePermission();
		rolePermission.setPersisted(false);
		rolePermission.setRoleId(2);
		rolePermission.setPerId(3);
		rolePermissionRepository.save(rolePermission);
		
	}

}
