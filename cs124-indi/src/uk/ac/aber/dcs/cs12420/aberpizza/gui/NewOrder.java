package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uk.ac.aber.dcs.cs12420.aberpizza.data.Order;

public class NewOrder extends JFrame implements ActionListener,
		ListSelectionListener, MouseListener {

	private static final long serialVersionUID = 4978171281055317618L;

	/**
	 * Manager for use of the Listeners
	 */
	private Manager manager;

	private JPanel customerNamePane, listPane, buttonPane, subDiscPane,
			payCancelPane;
	private JTextField customerText;

	private JList orderList;
	private DefaultListModel tableList = new DefaultListModel();
	/**
	 * Holds the current total of the order
	 */
	private JLabel subText, discText, totalText;
	/**
	 * Holds the currently selected index for item selected in the List that
	 * displays items in the current order
	 */
	private int selectedIndex = 0;
	/**
	 * Holds the name of the customer
	 */
	private String customerName;

	/**
	 * Construst a new order frame, and lays out the components
	 * 
	 * @see Manager
	 * @param manager
	 */
	public NewOrder(Manager manager) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {

		}

		this.manager = manager;
		this.getContentPane().setLayout(new BorderLayout());

		customerNamePane = getCustomerNamePane();
		this.add(customerNamePane, BorderLayout.NORTH);

		buttonPane = getButtonPane();
		this.add(buttonPane, BorderLayout.WEST);

		JPanel leftPane = new JPanel(new BorderLayout());
		leftPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		listPane = getOrderListPane();

		subDiscPane = getSubDiscPane();

		leftPane.add(listPane, BorderLayout.CENTER);
		leftPane.add(subDiscPane, BorderLayout.SOUTH);

		this.add(leftPane, BorderLayout.EAST);

		payCancelPane = getPayCancelPane();
		this.add(payCancelPane, BorderLayout.SOUTH);

		this.setVisible(true);
		this.setResizable(false);
		this.setSize(new Dimension(1024, 768));
	}

	/**
	 * Gets the Selected index for the List item selected
	 * 
	 * @return selectedIndex
	 */

	public int getSelectedIndex() {
		return selectedIndex;
	}

	/**
	 * Creates pane that allows user to enter the customers name
	 * 
	 * @see JPanel
	 * @see JLabel
	 * @see JTextField
	 * @return JPanel with components for Entering name
	 */

	private JPanel getCustomerNamePane() {
		JPanel thisPane = new JPanel();

		JLabel nameLabel = new JLabel("Enter Customers Name:",
				SwingConstants.RIGHT);
		nameLabel.setPreferredSize(new Dimension(200, 25));
		customerText = new JTextField();
		customerText.setPreferredSize(new Dimension(200, 25));

		thisPane.add(nameLabel);
		thisPane.add(customerText);
		thisPane.setPreferredSize(new Dimension(1024, 35));

		return thisPane;
	}

	/**
	 * JPanel with 3 Buttons that represent Pizzas, Sides, and Drinks
	 * 
	 * @see JPanel
	 * @see JButton
	 * @return JPanel with Buttons for selecting Pizzas, Sides or Drinks
	 */

	private JPanel getButtonPane() {
		JPanel thisPane = new JPanel();
		SpringLayout sl = new SpringLayout();

		JButton pizzas, sides, drinks;

		pizzas = new JButton("Add Pizza");
		pizzas.addActionListener(manager);
		pizzas.setPreferredSize(new Dimension(150, 50));

		sides = new JButton("Add Side");
		sides.addActionListener(manager);
		sides.setPreferredSize(new Dimension(150, 50));

		drinks = new JButton("Add Drink");
		drinks.addActionListener(manager);
		drinks.setPreferredSize(new Dimension(150, 50));

		sl.putConstraint(SpringLayout.NORTH, thisPane, 10, SpringLayout.NORTH,
				pizzas);
		sl.putConstraint(SpringLayout.WEST, thisPane, 20, SpringLayout.WEST,
				pizzas);
		sl.putConstraint(SpringLayout.SOUTH, pizzas, 10, SpringLayout.NORTH,
				sides);
		sl.putConstraint(SpringLayout.WEST, thisPane, 20, SpringLayout.WEST,
				sides);
		sl.putConstraint(SpringLayout.SOUTH, sides, 10, SpringLayout.NORTH,
				drinks);
		sl.putConstraint(SpringLayout.WEST, thisPane, 20, SpringLayout.WEST,
				drinks);

		thisPane.add(pizzas);
		thisPane.add(sides);
		thisPane.add(drinks);

		thisPane.setPreferredSize(new Dimension(170, 150));
		return thisPane;
	}

	/**
	 * JPanel that holds a JList containing items in the current order, with
	 * Quantity, Item and OrderItemTotal
	 * 
	 * @see JPanel
	 * @see JList
	 * @return JPanel containing JList with order items in
	 */
	private JPanel getOrderListPane() {
		JPanel thisPane = new JPanel(new BorderLayout());

		JLabel label = new JLabel("Items in Order");
		label.setPreferredSize(new Dimension(800, 25));
		thisPane.add(label, BorderLayout.NORTH);

		orderList = new JList(tableList);
		orderList.addMouseListener(this);
		orderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scroll = new JScrollPane(orderList);
		scroll.setPreferredSize(new Dimension(800, 300));

		thisPane.add(scroll, BorderLayout.CENTER);
		thisPane.setSize(800, 345);
		return thisPane;
	}

	/**
	 * JPanel containing information on the Subtotal for the Order, Discounts
	 * that have become effective, and the order total which is the subtotal -
	 * discounts
	 * 
	 * @see JPanel
	 * @see JLabel
	 * @return JPanel containing labels displaying information on subtotal,
	 *         discounts and order total
	 */

	private JPanel getSubDiscPane() {
		JPanel thisPane = new JPanel(new GridLayout(3, 2));

		JLabel subLabel, discLabel, totalLabel;

		subLabel = new JLabel("Subtotal : £", SwingConstants.RIGHT);
		thisPane.add(subLabel);

		subText = new JLabel("0.00");
		thisPane.add(subText);

		discLabel = new JLabel("Amount of Discount : £", SwingConstants.RIGHT);
		thisPane.add(discLabel);

		discText = new JLabel("0.00");
		thisPane.add(discText);

		totalLabel = new JLabel("Order Total : £", SwingConstants.RIGHT);
		thisPane.add(totalLabel);

		totalText = new JLabel("0.00");
		thisPane.add(totalText);

		thisPane.setSize(850, 150);
		return thisPane;
	}

	/**
	 * JPanel containing 2 buttons allowing the user to Pay the order, or cancel
	 * it
	 * 
	 * @see JPanel
	 * @see JButton
	 * @return JPanel containing the 2 buttons
	 */
	private JPanel getPayCancelPane() {
		JPanel thisPane = new JPanel();
		JButton pay, cancel;

		pay = new JButton("Pay");
		pay.addActionListener(manager);
		pay.setPreferredSize(new Dimension(150, 50));
		thisPane.add(pay);

		cancel = new JButton("Cancel Order");
		cancel.addActionListener(this);
		cancel.setPreferredSize(new Dimension(150, 50));

		thisPane.add(cancel);
		thisPane.setSize(1024, 60);

		return thisPane;
	}

	/**
	 * Checks if the name entered for the customer is not empty, if so produce
	 * input pane where user can enter the name
	 * 
	 * @see JOptionPane
	 * @return customerName
	 */

	public String getCustomerName() {
		return customerName;
	}

	/**
	 * Sets the customer name, and checks for
	 */

	public void setCustomerName() {
		if (customerText.getText().equals("")) {
			customerName = JOptionPane.showInputDialog(null,
					"Enter Customer Name");
		} else {
			customerName = customerText.getText();
		}
	}

	/**
	 * Updates the table when an item is added, updated or removed
	 * 
	 * @param order
	 */

	public void updateTable(Order order) {
		tableList.clear();
		for (int i = 0; i < order.getItems().size(); i++) {
			tableList.addElement(order.getItems().get(i).toString());
		}

		setSubTextText(order);
		setDiscountText(order);
		this.validate();
	}

	/**
	 * Sets the subtotal text on the screen for user to see.
	 * 
	 * @param order
	 */

	public void setSubTextText(Order order) {
		subText.setText(order.getSubtotal().toString());
	}

	/**
	 * Sets the discounts amount text on screen, also updates total for order
	 * text based on the discounts applied
	 * 
	 * @param order
	 */
	public void setDiscountText(Order order) {
		discText.setText(order.getDiscount().toString());
		totalText.setText(order.getOrderTotal().toString());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();

		if (action.equals("Cancel Order")) {
			this.dispose();
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		selectedIndex = orderList.getSelectedIndex();

		if (selectedIndex == -1) {
			selectedIndex = 0;
		}
	}

	public void mouseClicked(MouseEvent me) {
		if (SwingUtilities.isRightMouseButton(me)
				&& !orderList.isSelectionEmpty()) {
			UpdatePopupMenu up = new UpdatePopupMenu(manager);
			up.show(orderList, me.getX(), me.getY());
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
