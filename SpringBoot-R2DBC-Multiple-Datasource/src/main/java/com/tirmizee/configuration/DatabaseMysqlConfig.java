package com.tirmizee.configuration;

import java.time.Duration;
import java.util.WeakHashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.tirmizee.utils.CommonUtils;

import dev.miku.r2dbc.mysql.MySqlConnectionFactoryProvider;
import io.r2dbc.pool.PoolingConnectionFactoryProvider;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import io.r2dbc.spi.Option;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@EnableTransactionManagement 
@ConfigurationProperties("mysql.r2dbc")
@PropertySource("classpath:database/database-${spring.profiles.active}.properties")
@EnableR2dbcRepositories(basePackages = "com.tirmizee.mysql", entityOperationsRef = "mysqlEntityTemplate")
public class DatabaseMysqlConfig {
	
	public String host;
	public int port;
	public String parameter;
	public String username;
	public String password;
	public String database;
	public String poolName;
	public int poolInitialSize;
	public int poolMaxSize;
	public int poolMaxIdleTime;
	private boolean initialEnabled;
	private Resource initialData;
	private Resource initialSchema;
	
	@Bean(name = "mysqlConnectionFactory")
	public ConnectionFactory connectionFactory() {
		ConnectionFactoryOptions.Builder options = ConnectionFactoryOptions.builder()
		   .option(ConnectionFactoryOptions.DRIVER, "pool")
		   .option(ConnectionFactoryOptions.PROTOCOL, MySqlConnectionFactoryProvider.MYSQL_DRIVER) 
		   .option(ConnectionFactoryOptions.HOST, host) 
		   .option(ConnectionFactoryOptions.PORT, port) 
		   .option(ConnectionFactoryOptions.USER, username) 
		   .option(ConnectionFactoryOptions.PASSWORD, password)
		   .option(ConnectionFactoryOptions.DATABASE, database)
		   .option(PoolingConnectionFactoryProvider.POOL_NAME, poolName) 
		   .option(PoolingConnectionFactoryProvider.INITIAL_SIZE, poolInitialSize)
		   .option(PoolingConnectionFactoryProvider.MAX_SIZE, poolMaxSize)
		   .option(PoolingConnectionFactoryProvider.MAX_IDLE_TIME, Duration.ofMillis(poolMaxIdleTime));
		if (StringUtils.isNotBlank(parameter)) {
			WeakHashMap<String, String> map = CommonUtils.splitToMap(parameter, "&", "=");
			map.forEach((k, v) -> options.option(Option.valueOf(k), v));
		}
		return ConnectionFactories.get(options.build());
	}
	
	@Bean(name = "mysqlInitializer")
	public ConnectionFactoryInitializer initializer(
			@Qualifier("mysqlConnectionFactory") ConnectionFactory connectionFactory) {
		CompositeDatabasePopulator populator = new CompositeDatabasePopulator();
	    populator.addPopulators(new ResourceDatabasePopulator(initialSchema));
	    populator.addPopulators(new ResourceDatabasePopulator(initialData));
	    ConnectionFactoryInitializer factoryInitializer = new ConnectionFactoryInitializer();
	    factoryInitializer.setConnectionFactory(connectionFactory);
	    factoryInitializer.setDatabasePopulator(populator);
	    factoryInitializer.setEnabled(initialEnabled);
	    return factoryInitializer;
	}
	
	@Bean(name = "mysqlTransactionManager")
	public ReactiveTransactionManager transactionManager(
			@Qualifier("mysqlConnectionFactory") ConnectionFactory connectionFactory) { 
		return new R2dbcTransactionManager(connectionFactory);
	}

	@Bean(name = "mysqlClient")
	public DatabaseClient databaseClient(
			@Qualifier("mysqlConnectionFactory") ConnectionFactory connectionFactory) {
		return DatabaseClient.builder().connectionFactory(connectionFactory).build();
	}

	@Bean(name = "mysqlEntityTemplate")
	public R2dbcEntityTemplate r2dbcEntityTemplate(
			@Qualifier("mysqlConnectionFactory") ConnectionFactory connectionFactory) {
		return  new R2dbcEntityTemplate(connectionFactory);
	}

}
