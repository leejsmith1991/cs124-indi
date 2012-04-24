package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.*;
import java.math.BigDecimal;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import uk.ac.aber.dcs.cs12420.aberpizza.data.ItemType;

public class NewOrder extends JFrame implements ActionListener{

	private static final long serialVersionUID = 4978171281055317618L;
	private Manager manager;
	private ItemFrame itemFrame;
	
	private JPanel customerNamePane, listPane, buttonPane, subDiscPane, payCancelPane;
	private JTextField customerName;
	
	private DefaultTableModel tableList= new DefaultTableModel(0, 3);
	private BigDecimal totalForOrder = new BigDecimal("0");
	
	private JLabel subText;
	
	public NewOrder(Manager manager) {
		this.manager = manager;
		
		//TODO Change to SpringLayout
		this.setLayout(new GridLayout(5, 1));
		
		customerNamePane = getCustomerNamePane();
		this.add(customerNamePane);
		
		listPane = getOrderListPane();
		this.add(listPane);
		
		buttonPane = getButtonPane();
		this.add(buttonPane);
		
		subDiscPane = getSubDiscPane();
		this.add(subDiscPane);
		
		payCancelPane = getPayCancelPane();
		this.add(payCancelPane);
		
		this.setVisible(true);
		this.setSize(new Dimension(500,700));
	}

	private JPanel getCustomerNamePane(){
		JPanel thisPane = new JPanel(new GridLayout(1,2));
		
		JLabel nameLabel = new JLabel("Enter Customers Name:");
		customerName = new JTextField();
		thisPane.add(nameLabel);
		thisPane.add(customerName);		
		thisPane.setSize(new Dimension(500, 50));
		return thisPane;
	}

	private JPanel getOrderListPane(){
		JPanel thisPane = new JPanel();
		
		JTable orderList = new JTable(tableList);
		thisPane.add(orderList);
		
		return thisPane;	
	}
	
	private JPanel getButtonPane(){
		//SpringLayout bpsl = new SpringLayout();
		JPanel thisPane = new JPanel(new GridLayout(1,3));
		
		
		JButton pizzas, sides, drinks;
		pizzas = new JButton("Add Pizza");
		pizzas.addActionListener(manager);
		sides = new JButton("Add Side");
		sides.addActionListener(manager);
		drinks = new JButton("Add Drink");
		drinks.addActionListener(manager);

/*		bpsl.putConstraint(SpringLayout.EAST, thisPane, 10,
				SpringLayout.EAST, pizzas);
		bpsl.putConstraint(SpringLayout.EAST, sides, 10, SpringLayout.WEST,
				pizzas);
		bpsl.putConstraint(SpringLayout.EAST, drinks, 10, SpringLayout.WEST,
				sides);
		thisPane.setLayout(bpsl);*/
		
		thisPane.add(pizzas);
		thisPane.add(sides);
		thisPane.add(drinks);
		
		return thisPane;
	}

	private JPanel getSubDiscPane(){
		JPanel thisPane = new JPanel(new GridLayout(2,2));
		
		JLabel subLabel, discLabel, discText;
		subLabel = new JLabel("Subtotal:");
		thisPane.add(subLabel);
		subText = new JLabel("Subtotal for Order // to-do");
		thisPane.add(subText);
		discLabel = new JLabel("Amount of Discount");
		thisPane.add(discLabel);
		discText = new JLabel("Discount // to-do");
		thisPane.add(discText);
		
		return thisPane;
	}
	
	private JPanel getPayCancelPane(){
		JPanel thisPane = new JPanel(new GridLayout(1,2));
		
		JButton pay, cancel;
		
		pay = new JButton("Pay");
		pay.addActionListener(manager);
		thisPane.add(pay);
		
		cancel = new JButton("Cancel Order");
		cancel.addActionListener(this);
		thisPane.add(cancel);
		
		return thisPane;
	}
	
	public void addItemToTable(String name, int quantity, BigDecimal itemTotal){
		String[] singleItem = new String[3];
		singleItem[0] = name;
		singleItem[1] = Integer.toString(quantity);
		singleItem[2] = itemTotal.toString();
		tableList.addRow(singleItem);
		totalForOrder = totalForOrder.add(itemTotal);
		subText.setText(totalForOrder.toString());
		this.validate();		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		
		if (action.equals("Cancel Order")){
			this.dispose();
		}
		
	}
}
