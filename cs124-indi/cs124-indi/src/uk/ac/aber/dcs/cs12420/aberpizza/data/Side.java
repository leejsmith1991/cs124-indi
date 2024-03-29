package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.math.BigDecimal;

public class Side implements Item {

	/**
	 * Holds name of the side
	 */
	private String name;
	/**
	 * Holds description associated with the side
	 */
	private String description;
	/**
	 * Holds the price of the side
	 */
	private BigDecimal price = new BigDecimal("0");
	/**
	 * Holds the selected size of the side
	 */
	private String size;

	private final ItemType ITEMTYPE = ItemType.SIDE;
	
	public Side(){
		
	}
	
	/**
	 * Constructs a new Side with name, price, size and description of the item
	 * @param name
	 * @param price
	 * @param size
	 * @param desc
	 */
	public Side(String name, BigDecimal price, String size, String desc) {
		this.name = name;
		this.price = price;
		this.size = size;
		this.description = desc;
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

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;

	}

	public ItemType getItemType() {
		return ITEMTYPE;
	}

}
