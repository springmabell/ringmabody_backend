package com.example.jpetstore.domain;

import java.io.Serializable;
import java.util.Date;


@SuppressWarnings("serial")
public class Payment implements Serializable {

  /* Private Fields */

  private String payment_id;
  private Date pay_due_date;
  private Date pay_complete_date;
  private String commission;
  private String total_payment;
  private String teacher_id;
  

  /* JavaBeans Properties */
  
public String getPayment_id() {
	return payment_id;
}
public void setPayment_id(String payment_id) {
	this.payment_id = payment_id;
}
public Date getPay_due_date() {
	return pay_due_date;
}
public void setPay_due_date(Date pay_due_date) {
	this.pay_due_date = pay_due_date;
}
public Date getPay_complete_date() {
	return pay_complete_date;
}
public void setPay_complete_date(Date pay_complete_date) {
	this.pay_complete_date = pay_complete_date;
}
public String getCommission() {
	return commission;
}
public void setCommission(String commission) {
	this.commission = commission;
}
public String getTotal_payment() {
	return total_payment;
}
public void setTotal_payment(String total_payment) {
	this.total_payment = total_payment;
}
public String getTeacher_id() {
	return teacher_id;
}
public void setTeacher_id(String teacher_id) {
	this.teacher_id = teacher_id;
}
  

}