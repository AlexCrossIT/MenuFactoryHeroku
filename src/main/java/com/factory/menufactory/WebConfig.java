package com.factory.menufactory;


import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.factory.menufactory.converter.StringToShoppingListConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Override
	public void addFormatters(FormatterRegistry registry) {
		
		registry.addConverter(new StringToShoppingListConverter());
		
	}
	
}
