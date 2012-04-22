package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import uk.ac.aber.dcs.cs12420.aberpizza.data.Till;

public class MainFrame extends JFrame {
	private MenuBar menuBar;

	private Manager manager;
	
	public MainFrame(Manager manager) {
		this.manager = manager;
		menuBar = new MenuBar(manager);
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
		this.setVisible(true);
		this.setSize(new Dimension(200,300));
	}
}
