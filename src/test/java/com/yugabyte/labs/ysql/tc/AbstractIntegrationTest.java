package com.yugabyte.labs.ysql.tc;

import org.flywaydb.test.FlywayTestExecutionListener;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.testcontainers.containers.YugabyteDBYSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@ActiveProfiles({"test"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, FlywayTestExecutionListener.class})
@Testcontainers
public class AbstractIntegrationTest {
    static final String YUGABYTEDB_DOCKER_IMAGE = "yugabytedb/yugabyte:2.16.3.0-b43";
    static final String ENTRYPOINT = "bin/yugabyted start --background=false --tserver_flags=yb_enable_read_committed_isolation=true";

    @Container
    static final YugabyteDBYSQLContainer ysqlDB = new YugabyteDBYSQLContainer(YUGABYTEDB_DOCKER_IMAGE).withCommand(ENTRYPOINT);

    @DynamicPropertySource
    static void datasourceProps(final DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", ysqlDB::getJdbcUrl);
        registry.add("spring.datasource.username", ysqlDB::getUsername);
        registry.add("spring.datasource.password", ysqlDB::getPassword);
        registry.add("spring.datasource.driver-class-name", ysqlDB::getDriverClassName);
    }
}
