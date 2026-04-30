package com.skills.qb.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StandardErrorResponse {
	
	private LocalDateTime timestamp = LocalDateTime.now();
	private int status;
	private boolean success = false;
	private String message;

	private Object errors;

	public StandardErrorResponse(int status, String message, Object errors) {
		super();
		this.status = status;
		this.message = message;
		this.errors = errors;
	}
	
	
	
	

	
}
