package com.example.jpetstore.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CartCommand implements Serializable {
	private static final long serialVersionUID = 4447690L;
	public CartCommand() {
		
	}
	
	public String user_id;
	public int class_id;
	public Class findClass;
	
	public Class getFindClass() {
		return findClass;
	}
	public void setFindClass(Class findClass) {
		this.findClass = findClass;
	}

	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getClass_id() {
		return class_id;
	}
	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}

	
}
