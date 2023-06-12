package com.weshopifyapp.features.categories.exceptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CategoriesExceptionHandler extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		
			CategoriesExceptionList exceptionsList = new CategoriesExceptionList();
			
			List<CategoriesException> errorsList = new ArrayList<>();
		    
			ex.getBindingResult().getFieldErrors().stream().forEach(error->{
				
			CategoriesException fieldValidationError = new CategoriesException(ex.getLocalizedMessage(), 
					HttpStatus.BAD_REQUEST.value(), new Date());
			
			errorsList.add(fieldValidationError);
		});
			exceptionsList.setErrors(errorsList);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionsList);
	}
}
