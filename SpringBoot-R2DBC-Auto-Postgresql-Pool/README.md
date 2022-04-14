### dependencies

    implementation 'org.springframework.boot:spring-boot-starter-data-r2dbc'
    runtimeOnly 'io.r2dbc:r2dbc-postgresql'

### docker-compose.yaml

```yaml

version: '3.1'

services:
  db:
    image: postgres:14.1-alpine
    environment:
      POSTGRES_USER: usr
      POSTGRES_PASSWORD: pass
      POSTGRES_DB: test_db
    ports:
      - '5432:5432'
    volumes:
      - ./scripts/:/docker-entrypoint-initdb.d/

```

### application.yaml

```yaml

spring:
  r2dbc:
    url: r2dbc:postgresql://0.0.0.0:5432/test_db?schema=schema_sit
    username: usr
    password: pass
    pool:
      enabled: true    # default true
      initialSize: 5
      maxSize: 10
      validationQuery: SELECT 1

logging.level:
    org.springframework.r2dbc: DEBUG


```

### demo

```java

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

```