package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class UpdatePopupMenu extends JPopupMenu {
	JMenuItem updateQuantity, removeItem;
	private Manager manager;
	
	public UpdatePopupMenu(Manager manager){
		this.manager = manager;
		updateQuantity = new JMenuItem("Update Quantity");
		updateQuantity.addActionListener(manager);
		this.add(updateQuantity);
		
		removeItem = new JMenuItem("Remove Item");
		removeItem.addActionListener(manager);
		this.add(removeItem);
	}
}
