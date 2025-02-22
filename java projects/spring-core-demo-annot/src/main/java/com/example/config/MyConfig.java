package com.example.config;

import java.time.LocalDateTime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("com.example.service")
@PropertySource("application.properties")
public class MyConfig {
	@Bean
	@Scope("prototype")
	public LocalDateTime today(){
		return LocalDateTime.now();
	}
	
}
