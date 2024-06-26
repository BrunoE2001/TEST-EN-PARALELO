package org.skyline.example.testing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
//@ComponentScan("org.skyline.example")
//@ComponentScan("org.skyline.example.testing.util")
//@ComponentScan("org.skyline.example.testing.validators")
public class ExampleTestingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExampleTestingApplication.class, args);
	}

}
