package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class AddItemToOrderPanel extends JPanel implements ActionListener {
	

	private JPanel textHead, itemButtons;
	private JLabel headLabel;
	
	public AddItemToOrderPanel() throws FileNotFoundException{
		this.setSize(512, 700);
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());
		itemButtons = getButtonsPanel();		
		this.add(itemButtons, BorderLayout.WEST);
	}
	
	private JPanel getButtonsPanel(){
		SpringLayout bpsl = new SpringLayout();
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.WHITE);
		buttonPanel.setSize(512, 200);
		JButton pizzas, sides, drinks;
		pizzas = new JButton("Pizzas");
		pizzas.addActionListener(this);
		sides = new JButton("Sides");
		sides.addActionListener(this);
		drinks = new JButton("Drinks");
		drinks.addActionListener(this);
		
		bpsl.putConstraint(SpringLayout.EAST, buttonPanel, 10, SpringLayout.EAST, pizzas);
		bpsl.putConstraint(SpringLayout.EAST, sides, 10, SpringLayout.WEST, pizzas);
		bpsl.putConstraint(SpringLayout.EAST, drinks, 10, SpringLayout.WEST, sides);
		
		buttonPanel.add(pizzas);
		buttonPanel.add(sides);
		buttonPanel.add(drinks);
		return buttonPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.equals("Pizzas")){
			try {
				PizzasTable pt = new PizzasTable();
			} catch (FileNotFoundException e1) {
				e1.getMessage();
			}
		}
	
	}
}
