package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.io.FileNotFoundException;
import java.math.BigDecimal;

import javax.swing.JFrame;
import javax.swing.JPanel;

import uk.ac.aber.dcs.cs12420.aberpizza.data.ItemType;

public abstract class ItemFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 668813998738398121L;

	/**
	 * Returns a JPanel that contains a JList of the items, and there
	 * corresponding descriptions, JPanel is layed out
	 * 
	 * @see JList
	 * @see DefaultListModel
	 * @see JPanel
	 * @return JPanel
	 */
	protected abstract JPanel getItemPane();

	/**
	 * Returns a JPanel that contains a JList of the prices of the selected
	 * item, JPanel is layed out
	 * 
	 * @see JList
	 * @see DefaultListModel
	 * @see JPanel
	 * @return JPanel
	 */
	protected abstract JPanel getPricePane();

	/**
	 * Sets the Prices in the prices JList, changes based on the Item selected
	 * 
	 * @param selected
	 */
	protected abstract void setItemPrices(int selected);

	/**
	 * Returns a JPanel that contains a JTextField where the user can enter the
	 * customers required quantity of the item, JPanel is layed out
	 * 
	 * @see JTextField
	 * @see JPanel
	 * @return JPanel
	 */
	protected abstract JPanel getQuantityPane();

	/**
	 * Returns a JPanel that contains a Button, that if pressed, will add the
	 * item to the order. ActionListener attached to the button using the
	 * <code>Manager</code> class
	 * 
	 * @see Manager
	 * @see JButton
	 * @see JPanel
	 * @return JPanel
	 */
	protected abstract JPanel getSubmitPane();

	/**
	 * Gets all the information required to populate the ArrayLists from an
	 * external resource file
	 * 
	 * @throws FileNotFoundException
	 */
	public abstract void getFromFile() throws FileNotFoundException;

	/**
	 * Sets the itemSelected by the User
	 * @param selectItem
	 */
	public abstract void setSelectedItem(String selectItem);
	/**
	 * Returns the ItemSelected by the User
	 * @return selectedItem
	 */
	public abstract String getSelectedItem();
	/**
	 * Sets the price of the item that the user has selected
	 * @param itemPrice - <code>String</code>
	 */
	public abstract void setItemPrice(BigDecimal itemPrice);
	/**
	 * Returns the price of the item that the user has selected
	 * @return itemPrice
	 */
	public abstract BigDecimal getItemPrice();

	/**
	 * Sets the Size of the item, only used for Pizzas as drinks and sides do not required a size
	 * @see BigDecimal
	 * @param itemSize  - <code>BigDecimal</code>
	 */
	public abstract void setItemSize(String itemSize);
	/**
	 * Returns the size of the item selected, based on the price selected for pizzas (redundant method for Drinks and Sides)
	 * @return itemSize - <code>String</code>
	 */
	public abstract String getItemSize();
	/**
	 * Sets the Description fo the selected item
	 * @param itemDesc
	 */
	public abstract void setItemDesc(String itemDesc);
	/**
	 * Returns the selelected items' description
	 * @return description - <code>String</code>
	 */
	public abstract String getItemDesc();
	
	/**
	 * Sets the Quantity that the user has entered into the text field
	 */
	public abstract void setQuantity();
	/**
	 * Returns the quantity that the user has entered
	 * @return quantity
	 */
	public abstract int getQuantity();
	/**
	 * Returns the type of item to be added to the order, this is fixed and returns a value based on the type of window opened
	 * @see ItemType
	 * @return ItemType - type of itemFrame (Pizza, Side, or Drink)
	 */
	public abstract ItemType getItemType();
}
