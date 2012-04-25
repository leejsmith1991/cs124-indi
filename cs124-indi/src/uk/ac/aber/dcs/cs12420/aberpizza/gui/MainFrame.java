package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import uk.ac.aber.dcs.cs12420.aberpizza.data.Order;
import uk.ac.aber.dcs.cs12420.aberpizza.data.Till;

public class MainFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -688667233906778489L;

	private MenuBar menuBar;

	private Manager manager;
	
	private DefaultListModel orders;
	private JList ordersList;	
	
	private ArrayList<Order> ordersArray;
	
	public MainFrame(Manager manager, ArrayList<Order> orderList) {
		ordersArray = orderList;
		
		this.setManager(manager);
		menuBar = new MenuBar(manager);
		
		this.setJMenuBar(menuBar);

		this.setLayout(null);
		JButton addNewOrder = new JButton("Create New Order");
		addNewOrder.setBounds(5,5,200, 30);
		addNewOrder.addActionListener(manager);
		
		JButton viewSalesHistory = new JButton("View Todays Sales History");
		viewSalesHistory.setBounds(5,40,200, 30);
		viewSalesHistory.addActionListener(manager);
		
		JButton closeForDay = new JButton("End Day");
		closeForDay.setBounds(5,75,200, 30);
		closeForDay.addActionListener(manager);

		orders = new DefaultListModel();
		ordersList = new JList(orders);
		ordersList.setBounds(210, 5, 300, 696);
		
		add(addNewOrder);
		add(viewSalesHistory);
		add(closeForDay);
		add(ordersList);
				
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(1024,768));
	}
	
	public void updateArrayList(ArrayList<Order> l){
		orders.clear();
		ordersArray = l;
		for (int k = 0; k < ordersArray.size(); k++){
			orders.addElement(ordersArray.get(k).getCustomerName());
		}
		this.validate();
	}
	
	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

}
