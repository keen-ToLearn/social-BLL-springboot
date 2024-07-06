package com.palamahen.app.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> genericExceptionHandler(Exception ex, WebRequest req) {
		
		ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), req.getDescription(false), LocalDateTime.now());
		
		ResponseEntity<ErrorDetails> errorResponse = new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
		return errorResponse;
	}
}
