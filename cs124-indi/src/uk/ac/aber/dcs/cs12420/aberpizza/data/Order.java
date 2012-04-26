package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Order implements Serializable {

	/**
	 * Serial ID for saving and loading Till data
	 */
	private static final long serialVersionUID = -4579109022630547790L;
	private ArrayList<OrderItem> items;
	private String customerName;

	private final String NL = "\n";
	private Date today;
	private String orderTime;
	private SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss");
	private BigDecimal subTotal;

	public Order() {
		items = new ArrayList<OrderItem>();
		today = new Date();
		orderTime = dateFormatter.format(today);
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date date) {
		this.orderTime = dateFormatter.format(date);
	}

	public void addItem(Item item, int quantity) {
		items.add(new OrderItem(item, quantity));
	}

	public ArrayList<OrderItem> getItems() {
		return items;
	}

	public void setItems(ArrayList<OrderItem> items) {
		this.items = items;
	}

	public void updateItemQuantity(Item item, int quantity) {
		for (int i = 0; i > items.size(); i++) {
			if (items.get(i).equals(item)) {
				items.get(i).setQuantity(quantity);
			}
		}
	}

	public Item getItemAt(int index){
		return items.get(index).getItem();
	}
	
	public void removeItem(int index) {
		items.remove(index);
		System.out.println("Item Removed");
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subTotal = subtotal;
	}

	public BigDecimal getSubtotal() {
		subTotal = new BigDecimal("0");
		for (int j = 0; j < items.size(); j++) {
			subTotal.add(items.get(j).getOrderItemTotal());
		}
		System.out.println("Fired");
		return subTotal;
	}

	public BigDecimal getDiscount() {
		return null;
	}

	public String getReceipt() {
		String receipt = "";
		receipt = receipt + "Aber Pizza" + NL;
		receipt = receipt + orderTime;

		return receipt;
	}

	@Override
	public String toString() {
		return orderTime + "   - " + customerName + " £" + subTotal.toString();
	}
}
