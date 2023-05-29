package com.yugabyte.labs.ysql.tc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class YsqlTestcontainersApplication {

	public static void main(String[] args) {
		SpringApplication.run(YsqlTestcontainersApplication.class, args);
	}

}
