package com.example.jpetstore.controller;

import java.io.Serializable;

import com.example.jpetstore.domain.Account;
import com.example.jpetstore.domain.UserAccount;

/**
 * @author Juergen Hoeller
 * @since 01.12.2003
 */
@SuppressWarnings("serial")
public class UserAccountForm implements Serializable {

	private UserAccount account;

	private boolean newAccount;

	private String repeatedPassword;

	public UserAccountForm(UserAccount account) {
		this.account = account;
		this.newAccount = false;
	}

	public UserAccountForm() {
		this.account = new UserAccount();
		this.newAccount = true;
	}

	public UserAccount getAccount() {
		return account;
	}

	public boolean isNewAccount() {
		return newAccount;
	}

	public void setRepeatedPassword(String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}

	public String getRepeatedPassword() {
		return repeatedPassword;
	}
}
