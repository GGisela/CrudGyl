package com.gyl.CrudGyl;

import com.gyl.CrudGyl.entity.Role;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudGylApplication {
Role r = Role.USER;
	public static void main(String[] args) {
		SpringApplication.run(CrudGylApplication.class, args);
	}

}
