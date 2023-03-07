package com.portfolioehadad.portfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.portfolioehadad.portfolio")
@EntityScan(basePackages = "com.portfolioehadad.portfolio.models")
@EnableJpaRepositories( basePackages = "com.portfolioehadad.portfolio.repository")
@EnableAutoConfiguration
public class PortfolioApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(PortfolioApplication.class, args);
	}

}
