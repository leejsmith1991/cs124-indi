package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.math.BigDecimal;

public class Pizza implements Item {

	/**
	 * Holds name of the pizza
	 */
	private String name = null;
	/**
	 * Holds description associated with the pizza
	 */
	private String description = null;
	/**
	 * Holds the price of the pizza
	 */
	private BigDecimal price = new BigDecimal("0");
	/**
	 * Holds the selected size of the pizza
	 */
	private String size;
	
	private final ItemType ITEMTYPE = ItemType.PIZZA;
	
	public Pizza(){
		
	}
	
	/**
	 * Constructs a new Pizza type with name, price, size and description of the item
	 * @param name
	 * @param price
	 * @param size
	 * @param desc
	 */
	public Pizza(String name, BigDecimal price, String size,  String desc){
		this.name = name;
		this.price = price;
		this.description = desc;
		this.size = size;
	}
	
	public String getSize(){
		return size;
	}
	
	public void setSize(String size){
		this.size = size;
	}
	
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ItemType getItemType() {
		return ITEMTYPE;
	}
}
