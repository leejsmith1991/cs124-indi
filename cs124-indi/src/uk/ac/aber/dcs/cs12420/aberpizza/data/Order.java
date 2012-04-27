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
	private BigDecimal subTotal = new BigDecimal("0");

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
		updateSubTotal();
	}

	public ArrayList<OrderItem> getItems() {
		return items;
	}

	public void setItems(ArrayList<OrderItem> items) {
		this.items = items;
	}

	public void updateItemQuantity(Item item, int quantity) {
		for (OrderItem orderItem : items) {
			if (orderItem.getItem().equals(item)) {
				orderItem.setQuantity(quantity);
				System.out.println("Quantity updated");
			}
		}
	}

	public Item getItemAt(int index) {
		return items.get(index).getItem();
	}

	public void removeItem(int index) {
		items.remove(index);
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subTotal = subtotal;
	}

	public BigDecimal getSubtotal() {
		return subTotal;
	}

	public void updateSubTotal() {
		subTotal = new BigDecimal("0");
		for (int j = 0; j < items.size(); j++) {
			subTotal.add(items.get(j).getOrderItemTotal());
		}

	}

	public BigDecimal getDiscount() {
		ArrayList<OrderItem> smallPizza = new ArrayList<OrderItem>();
		ArrayList<OrderItem> mediumPizza = new ArrayList<OrderItem>();
		ArrayList<OrderItem> largePizza = new ArrayList<OrderItem>();

		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getItemType() == ItemType.PIZZA) {
				if (items.get(i).getItem().getSize().equals("Small")) {
					smallPizza.add(items.get(i));
				} else if (items.get(i).getItem().getSize().equals("Medium")) {
					mediumPizza.add(items.get(i));
				} else {
					largePizza.add(items.get(i));
				}
			}
		}

		int pizzaS = 0;
		int pizzaM = 0;
		int pizzaL = 0;

		for (int a = 0; a < smallPizza.size(); a++) {
			pizzaS = pizzaS + (1 * smallPizza.get(a).getQuantity());
		}
		for (int a = 0; a < mediumPizza.size(); a++) {
			pizzaM = pizzaM + (1 * mediumPizza.get(a).getQuantity());
		}
		for (int a = 0; a < largePizza.size(); a++) {
			pizzaL = pizzaL + (1 * largePizza.get(a).getQuantity());
		}

		sort(smallPizza);

		return null;
	}

	public ArrayList<OrderItem> sort(ArrayList<OrderItem> list) {
		boolean sorted = true;

		while (sorted) {
			
		}

		for (int a = 0; a < list.size(); a++) {
			System.out.println(list.get(a).getItem().getPrice());
		}
		return list;
	}

	public String getReceipt() {
		String receipt = "";
		receipt = receipt + "Aber Pizza" + NL;
		receipt = receipt + orderTime;

		return receipt;
	}

	@Override
	public String toString() {
		return orderTime + "   - " + customerName + " �" + subTotal.toString();
	}
}
