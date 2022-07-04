package com.project.FoodsolomonBackend.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private ApiInfo apiInfo() {
    	return new ApiInfoBuilder()
    			.title("Foodsolomon")
    			.description("Foodsolomon Swagger 입니다.")
    			.version("1.0")
    			.build();
    }
    
    @Bean
    public Docket api() {
    	return new Docket(DocumentationType.SWAGGER_2)
    			.apiInfo(apiInfo())
    			.select()
    			.apis(RequestHandlerSelectors.any())
    			.paths(PathSelectors.any())
    			.build();
    }
}