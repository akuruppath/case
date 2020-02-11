package com.afkl.travel.exercise.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
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
