package com.weshopifyapp.features.categories.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categories implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -605593816332602131L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name;
	private String alias;
	private int parentCategory;
	
	/**
	 * image should be uploaded to the cloud and the 
	 * image url should be maintained in db
	 */
	private String image;
	
	private boolean isEnabled;
	
	/**
	 * audit info
	 */
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date modifiedDate;

}
