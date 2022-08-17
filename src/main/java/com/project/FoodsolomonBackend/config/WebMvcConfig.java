package com.project.FoodsolomonBackend.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${resources.location}")
    private String resourcesLocation;

    @Value("${resources.uri_path}")
    private String resourceUriPath;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){

        registry.addResourceHandler(resourceUriPath+"/**")
                .addResourceLocations("file://"+resourcesLocation);

    }



    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins("http://localhost:3000");

    }


}
