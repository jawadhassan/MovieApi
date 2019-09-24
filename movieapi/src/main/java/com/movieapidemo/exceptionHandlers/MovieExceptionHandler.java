package com.movieapidemo.exceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.movieapidemo.entity.MovieErrorResponse;
import com.movieapidemo.exceptions.MovieNotFoundException;

@ControllerAdvice
public class MovieExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<MovieErrorResponse> handleMovieNotFoundException(MovieNotFoundException exc){
																														
		MovieErrorResponse response = new MovieErrorResponse(
				HttpStatus.NOT_FOUND.value(),exc.getMessage(),System.currentTimeMillis());
		
		return new ResponseEntity<MovieErrorResponse>(response, HttpStatus.NOT_FOUND);
	}
	
}
