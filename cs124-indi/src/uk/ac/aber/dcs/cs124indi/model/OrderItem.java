package uk.ac.aber.dcs.cs124indi.model;

import java.math.BigDecimal;

public class OrderItem {
	private int quantity;
	
	private Item item;
	
	public OrderItem(Item item, int quantity){
		
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	public BigDecimal getOrderItemTtoal(){
		return null;
	}
}
