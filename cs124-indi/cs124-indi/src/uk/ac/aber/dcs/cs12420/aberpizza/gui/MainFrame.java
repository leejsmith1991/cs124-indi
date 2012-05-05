package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;

import uk.ac.aber.dcs.cs12420.aberpizza.data.Order;
import uk.ac.aber.dcs.cs12420.aberpizza.data.Till;

/**
 * MainFrame wihin the program, Allows the user to create a new order, view
 * orders history for the day, or end the current day via access to the
 * <code>Manager</code> class. <br>
 * <br>
 * Has JLists that display all the orders that have been placed for the current
 * day. If the program is closed at anytime during the day when the program
 * reloads the items from the current day are automatically preloaded into the
 * List.<br>
 * <br>
 * Selecting an item in this list displays the reciept for the order in
 * question. This heavily relies on the overridden toString methods in the Order
 * and OrderItem classes to build the receipt
 * 
 * @author Lee Smith
 * 
 */
public class MainFrame extends JFrame implements ListSelectionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -688667233906778489L;

	private MenuBar menuBar;

	private DefaultListModel orders = new DefaultListModel();

	private JList ordersList = new JList(orders);
	private JTextArea indiOrder;

	private ArrayList<Order> ordersArray = new ArrayList<Order>();

	/**
	 * Constructs new JFrame and lays out the components in it. Parameters
	 * include the Manager class, this is used for actionListeners on the button
	 * presses on the Frame; The current Till state, which is used to load the
	 * orders into the JList, and a boolean which specifies if the loaded till
	 * is the main Till being loaded (Current Days Till), and disabled certain
	 * features such as creating a new order for an old till, and disabling the
	 * save function which corrupts the XML data. Also sets the
	 * DefaultCloseOperation of the window depending on the boolean status not
	 * allowing it, if the till is not todays version
	 * 
	 * @param manager
	 * @param till
	 * @param thisDay
	 */
	public MainFrame(Manager manager, Till till, boolean thisDay) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {

		}

		ordersArray = till.getOrdersArray();
		this.addWindowListener(manager);
		menuBar = new MenuBar(manager, thisDay);

		if (thisDay) {
			this.setTitle("Aber Pizza Till for : " + till.getToday());
		} else {
			this.setTitle("Aber Pizza Viewing Till for : " + till.getToday());
		}

		this.setJMenuBar(menuBar);

		this.getContentPane().setLayout(new BorderLayout());

		JPanel buttonPane = new JPanel();
		SpringLayout sl = new SpringLayout();

		JButton addNewOrder, viewSalesHistory, closeForDay;

		addNewOrder = new JButton("Create New Order");
		addNewOrder.addActionListener(manager);
		addNewOrder.setPreferredSize(new Dimension(150, 50));

		viewSalesHistory = new JButton("View Sales History");
		viewSalesHistory.addActionListener(manager);
		viewSalesHistory.setPreferredSize(new Dimension(150, 50));

		closeForDay = new JButton("End Day");
		closeForDay.addActionListener(manager);
		closeForDay.setPreferredSize(new Dimension(150, 50));

		sl.putConstraint(SpringLayout.NORTH, buttonPane, 10,
				SpringLayout.NORTH, addNewOrder);
		sl.putConstraint(SpringLayout.WEST, buttonPane, 20, SpringLayout.WEST,
				addNewOrder);
		sl.putConstraint(SpringLayout.SOUTH, addNewOrder, 10,
				SpringLayout.NORTH, viewSalesHistory);
		sl.putConstraint(SpringLayout.WEST, buttonPane, 20, SpringLayout.WEST,
				viewSalesHistory);
		sl.putConstraint(SpringLayout.SOUTH, viewSalesHistory, 10,
				SpringLayout.NORTH, closeForDay);
		sl.putConstraint(SpringLayout.WEST, buttonPane, 20, SpringLayout.WEST,
				closeForDay);

		buttonPane.add(addNewOrder);
		buttonPane.add(viewSalesHistory);
		buttonPane.add(closeForDay);

		buttonPane.setPreferredSize(new Dimension(170, 150));

		if (!thisDay) {
			addNewOrder.setEnabled(false);
		}

		ordersList.setModel(orders);
		updateArrayList(ordersArray);
		ordersList.setPreferredSize(new Dimension(200, 696));
		ordersList.addListSelectionListener(this);
		ordersList.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		indiOrder = new JTextArea("");
		indiOrder.setEditable(false);
		indiOrder.setPreferredSize(new Dimension(500, 696));
		indiOrder.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		add(buttonPane, BorderLayout.WEST);
		add(ordersList, BorderLayout.CENTER);
		add(indiOrder, BorderLayout.EAST);

		this.setVisible(true);

		if (thisDay) {
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		this.setResizable(false);
		this.setSize(new Dimension(1024, 768));
		this.setLocationRelativeTo(null);
	}

	/**
	 * Updates the JList as a new order is placed.
	 * 
	 * @param l
	 *            - <code>ArrayList</code><<code>Order</code>>
	 */
	public void updateArrayList(ArrayList<Order> l) {
		orders.clear();
		ordersArray = l;
		for (int k = 0; k < ordersArray.size(); k++) {
			orders.addElement(ordersArray.get(k).getCustomerName());
		}
		this.validate();
	}

	/**
	 * Overrides the valueChanged method in <code>ListSelectionListener</code>
	 * so when a item in the list is selected the reciept for that order is
	 * displayed in the TextArea
	 * 
	 * @see ListSelectionListener
	 */

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
