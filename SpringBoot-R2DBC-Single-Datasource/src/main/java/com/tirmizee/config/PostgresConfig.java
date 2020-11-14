package com.tirmizee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.connectionfactory.init.CompositeDatabasePopulator;
import org.springframework.data.r2dbc.connectionfactory.init.ConnectionFactoryInitializer;
import org.springframework.data.r2dbc.connectionfactory.init.ResourceDatabasePopulator;

import io.r2dbc.spi.ConnectionFactory;

@Configuration
public class PostgresConfig {

	public static final String SCRIPT_SCHEMA = "schema.sql";
	public static final String SCRIPT_DATA = "data.sql";
	
	@Bean
	public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
	    
		ClassPathResource schemaResource = new ClassPathResource(SCRIPT_SCHEMA);
		ClassPathResource dataResource = new ClassPathResource(SCRIPT_DATA);
		
		CompositeDatabasePopulator populator = new CompositeDatabasePopulator();
	    populator.addPopulators(new ResourceDatabasePopulator(schemaResource));
	    populator.addPopulators(new ResourceDatabasePopulator(dataResource));
	    
	    ConnectionFactoryInitializer factoryInitializer = new ConnectionFactoryInitializer();
	    factoryInitializer.setConnectionFactory(connectionFactory);
	    factoryInitializer.setDatabasePopulator(populator);
	    return factoryInitializer;
	}
	
}
