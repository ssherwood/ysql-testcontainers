package com.yugabyte.labs.ysql.tc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({"test", "testcontainer-defaults"})
class YsqlTestcontainersApplicationTests {

	@Test
	void contextLoads() {
	}

}
