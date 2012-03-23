package uk.ac.aber.dcs.cs124indi.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public class Order{

	private ArrayList<OrderItem> items;
	
	private Date date;
	
	private String customerName;
	
	public Order(){
		
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public void addItem(Item item, int quantity){
		
	}
	
	public void updateItemQuantity(Item item, int quantity){
		
	}
	
	public BigDecimal getSubtotal(){
		return null;	
	}
	
	public BigDecimal getDiscount(){
		return null;
	}
	
	public String getReceipt(){
		return null;
	}
}
