package com.afkl.travel.exercise.custom.exception.response;

import java.util.Date;

public class ExceptionResponse {

	private final Date timeStamp;
	private final String message;
	private int httpStatus;
	
	public ExceptionResponse(Date timeStamp, String message, int httpStatus) {
		this.timeStamp = timeStamp;
		this.message = message;
		this.httpStatus = httpStatus;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public String getMessage() {
		return message;
	}

	public int getHttpStatus() {
		return httpStatus;
	}
}
