package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.math.BigDecimal;

/**
 * Drink class allows the program to create a new Item of the Drink type
 * 
 * @author Lee Smith
 * @see uk.ac.aber.dcs.cs12420.aberpizza.data.Item Item
 */

public class Drink implements Item{
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
	 * 
	 * @param name - Name of the Drink to be created
	 * @param price - Price of the Drink to be created
	 * @param size - Size of the Drink to be created
	 * @param desc - Description of the Drink to be created
	 */
	public Drink(String name, BigDecimal price, String size, String desc){
		this.name = name;
		this.price = price;
		this.size = size;
		this.description = desc;
	}
	
	/**
	 * Returns the price of the Drink created
	 * @return price - of the Drink
	 */
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
