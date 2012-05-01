package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uk.ac.aber.dcs.cs12420.aberpizza.data.Order;
import uk.ac.aber.dcs.cs12420.aberpizza.data.Till;

public class MainFrame extends JFrame implements ListSelectionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -688667233906778489L;

	private MenuBar menuBar;

	private Manager manager;

	private DefaultListModel orders = new DefaultListModel();

	private JList ordersList = new JList(orders);
	private JTextArea indiOrder;

	private ArrayList<Order> ordersArray = new ArrayList<Order>();

	public MainFrame(Manager manager, Till till, boolean thisDay) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {

		}

		ordersArray = till.getOrdersArray();
		this.addWindowListener(manager);
		this.manager = manager;
		menuBar = new MenuBar(manager, thisDay);

		if (thisDay) {
			this.setTitle("Aber Pizza Till for : " + till.getToday());
		} else {
			this.setTitle("Aber Pizza Viewing Till for : " + till.getToday());
		}
		
		this.setJMenuBar(menuBar);

		this.setLayout(null);
		JButton addNewOrder = new JButton("Create New Order");
		if (!thisDay) {
			addNewOrder.setEnabled(false);
		}

		addNewOrder.setBounds(5, 5, 200, 30);
		addNewOrder.addActionListener(manager);

		JButton viewSalesHistory = new JButton("View Sales History");
		viewSalesHistory.setBounds(5, 40, 200, 30);
		viewSalesHistory.addActionListener(manager);

		JButton closeForDay = new JButton("End Day");
		closeForDay.setBounds(5, 75, 200, 30);
		closeForDay.addActionListener(manager);

		ordersList.setModel(orders);
		updateArrayList(ordersArray);
		ordersList.setBounds(210, 5, 300, 696);
		ordersList.addListSelectionListener(this);
		indiOrder = new JTextArea("");
		indiOrder.setBounds(520, 5, 300, 696);

		add(addNewOrder);
		add(viewSalesHistory);
		add(closeForDay);
		add(ordersList);
		add(indiOrder);

		this.setVisible(true);

		if (thisDay) {
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

		this.setSize(new Dimension(1024, 768));
	}

	public void updateArrayList(ArrayList<Order> l) {
		orders.clear();
		ordersArray = l;
		for (int k = 0; k < ordersArray.size(); k++) {
			orders.addElement(ordersArray.get(k).getCustomerName());
		}
		this.validate();
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		int selectedIndex = ordersList.getSelectedIndex();

		if (selectedIndex == -1) {
			selectedIndex = 0;
		}

		Order selectedOrder = ordersArray.get(selectedIndex);
		indiOrder.setText(selectedOrder.getReceipt());
	}
}
