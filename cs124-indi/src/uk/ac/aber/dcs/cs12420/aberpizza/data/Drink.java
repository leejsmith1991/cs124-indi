package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.math.BigDecimal;

/**
 * Drink class allows the program to create a new Item of the Drink type
 * 
 * @author Lee Smith
 * @see Item
 */

public class Drink implements Item{
	/**
	 * Serial ID to enable saving and loading of Till data
	 */
	private static final long serialVersionUID = -843689942661586094L;
	/**
	 * Holds name of the drink
	 */
	private String name;
	/**
	 * Holds description associated with the drink
	 */
	private String description;
	/**
	 * Holds the price of the drink
	 */
	private BigDecimal price;
	/**
	 * Holds the selected size of the drink
	 */
	private String size;
	
	/**
	 * Constructs a new Drink type with name, price, size and description of the item
	 * @param String name - Name of the Drink to be created
	 * @param BigDecimal price - Price of the Drink to be created
	 * @param String size - Size of the Drink to be created
	 * @param String desc - Description of the Drink to be created
	 */
	public Drink(String name, BigDecimal price, String size, String desc){
		this.name = name;
		this.price = price;
		this.size = size;
		this.description = desc;
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
	public String getSize() {
		return size;
	}

	@Override
	public void setSize(String size) {
		this.size = size;		
	}

}
