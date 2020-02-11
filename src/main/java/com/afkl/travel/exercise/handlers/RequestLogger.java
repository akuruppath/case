//package com.afkl.travel.exercise.handlers;
//
//import java.lang.reflect.Type;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.core.MethodParameter;
//import org.springframework.http.HttpInputMessage;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;
//
//public class RequestLogger extends RequestBodyAdviceAdapter {
//
//	private final Logger LOGGER = LoggerFactory.getLogger(RequestLogger.class);
//
//	@Override
//	public boolean supports(MethodParameter methodParameter, Type targetType,
//			Class<? extends HttpMessageConverter<?>> converterType) {
//		return true;
//	}
//
//	@Override
//	public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
//			Class<? extends HttpMessageConverter<?>> converterType) {
//
//		logger.debug(httpServletRequest, body);
//
//		return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
//	}
//}
