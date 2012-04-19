package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import uk.ac.aber.dcs.cs12420.aberpizza.data.Item;


public abstract class ItemFrame extends JFrame {
		
	public abstract Item getOrderItem();
	public abstract int getQuantity();
	
}
