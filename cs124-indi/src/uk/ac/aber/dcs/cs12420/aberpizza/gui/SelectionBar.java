package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class SelectionBar extends JPanel {
	
	private final int PADDING_TOP = 10;
	private final int PADDING_SIDES = 5;
	
	private Manager manager;
	
	private JButton pizzas, sides, drinks;
	private SpringLayout layout;
	
	private static final long serialVersionUID = 5892174426336388551L;

	public SelectionBar(Manager manager) {
		this.manager = manager;
		
		layout = new SpringLayout();
		this.setLayout(layout);
		
		pizzas = new JButton("Pizzas");
		sides = new JButton("Sides");
		drinks = new JButton("Drinks");
		
		pizzas.addActionListener(manager);
		sides.addActionListener(manager);
		drinks.addActionListener(manager);
		
		pizzas.setPreferredSize(new Dimension(100, 25));
		sides.setPreferredSize(new Dimension(100, 25));
		drinks.setPreferredSize(new Dimension(100, 25));
		
		layout.putConstraint(SpringLayout.NORTH, pizzas, PADDING_TOP, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, pizzas, PADDING_SIDES, SpringLayout.EAST, this);
		
		layout.putConstraint(SpringLayout.NORTH, sides, PADDING_TOP, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, sides, PADDING_SIDES, SpringLayout.EAST, pizzas);
		
		layout.putConstraint(SpringLayout.NORTH, drinks, PADDING_TOP, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, drinks, PADDING_SIDES, SpringLayout.EAST, sides);
		
		this.add(pizzas);
		this.add(sides);
		this.add(drinks);
		
		this.setPreferredSize(new Dimension(1024,35));
		
	}
}
