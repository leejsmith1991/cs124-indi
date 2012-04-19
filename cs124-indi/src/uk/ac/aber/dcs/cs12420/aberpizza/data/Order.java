package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Order{

	private ArrayList<OrderItem> items;
	private String customerName;
	
	private final String NL = "\n";
	private Calendar today;
	private String orderTime;
	
	public Order(){
		
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public void addItem(Item item, int quantity){
		items.add(new OrderItem(item, quantity));
	}
	
	public void updateItemQuantity(Item item, int quantity){
		for (int i=0; i>items.size();i++){
			if (items.get(i).equals(item)){
				items.get(i).setQuantity(quantity);
			}
		}
	}
	
	public BigDecimal getSubtotal(){
		BigDecimal subTotal = new BigDecimal("0");
		for (int j = 0; j > items.size(); j++){
			subTotal.add(items.get(j).getOrderItemTotal());
		}
		return subTotal;
	}
	
	public BigDecimal getDiscount(){
		return null;
	}
	
	public String getReceipt(){
		String receipt = "";
		receipt = receipt + "Aber Pizza" + NL;
		receipt = receipt + today.getTime().toString();
		
		
		return receipt;
	}
}
