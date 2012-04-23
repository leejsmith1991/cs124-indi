package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -688667233906778489L;

	private MenuBar menuBar;

	private Manager manager;
	
	public MainFrame(Manager manager) {
		this.setManager(manager);
		menuBar = new MenuBar(manager);
		this.setLayout(new GridLayout(3, 1));
		this.setJMenuBar(menuBar);
		
		setUILook();
		
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
	
	private void setUILook(){
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}
}
