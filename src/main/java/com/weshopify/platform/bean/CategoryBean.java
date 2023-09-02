package com.weshopify.platform.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class CategoryBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6589907488910721658L;
	
	private int id;
	private String name;
	
	private String alias;
	
	private String image;
	
	private boolean enabled;
	
	private String parentId;

}
