package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class SelectionBar extends JPanel {
	
	private final int PADDING = 10;
	private Manager manager;
	
	private JButton pizzas, sides, drinks;
	private SpringLayout layout;
	private JList list;
	
	Font f = new Font("Calibri", Font.PLAIN, 12);
	
	private static final long serialVersionUID = 5892174426336388551L;

	public SelectionBar(Manager manager) {
		this.manager = manager;
		/*
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
		
		layout.putConstraint(SpringLayout.NORTH, pizzas, PADDING, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, pizzas, PADDING, SpringLayout.WEST, this);
		
		layout.putConstraint(SpringLayout.NORTH, sides, PADDING, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, sides, PADDING, SpringLayout.EAST, pizzas);
		
		layout.putConstraint(SpringLayout.NORTH, drinks, PADDING, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, drinks, PADDING, SpringLayout.EAST, sides);
		
		this.add(pizzas);
		this.add(sides);
		this.add(drinks);
		*/
		
		
		
		this.setBackground(Color.WHITE);
		
		this.setPreferredSize(new Dimension(512,45));
		this.setVisible(true);		
	}
}
