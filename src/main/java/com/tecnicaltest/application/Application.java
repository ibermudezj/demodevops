package com.tecnicaltest.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "com.tecnicaltest.controller", "com.tecnicaltest.business",
		"com.tecnicaltest.model.helper", "com.tecnicaltest.controller.helper", "com.tecnicaltest.application",})
@EnableJpaRepositories(basePackages = { "com.tecnicaltest.repository", })
@EntityScan({ "com.tecnicaltest.model", })
public class Application extends SpringBootServletInitializer   {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	
}
