package com.weshopifyapp.features.categories.exceptions;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class CategoriesExceptionList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1644906630480143944L;
	
	private List<CategoriesException> errors;

}
