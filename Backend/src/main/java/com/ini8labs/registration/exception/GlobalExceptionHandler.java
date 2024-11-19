package com.ini8labs.registration.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ini8labs.registration.dto.ErrorResponse;
@ControllerAdvice
public class GlobalExceptionHandler {
	 @ExceptionHandler(RegistrationNotFoundException.class)
	    public ResponseEntity<ErrorResponse> handleRegistrationNotFoundException(RegistrationNotFoundException ex) {
	        ErrorResponse errorResponse = new ErrorResponse("Registration not found", ex.getMessage());
	        errorResponse.setTimestamp(LocalDateTime.now());
	        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	    }
}
