package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import uk.ac.aber.dcs.cs12420.aberpizza.data.*;

public class AddNewItemWindow extends JFrame {

	private static final long serialVersionUID = -4708376627751121406L;
	
	private ItemType type;
	private Item item;
	
	public AddNewItemWindow(ItemType type){
		this.type = type;
		
		this.setTitle("Create new " + type.toString().toLowerCase());
		this.setSize(new Dimension(300, 500));
		this.setResizable(false);
		this.setVisible(true);
			
		JPanel main = new JPanel();
		
		main.setPreferredSize(new Dimension(1024, 768));
		main.setBackground(Color.BLUE);
		add(main);
		
		if (type == ItemType.PIZZA){
			item = new Pizzas();
		} else if (type == ItemType.SIDE){
			item = new Sides();
		} else if (type == ItemType.DRINK){
			item = new Drinks();
		}
		
		
	}
	
	
}
