package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.math.BigDecimal;
/**
 * Item interface inplemented by Pizza, Side, and Drink
 * @author Lee Smith
 *@see Pizza
 *@see Side
 *@see Drink
 */
public interface Item{
	
	/**
	 * Returns the price of a created item
	 * @return price of Item
	 */
	public BigDecimal getPrice();
	/**
	 * Sets the Price of the created item
	 * @param price
	 */
	public void setPrice(BigDecimal price);
	/**
	 * Returns the description of a created item
	 * @return description of item
	 */
	public String getDescription();
	/**
	 * Sets the description of the created item
	 * @param description
	 */
	public void setDescription(String description);
	/**
	 * Returns the name of a created item
	 * @return name of item
	 */
	public String getName();
	/**
	 * Sets the name of a created item
	 * @param name
	 */
	public void setName(String name);
	/**
	 * Returns Size of the created item
	 * @return size
	 */
	public String getSize();
	/**
	 * Sets the size of a created item
	 * @param size
	 */
	public void setSize(String size);
	
	public ItemType getItemType();

}
