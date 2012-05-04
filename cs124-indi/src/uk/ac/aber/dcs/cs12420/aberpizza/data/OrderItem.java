package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.math.BigDecimal;

public class OrderItem {

	private int quantity;
	private BigDecimal itemTotal = new BigDecimal("0.00");
	private Item item = null;

	/**
	 * Constructs a new OrderItem, blank constructor needed for XML
	 * serialisation
	 */
	public OrderItem() {

	}

	/**
	 * Constructs a new OrderItem with and Item and the quantity required of
	 * that item
	 * 
	 * @see Item
	 * @param item
	 * @param quantity
	 */
	public OrderItem(Item item, int quantity) {
		this.item = item;
		this.quantity = quantity;
		updateItemTotal();
	}

	/**
	 * Gets the Item used in the OrderItem
	 * 
	 * @see Item
	 * @return Item
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * Set the item in the OrderItem
	 * 
	 * @param item
	 */
	public void setItem(Item item) {
		this.item = item;
	}

	/**
	 * Checks to see what type of <code>Item</code>, item is and returns an
	 * ItemType
	 * 
	 * @see Item
	 * @see ItemType
	 * @return ItemType
	 */
	public ItemType getItemType() {
		if (item instanceof Pizza) {
			return ItemType.PIZZA;
		} else if (item instanceof Side) {
			return ItemType.SIDE;
		} else {
			return ItemType.DRINK;
		}
	}

	/**
	 * Returns the quantity of the item ordered
	 * 
	 * @return quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity of the Item ordered
	 * 
	 * @param quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
		updateItemTotal();
	}

	/**
	 * Updates the total of the items ordered, this is the Price of the item
	 * multiplied by the quantity of the item requested
	 */

	public void updateItemTotal() {
		itemTotal = item.getPrice();
		itemTotal = itemTotal.multiply(new BigDecimal(quantity));
	}

	/**
	 * Sets the orderItemTotal
	 * @param itemTotal
	 */
	public void setOrderItemTotal(BigDecimal itemTotal) {
		this.itemTotal = itemTotal;
	}

	/**
	 * Gets the orderItemTotal
	 * @return itemTotal
	 */
	public BigDecimal getOrderItemTotal() {
		return itemTotal;
	}

	/**
	 * Overrides the <code>toString()</code> method from the Object superclass, to return a string giving 
	 */
	@Override
	public String toString() {
		return Integer.toString(quantity) + " x " + item.getName() + " @ £"
				+ item.getPrice() + " = £" + itemTotal.toString();
	}
}
