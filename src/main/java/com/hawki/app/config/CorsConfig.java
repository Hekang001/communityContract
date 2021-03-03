package com.hawki.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET","HEAD","POST","PUT","DELETE","OPTIONS")
                // 是否允许发送Cookie
                .allowCredentials(true)
                // 本次预检请求的有效期，单位为秒
                .maxAge(3600)
                .allowedHeaders("*");
    }

}