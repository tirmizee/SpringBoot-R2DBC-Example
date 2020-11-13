package com.tirmizee.config;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.connectionfactory.init.CompositeDatabasePopulator;
import org.springframework.data.r2dbc.connectionfactory.init.ConnectionFactoryInitializer;
import org.springframework.data.r2dbc.connectionfactory.init.ResourceDatabasePopulator;

import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;

@Configuration
public class PostgresConfig {

//    @Bean
//    public ConnectionFactory connectionFactory() {
//
//    	PostgresqlConnectionConfiguration configuration = PostgresqlConnectionConfiguration.builder()
//            .host("127.0.0.1")
//            .port(5432)
//            .schema("schema_postgres")
//            .username("postgres")
//            .password("postgres")
//            .build();
    	
//    	ConnectionPoolConfiguration configurations = ConnectionPoolConfiguration.builder(new PostgresqlConnectionFactory(configuration))
// 			   .maxIdleTime(Duration.ofMillis(1000))
// 			   .maxSize(20)
// 			   .build();
//        return new PostgresqlConnectionFactory(configuration);
//    }
	
	@Bean
	public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
	    
		CompositeDatabasePopulator populator = new CompositeDatabasePopulator();
	    populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
	    populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("data.sql")));
	    
	    ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
	    initializer.setConnectionFactory(connectionFactory);
	    initializer.setDatabasePopulator(populator);
	    return initializer;
	}
	
}
