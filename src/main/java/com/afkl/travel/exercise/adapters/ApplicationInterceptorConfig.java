package com.afkl.travel.exercise.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApplicationInterceptorConfig implements WebMvcConfigurer  {

	private final RequestInterceptor requestInterceptor;
	
	@Autowired
	public ApplicationInterceptorConfig(RequestInterceptor requestInterceptor) {
		this.requestInterceptor = requestInterceptor;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(requestInterceptor);
	}
}
