package com.wecat.small.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.wecat.small.utils.DateConverter;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	@Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new DateConverter());
    }
}
