package com.afkl.travel.exercise.adapters;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RequestCorrelationIdInterceptor implements HandlerInterceptor {

	private static final String CORRELATION_ID_HEADER_NAME = "X-Correlation-Id";
	private static final String CORRELATION_ID_LOG_VAR_NAME = "correlationId";
	private static final Logger LOGGER = LoggerFactory.getLogger(RequestCorrelationIdInterceptor.class);

	@Override
	public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
			throws Exception {
		final String correlationId = getCorrelationIdFromHeader(request);
		MDC.put(CORRELATION_ID_LOG_VAR_NAME, correlationId);
		LOGGER.info("Request URL: {}", request.getRequestURL());
		return true;
	}

	@Override
	public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response,
			final Object handler, final Exception ex) throws Exception {
		if (response != null) {
			LOGGER.info("Response : {}", response.getStatus());
		}
		MDC.remove(CORRELATION_ID_LOG_VAR_NAME);
	}

	/*
	 * Checks this in the header. Ideally if coming from another micro-service this
	 * header should be populated.
	 */
	private String getCorrelationIdFromHeader(final HttpServletRequest request) {
		String correlationId = request.getHeader(CORRELATION_ID_HEADER_NAME);
		return StringUtils.isBlank(correlationId) ? UUID.randomUUID().toString() : correlationId;
	}

}
