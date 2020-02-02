package com.example.sistema.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.sistema.exceptions.BusinessException;
import com.example.sistema.exceptions.validations.ValidationBusinessException;
import com.example.sistema.utils.exceptions.dto.ErrorDetailDto;

@ControllerAdvice
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler({ BusinessException.class })
	public ResponseEntity<?> handleBusinessException(final BusinessException ex){
		
		final ResponseStatus responseStatus = ex.getClass().getAnnotation(ResponseStatus.class);
		
		return new ResponseEntity<>(new ErrorDetailDto(ex.getMessage(), null),
				responseStatus == null ? HttpStatus.BAD_REQUEST : responseStatus.value());
	}
	
	@ExceptionHandler({ ValidationBusinessException.class })
	public ResponseEntity<?> handleValidationSampException(final ValidationBusinessException ex) {

		final ResponseStatus responseStatus = ex.getClass().getAnnotation(ResponseStatus.class);

		return new ResponseEntity<>(new ErrorDetailDto(ex.getMessage(), null, ex.getErrors()),
				responseStatus == null ? HttpStatus.BAD_REQUEST : responseStatus.value());
	}
}
