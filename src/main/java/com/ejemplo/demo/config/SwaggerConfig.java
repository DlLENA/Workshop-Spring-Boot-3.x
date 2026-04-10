package com.ejemplo.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {
	
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Workshop API - Spring Boot")
						.version("1.0.0")
						.description("Documentacion interactiva de la API para la clase/laboratorio ")
						.contact(new Contact()
								.name("Dilena Irene Grijalva Tenas ♥")
								.email("dgrijalvat1@miumg.edu.gt")));
	}

}
