package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.math.BigDecimal;

import javax.swing.JFrame;

import uk.ac.aber.dcs.cs12420.aberpizza.data.Item;


public abstract class ItemFrame extends JFrame {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 668813998738398121L;
	public abstract Item getOrderItem();
	public abstract void setQuantity();
	public abstract int getQuantity();
	
	public abstract BigDecimal getSubTotal();
	
}
