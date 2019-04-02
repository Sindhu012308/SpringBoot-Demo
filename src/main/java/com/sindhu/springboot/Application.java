package com.sindhu.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		
		ApplicationContext ac = SpringApplication.run(Application.class, args);
	
	}
	
	@Bean
	@Profile("dev")
	public String dummy(){
		return "something!";
	}

}
