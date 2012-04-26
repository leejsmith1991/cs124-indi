package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.math.BigDecimal;

public class Side implements Item {

	/**
	 * Serial ID to enable saving and loading of Till data
	 */
	private static final long serialVersionUID = 3715867360453103089L;
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
	private BigDecimal price;
	/**
	 * Holds the selected size of the side
	 */
	private String size;

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
