package com.airbus.product.catelog.demo.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoggerInterceptor implements HandlerInterceptor {
	

private static Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);

@Override
public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
	logger.info("Handling request started with request url: " + request.getRequestURI());
	return true;
}

}