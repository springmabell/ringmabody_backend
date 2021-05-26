package com.example.jpetstore.domain;

import java.io.Serializable;
import java.util.Date;


@SuppressWarnings("serial")
public class TeacherAccount implements Serializable {

  /* Private Fields */

  private String teacher_id;
  private String teacher_pwd;
  private String teacher_name;
  private String teacher_img;
  private String email;
  private String phonenum;
  private String user_type;
  private String belong;
  private String career;
  private String website;
  private String teacher_desc;
  private Date register_date;
  

  /* JavaBeans Properties */

	public String getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}
	public String getTeacher_pwd() {
		return teacher_pwd;
	}
	public void setTeacher_pwd(String teacher_pwd) {
		this.teacher_pwd = teacher_pwd;
	}
	public String getTeacher_name() {
		return teacher_name;
	}
	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}
	public String getTeacher_img() {
		return teacher_img;
	}
	public void setTeacher_img(String teacher_img) {
		this.teacher_img = teacher_img;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	public String getBelong() {
		return belong;
	}
	public void setBelong(String belong) {
		this.belong = belong;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getTeacher_desc() {
		return teacher_desc;
	}
	public void setTeacher_desc(String teacher_desc) {
		this.teacher_desc = teacher_desc;
	}
	public Date getRegister_date() {
		return register_date;
	}
	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
}

}
