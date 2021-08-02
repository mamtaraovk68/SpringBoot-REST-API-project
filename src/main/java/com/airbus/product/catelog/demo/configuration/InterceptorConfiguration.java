package com.airbus.product.catelog.demo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.airbus.product.catelog.demo.interceptors.LoggerInterceptor;
import com.airbus.product.catelog.demo.interceptors.RequestProcessingTimeInterceptor;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoggerInterceptor()).addPathPatterns("/**");
		registry.addInterceptor(new RequestProcessingTimeInterceptor()).addPathPatterns("/**");

	}
	

}
