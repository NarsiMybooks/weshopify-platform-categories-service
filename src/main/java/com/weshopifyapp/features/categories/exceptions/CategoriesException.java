package com.weshopifyapp.features.categories.exceptions;

import java.time.LocalDate;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoriesException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3899656805646551740L;
	
	private String errorMessage;
	private int errorCode;
	
	private Date timeStamp;
	
	

}
