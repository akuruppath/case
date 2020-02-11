package com.afkl.travel.exercise.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.afkl.travel.exercise.adapters.RequestCorrelationIdInterceptor;

@Component
public class ApplicationInterceptorConfig implements WebMvcConfigurer  {

	private final RequestCorrelationIdInterceptor requestInterceptor;
	
	@Autowired
	public ApplicationInterceptorConfig(RequestCorrelationIdInterceptor requestInterceptor) {
		this.requestInterceptor = requestInterceptor;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(requestInterceptor);
	}
}
