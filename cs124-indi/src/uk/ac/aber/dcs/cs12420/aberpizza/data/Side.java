package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.math.BigDecimal;

public class Side implements Item {

	private String name;
	private String description;
	private BigDecimal price;
	private ItemSize size;
	
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
	public ItemSize getSize() {
		return size;
	}
	@Override
	public void setSize(ItemSize size) {
		this.size = size;
		
	}
}
