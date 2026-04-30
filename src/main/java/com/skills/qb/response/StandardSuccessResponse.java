package com.skills.qb.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StandardSuccessResponse {
	
	private LocalDateTime timestamp=LocalDateTime.now();
    private int status;
    private String message;
    private Object data;
    
	public StandardSuccessResponse(int status, String message, Object data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}
    
    
    
}
