package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Pizzas implements Item {
	
	private ItemType type;
	private String name;
	private String description;
	private BigDecimal price;
	
	private ArrayList<String> toppings;
	private String baseType;
	
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
		return type;
	}

	@Override
	public void setItemType() {
		this.type = ItemType.PIZZA;
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
