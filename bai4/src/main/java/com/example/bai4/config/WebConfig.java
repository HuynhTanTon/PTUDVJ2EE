package com.example.bai4.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String imagesPath = Paths.get("src/main/resources/static/images").toAbsolutePath().toString();
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:" + imagesPath + "/");
    }
}