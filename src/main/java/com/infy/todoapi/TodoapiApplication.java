package com.infy.todoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author raghava
 * This is REST API to todo
 * omitted the service layer as this is smaller application.
 * For bigger apis will add service layer and separate request model
 * 
 *
 */

@SpringBootApplication
@EnableSwagger2
public class TodoapiApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(TodoapiApplication.class, args);
	}
	
	@Bean
	public Docket getProductApi() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.infy.todoapi")).build();
	}


}
