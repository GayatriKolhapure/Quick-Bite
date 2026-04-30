package com.skills.qb.exceptions;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> methodArgumentNotValidException(MethodArgumentNotValidException ex){
		List<FieldError> fieldErrors = ex.getFieldErrors();
		HashMap<String, String> errorMap = new HashMap<>();
		
		for(FieldError fieldError : fieldErrors) {
			String field = fieldError.getField();
			String message = fieldError.getDefaultMessage();
			errorMap.put(field, message);
		}
		return errorMap;
	}
	
	@ExceptionHandler(HandlerMethodValidationException.class)
	public List<String> handlerMethodValidationException(HandlerMethodValidationException ex) {
		List<String> errors = ex.getAllValidationResults()
				.stream()
				.flatMap(result -> result.getResolvableErrors().stream())
				.map(error -> error.getDefaultMessage())
				.toList();
		
		return errors;
	}

}
