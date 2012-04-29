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

	protected abstract JPanel getItemPane();
	protected abstract JPanel getPricePane();
	protected abstract void setItemPrices(int selected);
	protected abstract JPanel getQuantityPane();
	protected abstract JPanel getSubmitPane();
	
	public abstract void getFromFile() throws FileNotFoundException;
	
	public abstract void setSelectedItem(String selectItem);
	public abstract String getSelectedItem();
	
	public abstract void setItemPrice(BigDecimal itemPrice);
	public abstract BigDecimal getItemPrice();
	
	public abstract void setItemSize(String itemSize);
	public abstract String getItemSize();
	
	public abstract void setItemDesc(String itemDesc);
	public abstract String getItemDesc();
	
	public abstract void setQuantity();
	public abstract void setQuantity(int quantity);
	public abstract int getQuantity();
	
	public abstract ItemType getItemType();
}
