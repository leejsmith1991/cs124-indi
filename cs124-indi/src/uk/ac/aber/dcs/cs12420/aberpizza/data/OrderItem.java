package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.math.BigDecimal;

public class OrderItem {
	private int quantity;
	private Item item;
	
	public OrderItem(Item item, int quantity){
		this.item = item;
		this.quantity = quantity;
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}
	
	public BigDecimal getOrderItemTotal(){
		BigDecimal itemPrice = item.getPrice();
		itemPrice.multiply(new BigDecimal(quantity));
		return itemPrice;
	}
}
