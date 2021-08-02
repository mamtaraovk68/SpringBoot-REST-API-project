package com.airbus.product.catelog.demo.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import springfox.documentation.service.Parameter;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguraton {

	 @Bean
	  public Docket api() { 
		 
	        return new Docket(DocumentationType.SWAGGER_2)
	                .apiInfo(apiInfo())
	                .select()
	                .apis(RequestHandlerSelectors.any()) // Any RequestHandler satisfies this condition
	                .paths(PathSelectors.any()) // Any path satisfies this condition
	                .build();	
	                                                  
	    }
	 
	 
	 
	 private ApiInfo apiInfo() {
	        return new ApiInfoBuilder() // Builds the api information
	                .title("Spring Boot App")
	                .description("")
	                .version("1.0.0")
	                .build();
	    }
	 
}
