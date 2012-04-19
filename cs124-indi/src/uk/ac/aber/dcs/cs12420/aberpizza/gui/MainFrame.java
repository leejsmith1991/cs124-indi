package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import uk.ac.aber.dcs.cs12420.aberpizza.data.Till;

public class MainFrame extends JFrame {
	Till till;
	private MenuBar menuBar;

	Manager manager;

	public MainFrame(Manager manager) {
		this.manager = manager;

		this.setLayout(new GridLayout(3, 1));
		this.setJMenuBar(menuBar);
		
		JButton addNewOrder = new JButton("Create New Order");
		JButton viewSalesHistory = new JButton("View Todays Sales History");
		JButton closeForDay = new JButton("End Day");

		addNewOrder.addActionListener(manager);
		viewSalesHistory.addActionListener(manager);
		closeForDay.addActionListener(manager);

		add(addNewOrder);
		add(viewSalesHistory);
		add(closeForDay);
	}
}
