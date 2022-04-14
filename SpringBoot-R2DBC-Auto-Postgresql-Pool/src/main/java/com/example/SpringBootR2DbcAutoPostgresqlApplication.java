package com.example;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.r2dbc.core.DatabaseClient;

@SpringBootApplication
public class SpringBootR2DbcAutoPostgresqlApplication implements CommandLineRunner {

	@Autowired
	ApplicationContext applicationContext;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootR2DbcAutoPostgresqlApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ConnectionFactory connectionFactory = applicationContext.getBean(ConnectionFactory.class);
		System.out.println(connectionFactory.getClass().getSimpleName());

	}
}
