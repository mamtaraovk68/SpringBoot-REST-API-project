package com.airbus.product.catelog.demo.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


@Component
public class RequestProcessingTimeInterceptor implements HandlerInterceptor{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RequestProcessingTimeInterceptor.class);
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		long startTime = System.currentTimeMillis();
		LOGGER.info(
				"Request URL::" + request.getRequestURL().toString() + ":: Start Time=" + System.currentTimeMillis());
		request.setAttribute("startTime", startTime);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		LOGGER.info("Request URL::" + request.getRequestURL().toString() + " Sent to Handler :: Current Time="
				+ System.currentTimeMillis());

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		long startTime = (Long) request.getAttribute("startTime");
		LOGGER.info("Request URL::" + request.getRequestURL().toString() + ":: End Time=" + System.currentTimeMillis());
		LOGGER.info("Request URL::" + request.getRequestURL().toString() + ":: Time Taken="
				+ (System.currentTimeMillis() - startTime) + " ms");
	}
	

}
