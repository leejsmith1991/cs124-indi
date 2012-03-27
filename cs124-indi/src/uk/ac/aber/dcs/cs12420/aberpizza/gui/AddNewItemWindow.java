package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import uk.ac.aber.dcs.cs12420.aberpizza.data.*;

public class AddNewItemWindow extends JFrame {
	private static final long serialVersionUID = -4708376627751121406L;

	private ItemType type;
	private Item item;
	//private JPanel namePane, pricePane, descriptionPane, toppingsPaneMain, toppingsPane;
	private JTextField nameText, priceText, descriptionText;
	private JLabel nameLabel, priceLabel, descriptionLabel, panTypeLabel, toppingsLabel;
	private JPanel namePane, pricePane, toppingsPane;
	private SpringLayout mainLayout;

	Font f = new Font("Arial", Font.PLAIN, 12);

	public AddNewItemWindow(ItemType type) {
		this.type = type;

		this.setTitle("Create new " + type.toString().toLowerCase());
		//this.setSize(new Dimension(400, 300));
		
		Container mainPane = this.getContentPane();
		mainLayout = new SpringLayout();
		mainPane.setLayout(mainLayout);
		
		namePane.setLayout(new GridLayout(1,2));
		namePane.add(getNameLabel(), SwingConstants.RIGHT);
		namePane.add(getNameText());
		
		mainPane.add(namePane);
		
		mainPane.add(getPriceLabel(), SwingConstants.RIGHT);
		mainPane.add(getPriceText());
		
		if (type == ItemType.PIZZA){
			mainPane.add(getPanTypeLabel());
			mainPane.add(getPizzaPanType());
			mainPane.add(getPizzaToppingsLabel());
			mainPane.add(getPizzaToppings());
		}
		
		
		
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
		
		if (type == ItemType.PIZZA) {
			item = new Pizzas();
		} else if (type == ItemType.SIDE) {
			item = new Sides();
		} else if (type == ItemType.DRINK) {
			item = new Drinks();
		}

	}

	private JLabel getNameLabel(){
		nameLabel = new JLabel("Enter the name of the "
				+ type.toString().toLowerCase() + " :", SwingConstants.RIGHT);
		nameLabel.setFont(f);
		return nameLabel;
	}
	
	private JTextField getNameText(){
		nameText = new JTextField("", 20);
		nameText.setFont(f);
		nameText.setSize(200 ,f.getSize());
		return nameText;
	}
	
	private JLabel getPriceLabel(){
		priceLabel = new JLabel("Enter the price of the "
				+ type.toString().toLowerCase() + " :", SwingConstants.RIGHT);
		priceLabel.setFont(f);
		return priceLabel;
	}
	private JTextField getPriceText(){
		priceText = new JTextField("", 20);
		priceText.setFont(f);
		return priceText;
	}

	private JLabel getPanTypeLabel(){
		panTypeLabel = new JLabel("Enter the Pan Type of the Pizza :", SwingConstants.RIGHT);
		panTypeLabel.setFont(f);
		return panTypeLabel;
	}
	
	private JPanel getPizzaPanType() {
		JPanel panPane = new JPanel(new GridLayout(3, 1));
		panPane.setSize(400, 200);

		AbstractButton stuffedCrust, thinBase, deepPan;

		ButtonGroup base = new ButtonGroup();

		stuffedCrust = new JRadioButton("Stuffed Crust");
		stuffedCrust.setFont(f);
		base.add(stuffedCrust);
		panPane.add(stuffedCrust);

		thinBase = new JRadioButton("Thin Base");
		thinBase.setFont(f);
		base.add(thinBase);
		panPane.add(thinBase);

		deepPan = new JRadioButton("Deep Pan");
		deepPan.setFont(f);
		base.add(deepPan);
		panPane.add(deepPan);
		
		return panPane;
	}
	private JLabel getPizzaToppingsLabel(){
		toppingsLabel = new JLabel("Enter the toppings for the Pizza :", SwingConstants.NORTH_EAST);
		toppingsLabel.setFont(f);
		return toppingsLabel;
	}
	private JPanel getPizzaToppings(){
		JPanel toppingsPane = new JPanel(new GridLayout(0,1));
		
		JCheckBox mushrooms = new JCheckBox("Mushrooms");
		toppingsPane.add(mushrooms);
		
		JCheckBox sweetcorn = new JCheckBox("SweetCorn");
		toppingsPane.add(sweetcorn);
		
		JCheckBox beef = new JCheckBox("Beef");
		toppingsPane.add(beef);
		
		JCheckBox onions = new JCheckBox("Onions");
		toppingsPane.add(onions);
		
		JCheckBox peppers = new JCheckBox("Peppers");
		toppingsPane.add(peppers);
		
		JCheckBox jalapenos = new JCheckBox("Jalapenos");
		toppingsPane.add(jalapenos);
		
		JCheckBox ham = new JCheckBox("Ham");
		toppingsPane.add(ham);
		
		JCheckBox pepperoni = new JCheckBox("Pepperoni");
		toppingsPane.add(pepperoni);
				
		for (int i = 0; i > toppingsPane.getComponentCount(); i++){
			toppingsPane.getComponent(i).setFont(f);
		}
		
		return toppingsPane;
		
	}
}
