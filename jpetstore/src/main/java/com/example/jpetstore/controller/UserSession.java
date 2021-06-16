package com.example.jpetstore.controller;

import java.io.Serializable;
import org.springframework.beans.support.PagedListHolder;

import com.example.jpetstore.domain.Account;
import com.example.jpetstore.domain.Product;
import com.example.jpetstore.domain.TeacherAccount;
import com.example.jpetstore.domain.UserAccount;

/**
 * @author Juergen Hoeller
 * @since 30.11.2003
 */
@SuppressWarnings("serial")
public class UserSession implements Serializable {

	private UserAccount account;

	private PagedListHolder<Product> myList;
	

	public UserSession(UserAccount account) {
		this.account = account;
	}

	public UserAccount getAccount() {
		return account;
	}

//	public void setMyList(PagedListHolder<Product> myList) {
//		this.myList = myList;
//	}
//
//	public PagedListHolder<Product> getMyList() {
//		return myList;
//	}
}
