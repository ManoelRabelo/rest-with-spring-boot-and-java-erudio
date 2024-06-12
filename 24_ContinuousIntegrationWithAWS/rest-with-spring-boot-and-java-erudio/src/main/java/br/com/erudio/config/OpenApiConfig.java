package br.com.erudio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenApi() {
		return new OpenAPI()
				.info(new Info()
						.title("RESTful API with Java 19 and Spring Boot 3 - Foo Bar")
						.version("v1")
						.description("Some description about your API")
						.termsOfService("https://github.com/ManoelRabelo/rest-with-spring-boot-and-java-erudio")
						.license(
								new License()
									.name("Apache 2.0")
									.url("https://github.com/ManoelRabelo/rest-with-spring-boot-and-java-erudio")
								)
						);
		
	}
}