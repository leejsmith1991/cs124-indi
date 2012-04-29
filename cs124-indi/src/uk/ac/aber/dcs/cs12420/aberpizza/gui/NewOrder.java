package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;

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
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uk.ac.aber.dcs.cs12420.aberpizza.data.Item;
import uk.ac.aber.dcs.cs12420.aberpizza.data.ItemType;
import uk.ac.aber.dcs.cs12420.aberpizza.data.Order;
import uk.ac.aber.dcs.cs12420.aberpizza.data.OrderItem;

public class NewOrder extends JFrame implements ActionListener,
		ListSelectionListener {

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
	private BigDecimal totalForOrder = new BigDecimal("0");
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
	 * @see Manager
	 * @param manager
	 */
	public NewOrder(Manager manager) {
		this.manager = manager;
		this.setLayout(null);

		customerNamePane = getCustomerNamePane();
		customerNamePane.setBounds(0, 0, customerNamePane.getWidth(),
				customerNamePane.getHeight());
		this.add(customerNamePane);

		buttonPane = getButtonPane();
		buttonPane.setBounds(0, customerNamePane.getHeight(),
				buttonPane.getWidth(), buttonPane.getHeight());
		this.add(buttonPane);

		listPane = getOrderListPane();
		listPane.setBounds(0,
				customerNamePane.getHeight() + buttonPane.getHeight(),
				listPane.getWidth(), listPane.getHeight());
		this.add(listPane);

		subDiscPane = getSubDiscPane();
		subDiscPane.setBounds(0,
				customerNamePane.getHeight() + buttonPane.getHeight()
						+ listPane.getHeight(), subDiscPane.getWidth(),
				subDiscPane.getHeight());
		this.add(subDiscPane);

		payCancelPane = getPayCancelPane();
		payCancelPane.setBounds(0,
				customerNamePane.getHeight() + buttonPane.getHeight()
						+ listPane.getHeight() + subDiscPane.getHeight(),
				payCancelPane.getWidth(), subDiscPane.getHeight());
		this.add(payCancelPane);

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
		JPanel thisPane = new JPanel(null);

		JLabel nameLabel = new JLabel("Enter Customers Name:",
				SwingConstants.RIGHT);
		nameLabel.setBounds(5, 5, 150, 25);
		customerText = new JTextField();
		customerText.setBounds(160, 5, 150, 25);
		thisPane.add(nameLabel);
		thisPane.add(customerText);
		thisPane.setSize(new Dimension(1024, 35));
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
		JPanel thisPane = new JPanel(null);

		JButton pizzas, sides, drinks;
		pizzas = new JButton("Add Pizza");
		pizzas.addActionListener(manager);
		pizzas.setBounds(100, 10, 200, 150);

		sides = new JButton("Add Side");
		sides.addActionListener(manager);
		sides.setBounds(320, 10, 200, 150);

		drinks = new JButton("Add Drink");
		drinks.addActionListener(manager);
		drinks.setBounds(540, 10, 200, 150);

		thisPane.add(pizzas);
		thisPane.add(sides);
		thisPane.add(drinks);
		thisPane.setSize(1024, 170);
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
		JPanel thisPane = new JPanel(null);

		JLabel label = new JLabel("Items in Order");
		label.setBounds(5, 5, 1000, 25);

		orderList = new JList(tableList);
		orderList.addMouseListener(manager);
		orderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		UpdatePopupMenu uq = new UpdatePopupMenu(manager);
		orderList.addListSelectionListener(this);
		orderList.setComponentPopupMenu(uq);

		orderList.setLocation(0, 0);

		JScrollPane scroll = new JScrollPane(orderList);
		scroll.setBounds(5, 35, 1000, 200);

		thisPane.add(scroll);
		thisPane.setSize(1024, label.getHeight() + scroll.getHeight() + 15);
		return thisPane;
	}

	/**
	 * JPanel containing information on the Subtotal for the Order, Discounts
	 * that have become effective, and the order total which is the subtotal -
	 * discounts
	 * @see JPanel
	 * @see JLabel
	 * @return JPanel containing labels displaying information on subtotal, discounts and order total
	 */

	private JPanel getSubDiscPane() {
		JPanel thisPane = new JPanel(null);

		JLabel subLabel, discLabel, totalLabel;

		subLabel = new JLabel("Subtotal : £", SwingConstants.RIGHT);
		subLabel.setBounds(150, 5, 250, 25);
		thisPane.add(subLabel);

		subText = new JLabel("0.00");
		subText.setBounds(420, 5, 250, 25);
		thisPane.add(subText);

		discLabel = new JLabel("Amount of Discount : £", SwingConstants.RIGHT);
		discLabel.setBounds(150, 35, 250, 25);
		thisPane.add(discLabel);

		discText = new JLabel("0.00");
		discText.setBounds(420, 35, 250, 25);
		thisPane.add(discText);

		totalLabel = new JLabel("Order Total : £", SwingConstants.RIGHT);
		totalLabel.setBounds(150, 65, 250, 25);
		thisPane.add(totalLabel);

		totalText = new JLabel("0.00");
		totalText.setBounds(420, 65, 250, 25);
		thisPane.add(totalText);

		thisPane.setSize(1024, 150);
		return thisPane;
	}
/**
 * JPanel containing 2 buttons allowing the user to Pay the order, or cancel it
 * @see JPanel
 * @see JButton
 * @return JPanel containing the 2 buttons
 */
	private JPanel getPayCancelPane() {
		JPanel thisPane = new JPanel(null);

		JButton pay, cancel;

		pay = new JButton("Pay");
		pay.addActionListener(manager);
		pay.setBounds(137, 5, 350, 90);
		thisPane.add(pay);

		cancel = new JButton("Cancel Order");
		cancel.addActionListener(this);
		cancel.setBounds(537, 5, 350, 90);
		thisPane.add(cancel);
		thisPane.setSize(1024, 100);
		return thisPane;
	}

	/**
	 * Checks if the name entered for the customer is not empty, if so produce input pane where user can enter the name
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
	 * @param order 
	 */
	
	public void updateTable(Order order) {
		tableList.clear();
		for (int i = 0; i < order.getItems().size(); i++) {
			tableList.addElement(order.getItems().get(i).toString());
		}

		setSubTextText(order);
		this.validate();
	}

	/**
	 * Sets the subtotal text on the screen for user to see. 
	 * @param order
	 */
	
	public void setSubTextText(Order order) {
		subText.setText(order.getSubtotal().toString());
	}
	/**
	 * Sets the discounts amount text on screen, also updates total for order text based on the discounts applied
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
}
