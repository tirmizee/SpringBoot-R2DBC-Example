package com.example;

import io.r2dbc.spi.ConnectionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.r2dbc.core.DatabaseClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SpringBootR2DbcAutoPostgresqlApplicationTests {

	@Autowired
	private ConnectionFactory connectionFactory;

	@Autowired
	private DatabaseClient databaseClient;

	@Test
	void should_pool() {
		assertThat(connectionFactory.getClass().getSimpleName()).isEqualTo("ConnectionPool");
	}

	@Test
	void should_nonnull() {
		assertThat(databaseClient).isNotNull();
	}

}
