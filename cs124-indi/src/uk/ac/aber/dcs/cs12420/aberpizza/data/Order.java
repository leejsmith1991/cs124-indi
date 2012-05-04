package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Order {

	private ArrayList<OrderItem> items;
	private String customerName;
	/**
	 * Use allows simple entry of a new line character
	 */
	private final String NL = "\n";
	private Date today = new Date();;
	private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
	private SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
	private String orderTime = timeFormatter.format(today);;
	private String orderDate = dateFormatter.format(today);;
	private BigDecimal subTotal = new BigDecimal("0.00");
	private BigDecimal orderTotal = new BigDecimal("0.00");
	private String discountType, receipt;

	/**
	 * Constructs a new Order object, and initialises an Arraylist of OrderItem
	 * types
	 * 
	 * @see OrderItem
	 */
	public Order() {
		items = new ArrayList<OrderItem>();
	}

	/**
	 * Returns the Customers name
	 * 
	 * @return customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * Sets the Customer Name
	 * 
	 * @param customerName
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * Returns the date when the Order was ordered preformatted as a string in
	 * the format dd/MM/yyyy
	 * 
	 * @return orderDate
	 */
	public String getOrderDate() {
		return orderDate;
	}

	/**
	 * Sets the date when the order was made
	 * 
	 * @param orderDate
	 */
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * Returns teh time the order was made, preformatted as a String into
	 * HH:mm:ss
	 * 
	 * @return orderTime
	 */

	public String getOrderTime() {
		return orderTime;
	}

	/**
	 * Sets the time when the order was made
	 * 
	 * @param orderTime
	 */

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	/**
	 * Gets the total for the order
	 * 
	 * @return orderTotal
	 */

	public BigDecimal getOrderTotal() {
		return orderTotal;
	}

	/**
	 * Sets the orderTotal
	 * 
	 * @see BigDecimal
	 * @param orderTotal
	 */
	public void setOrderTotal(BigDecimal orderTotal) {
		this.orderTotal = orderTotal;
	}

	/**
	 * Adds a new Item to the arraylist conatining OrderItems, also sets the
	 * quantity of the item ordered, the method then also runs the
	 * updateSubtotal method to ensure then the subtotal is kept up-to-date when
	 * a new item is added to the order
	 * 
	 * @see updateSubTotal
	 * @param item
	 * @param quantity
	 */
	public void addItem(Item item, int quantity) {
		items.add(new OrderItem(item, quantity));
		updateSubTotal();
	}

	/**
	 * Returns the arraylist of OrderItems
	 * 
	 * @return ArrayList<<code>OrderItem</code>>
	 */

	public ArrayList<OrderItem> getItems() {
		return items;
	}

	/**
	 * Sets the ArrayList of OrderItems
	 * 
	 * @param ArrayList
	 *            <<code>items</code>>
	 */

	public void setItems(ArrayList<OrderItem> items) {
		this.items = items;
	}

	/**
	 * Returns the item from the arraylist at the index that is selected
	 * 
	 * @param index
	 * @return Item
	 */

	public Item getItemAt(int index) {
		return items.get(index).getItem();
	}

	/**
	 * Updates the quantity of the item that has been selected to be updated
	 * 
	 * @param item
	 * @param quantity
	 */

	public void updateItemQuantity(Item item, int quantity) {
		for (OrderItem orderItem : items) {
			if (orderItem.getItem().equals(item)) {
				orderItem.setQuantity(quantity);
			}
		}
	}

	/**
	 * Removes the item at the selected index in the ArrayList
	 * 
	 * @see ArrayList
	 * @param index
	 */

	public void removeItem(int index) {
		items.remove(index);
	}

	/**
	 * Sets the subtotal for the order
	 * 
	 * @param subtotal
	 */

	public void setSubtotal(BigDecimal subtotal) {
		this.subTotal = subtotal;
	}

	/**
	 * Returns the subtotal for the order
	 * 
	 * @return subTotal
	 */

	public BigDecimal getSubtotal() {
		return subTotal;
	}

	/**
	 * Runs throught the ArrayList and gets the OrderItemTotal for each
	 * OrderItem, this method is run everytime an item is added to the arraylist
	 * to ensure that the subtotal is constantly updated when items are added,
	 * updated, or removed from the arraylist
	 */

	public void updateSubTotal() {
		subTotal = new BigDecimal("0");
		for (int j = 0; j < items.size(); j++) {
			subTotal = subTotal.add(items.get(j).getOrderItemTotal());
		}
		setSubtotal(subTotal);
		setOrderTotal(getDiscount());
	}

	/**
	 * Calculates the discount to be applied to the order, splitting the main
	 * ArrayList of orders into smaller ArrayLists<<code>Item</code>>based on
	 * the type of item, and in the instance of pizza the 3 sizes leaving 5
	 * ArrayLists<<code>Item</code>>. <br>
	 * <br>
	 * These ArrayLists are then cycled through to get the quantity of the item
	 * to get how many items were ordered in total for each ArrayList. The
	 * ArrayLists are then checked for size and sorted using the sort method. <br>
	 * <br>
	 * The Discounts will be applied as follows:<br>
	 * Buy 2 get 1 free on Large Pizzas<br>
	 * Buy 2 get 1 free on Medium Pizzas<br>
	 * Buy 2 get 1 free on Small Pizzas<br>
	 * Meal Deal - 15% off, Buy Large Pizza, 1 Side and 1 Drink<br>
	 * Only 1 deal can be applied so there is a check to see if a discount has
	 * already been applied
	 * 
	 * @see sort
	 * @return <code>BigDecimal</code> discount
	 */

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
			discountType = "Buy 2 get 1 Free Large Pizzas";
		} else if (pizzaM >= 3 && discountApplied == false) {
			discountItems = mediumPizza.get(0).getPrice();
			discountItems = discountItems.add(mediumPizza.get(1).getPrice());
			discountItems = discountItems.add(mediumPizza.get(2).getPrice());
			discount = discountItems.multiply(new BigDecimal("0.33334"));
			discountApplied = true;
			discountType = "Buy 2 get 1 Free Medium Pizzas";
		} else if (pizzaS >= 3 && discountApplied == false) {
			discountItems = smallPizza.get(0).getPrice();
			discountItems = discountItems.add(smallPizza.get(1).getPrice());
			discountItems = discountItems.add(smallPizza.get(2).getPrice());
			discount = discountItems.multiply(new BigDecimal("0.33334"));
			discountApplied = true;
			discountType = "Buy 2 get 1 Free Small Pizzas";
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

	/**
	 * ArrayLists are passed into this method, which sorts the
	 * <code>ArrayList</code> into Descending order based on the price of the
	 * item and returns the same list but sorted
	 * 
	 * @param list
	 *            - <code>ArrayList</code><<code>Item</code>>
	 * @return ArrayList<<code>Item</code>>
	 */

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

	/**
	 * Returns a string reviewing the order made, this method is made simpler by
	 * overriding the <code>toString()</code> method in this and the OrderItem
	 * classes. It returns a string which could then be used as a printout for a
	 * receipt
	 * 
	 * @return receipt - <code>String</code>
	 */

	public String getReceipt() {
		String receipt = "";
		receipt = receipt + "Aber Pizza" + NL + NL;
		receipt = receipt + "Order for " + customerName + NL;
		receipt = receipt + "Time of Order: " + getOrderDate() + " at "
				+ getOrderTime() + NL + NL;
		for (int i = 0; i < items.size(); i++) {
			receipt = receipt + items.get(i).toString() + NL;
		}

		receipt = receipt + NL + "Subtotal : £" + subTotal.toString() + NL;
		receipt = receipt + "Discounts " + discountType + " : £"
				+ getDiscount().toString() + NL;
		receipt = receipt + "Order Total : £" + orderTotal;

		return receipt;
	}

	/**
	 * Overrides the Object superclass toString() method allowing the time of
	 * the order, name of the customer, and the subtotal to be extracted from
	 * this class
	 * 
	 * @return orderTime, customerName, and the subtotal
	 */
	
	@Override
	public String toString() {
		return orderTime + "   - " + customerName + " £" + subTotal.toString();
	}
}
