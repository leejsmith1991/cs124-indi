package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class UpdatePopupMenu extends JPopupMenu {
	JMenuItem updateQuantity, removeItem;
	
	public UpdatePopupMenu(Manager manager){
		updateQuantity = new JMenuItem("Update Quantity");
		updateQuantity.addActionListener(manager);
		this.add(updateQuantity);
		
		removeItem = new JMenuItem("Remove Item");
		removeItem.addActionListener(manager);
		this.add(removeItem);
	}
}
