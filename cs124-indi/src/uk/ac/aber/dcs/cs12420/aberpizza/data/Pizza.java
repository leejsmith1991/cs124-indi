package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Pizza implements Item {

	private String name;
	private String description;
	private BigDecimal price;
	
	private ArrayList<String> toppings;
	private String baseType;
	
	public Pizza(String name, BigDecimal price, String desc){
		this.name = name;
		this.price = price;
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

	public void setToppings(ArrayList<String> toppings){
		this.toppings = toppings;
	}
	
	public ArrayList<String> getToppings(){
		return toppings;
	}
	
	public void setBaseType(String base){
		this.baseType = base;
	}
	public String getBaseType(){
		return baseType;
	}
}
