package com.example.jpetstore.domain;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.*;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
/*
 * @Getter
 * 
 * @Setter
 */
@AllArgsConstructor
public class Class implements Serializable {
	/* private static final long serialVersionUID = 6529685098267757690L; */
	  public Class() {
	  }
	
	  public int getClass_id() {
		return class_id;
	}
	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}
	public String getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCartadded() {
		return cartadded;
	}
	public void setCartadded(int cartadded) {
		this.cartadded = cartadded;
	}
	public Date getSdate() {
		return sdate;
	}
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	public Date getEdate() {
		return edate;
	}
	public void setEdate(Date edate) {
		this.edate = edate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getParticipant() {
		return participant;
	}
	public void setParticipant(int participant) {
		this.participant = participant;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public int getMinNum() {
		return minNum;
	}
	public void setMinNum(int minNum) {
		this.minNum = minNum;
	}
	public int getMaxNum() {
		return maxNum;
	}
	public void setMaxNum(int maxNum) {
		this.maxNum = maxNum;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

 	public int class_id;
	public String teacher_id; 
	public int category_id;
	@NotBlank(message = "�젣紐⑹쓣 �엯�젰�빐二쇱꽭�슂")
	public String title;
	/* @NotBlank(message = "�궡�슜�쓣 �엯�젰�빐二쇱꽭�슂.") */
	public String content;
	public int hit; 
	/* @PositiveOrZero(message = "0�씠�긽留� 媛��뒫�빀�땲�떎.") */
	public int price;
	public MultipartFile getReport() {
		return report;
	}

	public void setReport(MultipartFile report) {
		this.report = report;
	}

	public int cartadded;
	public Date sdate;
	public Date edate;
	public String state; 
	public int participant; 
	public String img;
	
	
	
	public String place;
	public String local;
	public int minNum; 
	public int maxNum;
	public String categoryName;
	public MultipartFile report;
	public long date;
	private String teacher_name;
	private String teacher_desc;
	
	public String getTeacher_name() {
		return teacher_name;
	}

	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}

	public String getTeacher_desc() {
		return teacher_desc;
	}

	public void setTeacher_desc(String teacher_desc) {
		this.teacher_desc = teacher_desc;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}
}
