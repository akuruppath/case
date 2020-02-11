package com.afkl.travel.exercise.handlers;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.afkl.travel.exercise.custom.exception.response.ExceptionResponse;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);
	
	@org.springframework.web.bind.annotation.ExceptionHandler(IllegalArgumentException.class)
	protected HttpEntity<ExceptionResponse> handleIllegalArgument(final IllegalArgumentException e) {
		ExceptionResponse response = new ExceptionResponse(new Date(), e.getMessage(), HttpStatus.BAD_REQUEST.value());
		LOGGER.error(response.toString(), e);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
