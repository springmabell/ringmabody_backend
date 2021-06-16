package com.example.jpetstore.controller;

import java.io.Serializable;

import com.example.jpetstore.domain.Account;
import com.example.jpetstore.domain.TeacherAccount;
import com.example.jpetstore.domain.UserAccount;

/**
 * @author Juergen Hoeller
 * @since 01.12.2003
 */
@SuppressWarnings("serial")
public class TeacherAccountForm implements Serializable {

	private TeacherAccount account;

	private boolean newAccount;

	private String repeatedPassword;

	public TeacherAccountForm(TeacherAccount account) {
		this.account = account;
		this.newAccount = false;
	}

	public TeacherAccountForm() {
		this.account = new TeacherAccount();
		this.newAccount = true;
	}

	public TeacherAccount getAccount() {
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
