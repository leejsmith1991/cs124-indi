package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.math.BigDecimal;

import javax.swing.JFrame;

import uk.ac.aber.dcs.cs12420.aberpizza.data.Item;
import uk.ac.aber.dcs.cs12420.aberpizza.data.ItemType;


public abstract class ItemFrame extends JFrame {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 668813998738398121L;

	public abstract void setSelectedItem(String selectItem);
	public abstract String getSelectedItem();
	
	public abstract void setItemPrice(BigDecimal itemPrice);
	public abstract BigDecimal getItemPrice();
	
	public abstract void setItemSize(String itemSize);
	public abstract String getItemSize();
	
	public abstract void setItemDesc(String itemDesc);
	public abstract String getItemDesc();
	
	public abstract void setQuantity();
	public abstract int getQuantity();
	
	public abstract ItemType getItemType();
	
	public abstract BigDecimal getSubTotal();
	
}
