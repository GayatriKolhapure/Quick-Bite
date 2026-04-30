package com.skills.qb.exceptions;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import com.skills.qb.response.StandardErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public StandardErrorResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex){
		List<FieldError> fieldErrors = ex.getFieldErrors();
		HashMap<String, String> errorMap = new HashMap<>();
		
		for(FieldError fieldError : fieldErrors) {
			String field = fieldError.getField();
			String message = fieldError.getDefaultMessage();
			errorMap.put(field, message);
		}
		
		StandardErrorResponse response = new StandardErrorResponse(HttpStatus.BAD_REQUEST.value(), "Validation Failed", errorMap);
		
		return response;
	}
	
	@ExceptionHandler(HandlerMethodValidationException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public StandardErrorResponse handleMethodValidation(HandlerMethodValidationException ex) {
		List<String> errors = ex.getAllValidationResults()
				.stream()
				.flatMap(result -> result.getResolvableErrors().stream())
				.map(error -> error.getDefaultMessage())
				.toList();
		
		return new StandardErrorResponse(HttpStatus.BAD_REQUEST.value(), "Validation Failed", errors);
	}

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardErrorResponse> handleGeneric(Exception ex) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        		.body(new StandardErrorResponse(500, "Something went wromg", ex.getMessage()));
    }

}
