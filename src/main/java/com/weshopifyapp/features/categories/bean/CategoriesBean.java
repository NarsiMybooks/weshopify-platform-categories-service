package com.weshopifyapp.features.categories.bean;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriesBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -605593816332602131L;
	
	private int id;
	
	@NotEmpty(message = "Category Name must be Provided. It should neither be emty nor blank")
	private String name;
	
	@NotEmpty(message = "Alias For the Category Name must be Provided. It should neither be emty nor blank")
	private String alias;
	
	private int parentCategory;
	
	/**
	 * image should be uploaded to the cloud and the 
	 * image url should be maintained in db
	 */
	private String image;
	
	private boolean isEnabled;

}
