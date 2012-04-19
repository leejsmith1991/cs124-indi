package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Pizza implements Item {

	private String name;
	private String description;
	private BigDecimal price;
	private String size;
	
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
}
