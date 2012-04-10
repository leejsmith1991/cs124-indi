package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import javax.swing.JTabbedPane;

public class SelectionTabbed extends JTabbedPane {
	private Manager manager;
	public SelectionTabbed(Manager m){
		super(JTabbedPane.TOP);
		this.manager = m;
		
		
		
		this.addTab("Pizzas", null);
		this.addTab("Sides", null);
		this.addTab("Drinks", null);
	}
}
