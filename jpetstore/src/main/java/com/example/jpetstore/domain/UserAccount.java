package com.example.jpetstore.domain;

import java.io.Serializable;
import java.util.Date;


@SuppressWarnings("serial")
public class UserAccount implements Serializable {

  /* Private Fields */

  private String user_id;
  private String user_pwd;
  private String user_name;
  private String user_img;
  private String email;
  private String phone;
  private String gender;
  private String address;
  private String user_type;
  private Date birthdate;
  private Date register_date;
  
  /* JavaBeans Properties */

  public String getUser_id() {
	return user_id;
}
public void setUser_id(String user_id) {
	this.user_id = user_id;
}
public String getUser_pwd() {
	return user_pwd;
}
public void setUser_pwd(String user_pwd) {
	this.user_pwd = user_pwd;
}
public String getUser_name() {
	return user_name;
}
public void setUser_name(String user_name) {
	this.user_name = user_name;
}
public String getUser_img() {
	return user_img;
}
public void setUser_img(String user_img) {
	this.user_img = user_img;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getUser_type() {
	return user_type;
}
public void setUser_type(String user_type) {
	this.user_type = user_type;
}
public Date getBirth_date() {
	return birthdate;
}
public void setBirth_date(Date birthdate) {
	this.birthdate = birthdate;
}
public Date getRegister_date() {
	return register_date;
}
public void setRegister_date(Date register_date) {
	this.register_date = register_date;
}

  



}
