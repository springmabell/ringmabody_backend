package com.example.jpetstore.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;

/*@SuppressWarnings("serial")*/
@AllArgsConstructor
public class Cart implements Serializable {
	private static final long serialVersionUID = 4447690L;
	public Cart() {
		
	}
	
	public Cart(String user_id, int class_id) {
		this.user_id = user_id;
		this.class_id = class_id;
	}

	public String user_id;
	public int class_id;
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