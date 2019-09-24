package com.movieapidemo.exceptions;

public class MovieNotFoundException extends RuntimeException {

	public MovieNotFoundException(String message, Throwable cause) {
		super(message, cause);
	
	}

	public MovieNotFoundException(String message) {
		super(message);
	
	}

	public MovieNotFoundException(Throwable cause) {
		super(cause);
	
	}
	
	
	

	
	
}
