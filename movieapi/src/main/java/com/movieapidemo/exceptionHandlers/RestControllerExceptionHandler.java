package com.movieapidemo.exceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.movieapidemo.entity.MovieErrorResponse;
import com.movieapidemo.exceptions.MovieNotFoundException;

@ControllerAdvice
public class RestControllerExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<MovieErrorResponse> handleMovieNotFoundException(MovieNotFoundException exc) {

		MovieErrorResponse response = new MovieErrorResponse(HttpStatus.NOT_FOUND.value(), exc.getMessage(),
				System.currentTimeMillis());

		return new ResponseEntity<MovieErrorResponse>(response, HttpStatus.NOT_FOUND);
	}

//	public ResponseEntity<RatingErrorResponse> handleInvalidRatingException(RatingException exc) {
//		RatingErrorResponse response = new RatingErrorResponse(HttpStatus.NOT_FOUND.value(), exc.getMessage(),
//				System.currentTimeMillis());
//
//		return new ResponseEntity<RatingErrorResponse>(response, HttpStatus.NOT_FOUND);
//	}

	/*
	 * @ResponseStatus(HttpStatus.BAD_REQUEST)
	 * 
	 * @ExceptionHandler(MethodArgumentNotValidException.class) public Map<String,
	 * String> handleValidationExceptions(MethodArgumentNotValidException e) {
	 * Map<String, String> errors = new HashMap<String, String>();
	 * 
	 * e.getBindingResult().getAllErrors().forEach((error) -> { String fieldName =
	 * ((FieldError) error).getField(); String errorMessage = ((FieldError)
	 * error).getDefaultMessage(); errors.put(fieldName, errorMessage); });
	 * 
	 * return errors;
	 * 
	 * }
	 */

}
