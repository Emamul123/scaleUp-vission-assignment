package com.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.boot.dao.UserRepository;

@SpringBootApplication
public class SpringBootWebApplicationWithInMemoryApplication implements CommandLineRunner {
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebApplicationWithInMemoryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Student id 10001 -> {}", repository.findByEmail("j@gmail.com"));

	}
}
