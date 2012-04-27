package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.math.BigDecimal;

public class Pizza implements Item {

	/**
	 * Serial ID to enable saving and loading of Till data
	 */
	private static final long serialVersionUID = 4003485664419158971L;
	/**
	 * Holds name of the pizza
	 */
	private String name;
	/**
	 * Holds description associated with the pizza
	 */
	private String description;
	/**
	 * Holds the price of the pizza
	 */
	private BigDecimal price = new BigDecimal("0");
	/**
	 * Holds the selected size of the pizza
	 */
	private String size;
	
	private final ItemType ITEMTYPE = ItemType.PIZZA;
	
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
	
	@Override
	public String getSize(){
		return size;
	}
	
	@Override
	public void setSize(String size){
		this.size = size;
	}
	
	@Override
	public BigDecimal getPrice() {
		return price;
	}

	@Override
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String getDecription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public ItemType getItemType() {
		return ITEMTYPE;
	}
}
