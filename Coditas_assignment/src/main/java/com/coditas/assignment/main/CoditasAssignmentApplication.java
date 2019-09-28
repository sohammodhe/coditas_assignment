package com.coditas.assignment.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import com.coditas.assignment.controller.SearchRepositoriesApiController;
import com.coditas.assignment.properties.MiddlewareServiceProperties;
import com.coditas.assignment.service.MiddlewareApiService;
import com.coditas.assignment.service.SearchRepositoriesMWApiServiceImpl;

@SpringBootApplication
@ComponentScan(basePackageClasses = SearchRepositoriesApiController.class)
public class CoditasAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoditasAssignmentApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	@Bean(name = "searchReposMWApiService")
	public MiddlewareApiService getMiddlewareApiService() {
		return new SearchRepositoriesMWApiServiceImpl(); 
	}
	
	@Bean
	public MiddlewareServiceProperties getMiddlewareServiceProperties() {
		return new MiddlewareServiceProperties(); 
	}
}
