package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Order {

	private ArrayList<OrderItem> items;
	private String customerName;

	private final String NL = "\n";
	private Date today= new Date();;
	private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
	private SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
	private String orderTime=timeFormatter.format(today);;
	private String orderDate= dateFormatter.format(today);;
	private BigDecimal subTotal = new BigDecimal("0.00");
	private BigDecimal orderTotal = new BigDecimal("0.00");
	private String discountType;
	
	public Order() {
		items = new ArrayList<OrderItem>();
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getOrderDate(){
		return orderDate;
	}
	public void setOrderDate(String orderDate){
		this.orderDate = orderDate;
	}
	
	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public BigDecimal getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(BigDecimal orderTotal) {
		this.orderTotal = orderTotal;
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

	public Item getItemAt(int index) {
		return items.get(index).getItem();
	}

	public void updateItemQuantity(Item item, int quantity) {
		for (OrderItem orderItem : items) {
			if (orderItem.getItem().equals(item)) {
				orderItem.setQuantity(quantity);
			}
		}
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
			subTotal = subTotal.add(items.get(j).getOrderItemTotal());
		}
		setSubtotal(subTotal);
		setOrderTotal(getDiscount());
	}

	public BigDecimal getDiscount() {
		ArrayList<Item> smallPizza = new ArrayList<Item>();
		ArrayList<Item> mediumPizza = new ArrayList<Item>();
		ArrayList<Item> largePizza = new ArrayList<Item>();
		ArrayList<Item> sides = new ArrayList<Item>();
		ArrayList<Item> drinks = new ArrayList<Item>();

		int pizzaS = 0;
		int pizzaM = 0;
		int pizzaL = 0;
		int sideCount = 0;
		int drinkCount = 0;

		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getItemType() == ItemType.PIZZA
					&& items.get(i).getItem().getSize().equals("Small")) {
				for (int a = 0; a < items.get(i).getQuantity(); a++) {
					smallPizza.add(items.get(i).getItem());
					pizzaS++;
				}
			} else if (items.get(i).getItemType() == ItemType.PIZZA
					&& items.get(i).getItem().getSize().equals("Medium")) {
				for (int a = 0; a < items.get(i).getQuantity(); a++) {
					mediumPizza.add(items.get(i).getItem());
					pizzaM++;
				}
			} else if (items.get(i).getItemType() == ItemType.PIZZA
					&& items.get(i).getItem().getSize().equals("Large")) {
				for (int a = 0; a < items.get(i).getQuantity(); a++) {
					largePizza.add(items.get(i).getItem());
					pizzaL++;
				}
			} else if (items.get(i).getItemType() == ItemType.SIDE) {
				for (int a = 0; a < items.get(i).getQuantity(); a++) {
					sides.add(items.get(i).getItem());
					sideCount++;
				}
			} else if (items.get(i).getItemType() == ItemType.DRINK) {
				for (int a = 0; a < items.get(i).getQuantity(); a++) {
					drinks.add(items.get(i).getItem());
					drinkCount++;
				}
			}
		}
		if (pizzaS > 0) {
			smallPizza = sort(smallPizza);
		}
		if (pizzaM > 0) {
			mediumPizza = sort(mediumPizza);
		}
		if (pizzaL > 0) {
			largePizza = sort(largePizza);
		}
		if (sideCount > 0) {
			sides = sort(sides);
		}
		if (drinkCount > 0) {
			drinks = sort(drinks);
		}

		boolean discountApplied = false;
		BigDecimal discountItems = new BigDecimal("0");
		BigDecimal discount = new BigDecimal("0");

		if (pizzaL >= 3) {
			discountItems = largePizza.get(0).getPrice();
			discountItems = discountItems.add(largePizza.get(1).getPrice());
			discountItems = discountItems.add(largePizza.get(2).getPrice());
			discount = discountItems.multiply(new BigDecimal("0.33334"));
			discountApplied = true;
			discountType = "Buy 3 get 1 Free Large Pizzas";
		} else if (pizzaM >= 3 && discountApplied == false) {
			discountItems = mediumPizza.get(0).getPrice();
			discountItems = discountItems.add(mediumPizza.get(1).getPrice());
			discountItems = discountItems.add(mediumPizza.get(2).getPrice());
			discount = discountItems.multiply(new BigDecimal("0.33334"));
			discountApplied = true;
			discountType = "Buy 3 get 1 Free Medium Pizzas";
		} else if (pizzaS >= 3 && discountApplied == false) {
			discountItems = smallPizza.get(0).getPrice();
			discountItems = discountItems.add(smallPizza.get(1).getPrice());
			discountItems = discountItems.add(smallPizza.get(2).getPrice());
			discount = discountItems.multiply(new BigDecimal("0.33334"));
			discountApplied = true;
			discountType = "Buy 3 get 1 Free Small Pizzas";
		} else if (pizzaL >= 1 && sideCount >= 1 && drinkCount >= 1
				&& discountApplied == false) {
			discountItems = largePizza.get(0).getPrice();
			discountItems = discountItems.add(sides.get(0).getPrice());
			discountItems = discountItems.add(drinks.get(0).getPrice());
			discount = discountItems.multiply(new BigDecimal("0.15"));
			discountType = "Meal Deal";
		}

		discount = discount.setScale(2, BigDecimal.ROUND_HALF_UP);

		orderTotal = subTotal.subtract(discount);
		return discount;
	}

	private ArrayList<Item> sort(ArrayList<Item> list) {
		boolean sorted = false;
		int equalCount = 0;
		int listLoc = 1;

		while (!sorted && list.size() > 1) {
			Item a = list.get(listLoc - 1);
			Item b = list.get(listLoc);
			boolean changed = false;

			if (a.getPrice().compareTo(list.get(listLoc).getPrice()) <= 0) {
				if (a.getPrice().compareTo(list.get(listLoc).getPrice()) == 0) {
					equalCount++;
				}
				list.set(listLoc - 1, b);
				list.set(listLoc, a);
				changed = true;
			}
			if (listLoc == list.size() - 1) {
				listLoc = 1;
			} else {
				listLoc++;
			}

			if (changed == false) {
				sorted = true;
			}
			if (equalCount == list.size()) {
				sorted = true;
			}
		}

		return list;
	}

	public String getReceipt() {
		String receipt = "";
		receipt = receipt + "Aber Pizza" + NL + NL;
		receipt = receipt + "Order for " + customerName + NL;
		receipt = receipt + "Time of Order: " + getOrderDate() + " at " + getOrderTime()
				+ NL + NL;
		for (int i = 0; i < items.size(); i++) {
			receipt = receipt + items.get(i).toString() + NL;
		}

		receipt = receipt + NL +"Subtotal : £" + subTotal.toString() + NL;
		receipt = receipt + "Discounts " + discountType + " : £"
				+ getDiscount().toString() + NL;
		receipt = receipt + "Order Total : £" + orderTotal;

		return receipt;
	}

	@Override
	public String toString() {
		return orderTime + "   - " + customerName + " £" + subTotal.toString();
	}
}
