package com.movieapidemo.entity;

import java.io.Serializable;

public class ApiResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2962902841029869460L;
	private int status;
	private String message;
	private long timeStamp;

	public ApiResponse() {
	}

	public ApiResponse(int status, String message, long timeStamp) {
		this.status = status;
		this.message = message;
		this.timeStamp = timeStamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "ApiResponse [status=" + status + ", message=" + message + ", timeStamp=" + timeStamp + "]";
	}

}
