package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderItem implements Serializable{
	/**
	 * Serial ID to enable saving and loading of Till data
	 */
	private static final long serialVersionUID = -9034323080247401793L;
	private int quantity;
	private Item item;
	
	public OrderItem(Item item, int quantity){
		this.item = item;
		this.quantity = quantity;
	}
	
	public Item getItem(){
		return item;
	}
	
	public void setItem(Item item){
		this.item = item;
	}
	public ItemType getItemType(){
		if (item instanceof Pizza){
			return ItemType.PIZZA;
		} else if (item instanceof Side){
			return ItemType.SIDE;
		} else {
			return ItemType.DRINK;
		}
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	public void setQuantity(int quantity){
		this.quantity = quantity;
		getOrderItemTotal();
	}
	
	public BigDecimal getOrderItemTotal(){
		BigDecimal itemPrice = item.getPrice();
		itemPrice.multiply(new BigDecimal(quantity));
		return itemPrice;
	}
	
	public String toString(){
		return item.getName() + " x " + Integer.toString(quantity);
	}
}
