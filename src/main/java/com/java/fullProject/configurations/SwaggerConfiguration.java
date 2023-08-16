package com.java.fullProject.configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

// Remember to add the swagger url's to the security ant matchers, to let people from outside access
/*
The Swagger UI page will then be available at http://localhost:8080/swagger-ui/index.html
and the OpenAPI description will be available at the following url for json format: http://localhost:8080/v3/api-docs
*/

// @EnableSwagger2-  Old way

/*
Using openApi I can customize my swagger page in two ways
1. create a class and use @OpenAPIDefinition and give all the information in annotation like shown below in approach 1.
2. create a configuration class and mark it as @Configuration then create a @Bean of OpenAPI and return the details as shown below.
*/

// @Configuration// use this if using approach 2.

// 1st approach
@OpenAPIDefinition(
    info =
        @Info(
            description = "(NOTE: If having Swagger UI issues w/ Chrome then use Firefox instead.)",
            title = "XApp application API",
            version = "v1",
            summary = "Hello this is swagger for employee service",
            // extensions = "Kuch Bhi",
            termsOfService = "kuch nahi hai",
            license = @License(name = "Rakesh Inc", url = "https://pompom.com/"),
            contact =
                @Contact(name = "Rakesh", email = "rakesh@gmail.com", url = "http://abc.com")),
    servers = {
      @Server(description = "Local ENV", url = "http://localhost:8080"),
      @Server(description = "PROD ENV", url = "http://abc.com")
    },
    security = {
      @SecurityRequirement(name = "bearerAuth")
    }) // This is done to say that all my API are protected by security using the @SecurityScheme
       // declared below.

/*This is given so that we can provide our JWT token to access the url in swagger, to use security on specific url, go to controller and use
@SecurityRequirement(name="bearerAuth") on top of the controller, and to provide security for all the controllers  type security like above*/
@SecurityScheme(
    name = "bearerAuth",
    description = "JWT auth description",
    scheme = "bearer",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    in = SecuritySchemeIn.HEADER)
public class SwaggerConfiguration {

  /*//  2nd Approach
  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(
            new Info()
                .version("v1")
                .title("XApp application API")
                .description(
                    "(NOTE: If having Swagger UI issues w/ Chrome then use Firefox instead.)")
                .license(new License().name("My License"))
                .version("v16")
                .contact(new Contact().name("Edi")));
  }*/

  /*  Old way to configure swagger

  ApiInfo apiInfo = new ApiInfoBuilder()
            .title("XApp application API")
            .version("v1")
            .description("(NOTE: If having Swagger UI issues w/ Chrome then use Firefox instead.)")
            .license("My License")
            .licenseUrl("http://www.rakesh.com")
            .build();

    @Bean
    public Docket docket() {
      return new Docket(DocumentationType.SWAGGER_2)
              .select()
              .apis(RequestHandlerSelectors.basePackage("com.java.fillProject"))
              .paths(PathSelectors.any())
              .build()
              .apiInfo(apiInfo);
    }*/
}
