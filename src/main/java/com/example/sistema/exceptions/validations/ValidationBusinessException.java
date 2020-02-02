package com.example.sistema.exceptions.validations;

import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.sistema.exceptions.BusinessException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationBusinessException extends BusinessException{
	
	private static final long serialVersionUID = 1L;
	
	private final Map<String, Set<String>> errors;

	public ValidationBusinessException(String message, Map<String, Set<String>> errors) {
		super(message);
		this.errors = errors;
	}
	
	public Map<String, Set<String>> getErrors(){
		return this.errors;
	}



}
