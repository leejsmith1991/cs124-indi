package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.math.BigDecimal;

public class Drink implements Item{

	private String name;
	private String description;
	private BigDecimal price;
	private ItemSize size;
	
	public Drink(String name, BigDecimal price, ItemSize size, String desc){
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
	public ItemSize getSize() {
		return size;
	}

	@Override
	public void setSize(ItemSize size) {
		this.size = size;		
	}

}
