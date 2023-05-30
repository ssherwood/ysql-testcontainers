package com.yugabyte.labs.ysql.tc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({"test"})
@ExtendWith(IntegrationTestExtension.class)
class YsqlTestcontainersApplicationTests {

    @Test
    void contextLoads() {
    }

}
