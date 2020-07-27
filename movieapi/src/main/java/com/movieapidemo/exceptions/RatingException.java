package com.movieapidemo.exceptions;

public class RatingException extends RuntimeException {

	public RatingException(String message, Throwable cause) {
		super(message, cause);

	}

	public RatingException(Throwable cause) {
		super(cause);

	}

	public RatingException(String message) {
		super(message);

	}

}
