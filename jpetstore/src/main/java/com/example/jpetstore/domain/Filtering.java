package com.example.jpetstore.domain;

import java.io.Serializable;
import java.util.List;


public class Filtering implements Serializable{
	
	private static final long serialVersionUID = 7876729345L;
	
	public Filtering() {
		
	}
	public List<String> checkedCategory;
	public String checkedLocal;

	public List<String> getCheckedCategory() {
		return checkedCategory;
	}
	public void setCheckedCategory(List<String> checkedCategory) {
		this.checkedCategory = checkedCategory;
	}
	public String getCheckedLocal() {
		return checkedLocal;
	}
	public void setCheckedLocal(String checkedLocal) {
		this.checkedLocal = checkedLocal;
	}
	/*
	 * public static long getSerialversionuid() { return serialVersionUID; }
	 * 
	 */

}
