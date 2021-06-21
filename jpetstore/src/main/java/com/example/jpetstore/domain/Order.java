package com.example.jpetstore.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class Order implements Serializable {
	  
	/* Private Fields */
	 
	 public Order() {
		 
	 }

	 public int order_id;
	 public int class_id;
	 public String user_id;
	 public Date order_date;
	 public String card_bank;
	 public String card_number;
	 public Date expiration_date;
	 public int cvc;
	 public int total_price;

	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getClass_id() {
		return class_id;
	}
	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public String getCard_bank() {
		return card_bank;
	}
	public void setCard_bank(String card_bank) {
		this.card_bank = card_bank;
	}
	public String getCard_number() {
		return card_number;
	}
	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}

	public int getCvc() {
		return cvc;
	}
	public void setCvc(int cvc) {
		this.cvc = cvc;
	}
	
	public Date getExpiration_date() {
		return expiration_date;
	}
	public void setExpiration_date(Date expiration_date) {
		this.expiration_date = expiration_date;
	}
	public int getTotal_price() {
		return total_price;
	}
	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}
		
  /* Private Fields */

  private String username;
  private List<LineItem> lineItems = new ArrayList<LineItem>();

  /* JavaBeans Properties */

  public String getUsername() { return username; }
  public void setUsername(String username) { this.username = username; }

  public void setLineItems(List<LineItem> lineItems) { this.lineItems = lineItems; }
  public List<LineItem> getLineItems() { return lineItems; }

  /* Public Methods */

//  public void initOrder(Account account, Cart cart) {
		/*
		 * username = account.getUsername(); orderDate = new Date();
		 * 
		 * shipToFirstName = account.getFirstName(); shipToLastName =
		 * account.getLastName(); shipAddress1 = account.getAddress1(); shipAddress2 =
		 * account.getAddress2(); shipCity = account.getCity(); shipState =
		 * account.getState(); shipZip = account.getZip(); shipCountry =
		 * account.getCountry();
		 * 
		 * billToFirstName = account.getFirstName(); billToLastName =
		 * account.getLastName(); billAddress1 = account.getAddress1(); billAddress2 =
		 * account.getAddress2(); billCity = account.getCity(); billState =
		 * account.getState(); billZip = account.getZip(); billCountry =
		 * account.getCountry();
		 * 
		 * totalPrice = cart.getSubTotal();
		 * 
		 * creditCard = "999 9999 9999 9999"; expiryDate = "12/03"; cardType = "Visa";
		 * courier = "UPS"; locale = "CA"; status = "P";
		 * 
		 * Iterator<CartItem> i = cart.getAllCartItems(); while (i.hasNext()) { CartItem
		 * cartItem = (CartItem) i.next(); addLineItem(cartItem); }
		 */  
//}
  
  public void addLineItem(CartItem cartItem) {
    LineItem lineItem = new LineItem(lineItems.size() + 1, cartItem);
    addLineItem(lineItem);
  }

  public void addLineItem(LineItem lineItem) {
    lineItems.add(lineItem);
  }
}