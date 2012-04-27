package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.math.BigDecimal;

public class OrderItem {
		
	private int quantity;
	private BigDecimal itemTotal = new BigDecimal("0");
	private Item item;
	
	public OrderItem(Item item, int quantity){
		this.item = item;
		this.quantity = quantity;
		updateItemTotal();
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
		updateItemTotal();
	}
	
	public void updateItemTotal(){
		itemTotal = item.getPrice();
		itemTotal = itemTotal.multiply(new BigDecimal(quantity));
	}
	
	public BigDecimal getOrderItemTotal(){
		return itemTotal;
	}
	
	public String toString(){
		return Integer.toString(quantity) + " x " + item.getName() + " @ " + item.getPrice() + " = �" + itemTotal.toString();
	}
}
