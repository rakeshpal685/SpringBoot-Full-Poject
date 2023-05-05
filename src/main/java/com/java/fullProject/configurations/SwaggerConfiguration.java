package com.java.fullProject.configurations;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//http://localhost:8080/swagger-ui/index.html --Swagger URL
@Configuration
public class SwaggerConfiguration {


    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(
                new Info().version("v1").title("XApp application API")
                        .description("(NOTE: If having Swagger UI issues w/ Chrome then use Firefox instead.)")
                        .license(new License().name("My License"))
                        .version("v16")
                        .contact(new Contact().name("Edi")));
    }

}