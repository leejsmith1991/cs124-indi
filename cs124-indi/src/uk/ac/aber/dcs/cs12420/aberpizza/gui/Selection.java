package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class Selection extends JPanel {
	
	private Manager manager;
	
	private JButton pizzas, sides, drinks;
	private SpringLayout layout;
	
	private static final long serialVersionUID = 5892174426336388551L;

	public Selection(Manager manager) {
		this.manager = manager;
		
		
	}
}
