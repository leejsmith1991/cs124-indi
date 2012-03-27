package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.Container;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import uk.ac.aber.dcs.cs12420.aberpizza.data.*;

public class AddNewItemWindow extends JFrame implements ActionListener{
	private static final long serialVersionUID = -4708376627751121406L;

	private ItemType type;
	private Item item;
	// private JPanel namePane, pricePane, descriptionPane, toppingsPaneMain,
	// toppingsPane;
	private JTextField nameText, priceText, descriptionText;
	private JLabel nameLabel, priceLabel, descriptionLabel, panTypeLabel,
			toppingsLabel;
	private JPanel namePane, pricePane, toppingsPane, panTypePane, submitPane;
	private SpringLayout mainLayout;

	Font f = new Font("Arial", Font.PLAIN, 12);

	public AddNewItemWindow(ItemType type) {
		this.type = type;

		this.setTitle("Create new " + type.toString().toLowerCase());
		//this.setSize(400, 400);
		
		Container mainPane = this.getContentPane();
		mainLayout = new SpringLayout();
		
		
		namePane = new JPanel(new GridLayout(1,2,5,5));
		namePane.add(getNameLabel());
		namePane.add(getNameText());
		
		pricePane = new JPanel(new GridLayout(1,2,5,5));
		pricePane.add(getPriceLabel());
		pricePane.add(getPriceText());
		
		
		
		mainPane.add(namePane);
		mainPane.add(pricePane);
		mainLayout.putConstraint(SpringLayout.NORTH, namePane,10, SpringLayout.NORTH, this);
		mainLayout.putConstraint(SpringLayout.NORTH, pricePane, 10, SpringLayout.SOUTH, namePane);
		
		JButton submit =  new JButton("Submit");
		submit.addActionListener(this);
		submit.setSize(new Dimension(100, 20));
		
		submitPane = new JPanel();
		submit.setSize(150, 50);
		submitPane.add(submit);
			
		if (type == ItemType.PIZZA){
			panTypePane = new JPanel(new GridLayout(1,2));
			panTypePane.add(getPanTypeLabel());
			panTypePane.add(getPizzaPanType());
			mainPane.add(panTypePane);	
			mainLayout.putConstraint(SpringLayout.NORTH, panTypePane, 10, SpringLayout.SOUTH, pricePane);
			
			toppingsPane = new JPanel(new GridLayout(1,2));
			toppingsPane.add(getPizzaToppingsLabel());
			toppingsPane.add(getPizzaToppings());
			mainPane.add(toppingsPane);
			mainLayout.putConstraint(SpringLayout.NORTH, toppingsPane, 10, SpringLayout.SOUTH, panTypePane);
			mainLayout.putConstraint(SpringLayout.NORTH, submitPane, 10, SpringLayout.SOUTH, toppingsPane);	
		} else {
			mainLayout.putConstraint(SpringLayout.NORTH, submitPane, 10, SpringLayout.SOUTH, pricePane);	
		}
						
		mainPane.add(submitPane);
		mainPane.setLayout(mainLayout);
		
		//this.pack();
		//this.setResizable(false);
		this.setVisible(true);
		this.setSize(namePane.getWidth()+ 18, 500);
		
		if (type == ItemType.PIZZA) {
			item = new Pizzas();
		} else if (type == ItemType.SIDE) {
			item = new Sides();
		} else if (type == ItemType.DRINK) {
			item = new Drinks();
		}

	}

	private JLabel getNameLabel() {
		nameLabel = new JLabel("Enter the name of the "
				+ type.toString().toLowerCase() + " :", SwingConstants.LEFT);
		nameLabel.setFont(f);
		return nameLabel;
	}

	private JTextField getNameText() {
		nameText = new JTextField("", 15);
		nameText.setFont(f);
		return nameText;
	}

	private JLabel getPriceLabel() {
		priceLabel = new JLabel("Enter the price of the "
				+ type.toString().toLowerCase() + " :", SwingConstants.LEFT);
		priceLabel.setFont(f);
		return priceLabel;
	}

	private JTextField getPriceText() {
		priceText = new JTextField("", 15);
		priceText.setFont(f);
		return priceText;
	}

	private JLabel getPanTypeLabel() {
		panTypeLabel = new JLabel("Enter the Pan Type of the Pizza :");
		panTypeLabel.setVerticalAlignment(SwingConstants.NORTH);
		panTypeLabel.setFont(f);
		return panTypeLabel;
	}

	private JPanel getPizzaPanType() {
		JPanel panPane = new JPanel(new GridLayout(3, 1));
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

	private JLabel getPizzaToppingsLabel() {
		toppingsLabel = new JLabel("Enter the toppings for the Pizza :");
		toppingsLabel.setVerticalAlignment(SwingConstants.NORTH);
		toppingsLabel.setFont(f);
		return toppingsLabel;
	}

	private JPanel getPizzaToppings() {
		JPanel toppingsPane = new JPanel(new GridLayout(0, 1));

		JCheckBox mushrooms = new JCheckBox("Mushrooms");
		mushrooms.setFont(f);
		toppingsPane.add(mushrooms);

		JCheckBox sweetcorn = new JCheckBox("SweetCorn");
		sweetcorn.setFont(f);
		toppingsPane.add(sweetcorn);

		JCheckBox beef = new JCheckBox("Beef");
		beef.setFont(f);
		toppingsPane.add(beef);

		JCheckBox onions = new JCheckBox("Onions");
		onions.setFont(f);
		toppingsPane.add(onions);

		JCheckBox peppers = new JCheckBox("Peppers");
		peppers.setFont(f);
		toppingsPane.add(peppers);

		JCheckBox jalapenos = new JCheckBox("Jalapenos");
		jalapenos.setFont(f);
		toppingsPane.add(jalapenos);

		JCheckBox ham = new JCheckBox("Ham");
		ham.setFont(f);
		toppingsPane.add(ham);

		JCheckBox pepperoni = new JCheckBox("Pepperoni");
		pepperoni.setFont(f);
		toppingsPane.add(pepperoni);

		return toppingsPane;
	}
	private void createNew(){
		System.out.println("Created");
		
		if (type == ItemType.PIZZA) {
			item = new Pizzas();
		} else if (type == ItemType.SIDE) {
			item = new Sides();
		} else if (type == ItemType.DRINK) {
			item = new Drinks();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("Submit")){
			createNew();
		}
		
	}
}
