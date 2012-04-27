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
	private Manager manager;

	private JPanel customerNamePane, listPane, buttonPane, subDiscPane,
			payCancelPane;
	private JTextField customerText;

	private JList orderList;
	private DefaultListModel tableList = new DefaultListModel();

	private BigDecimal totalForOrder = new BigDecimal("0");
	private JLabel subText;

	private int selectedIndex = 0;
	private String customerName;

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

	public int getSelectedIndex() {
		return selectedIndex;
	}

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

	private JPanel getSubDiscPane() {
		JPanel thisPane = new JPanel(null);

		JLabel subLabel, discLabel, discText;

		subLabel = new JLabel("Subtotal : £", SwingConstants.RIGHT);
		subLabel.setBounds(150, 5, 250, 25);
		thisPane.add(subLabel);

		subText = new JLabel("Subtotal for Order // to-do");
		subText.setBounds(420, 5, 250, 25);
		thisPane.add(subText);

		discLabel = new JLabel("Amount of Discount : £", SwingConstants.RIGHT);
		discLabel.setBounds(150, 50, 250, 25);
		thisPane.add(discLabel);

		discText = new JLabel("Discount // to-do");
		discText.setBounds(420, 50, 250, 25);
		thisPane.add(discText);
		
		JButton calcDisc = new JButton("Calculate Discount");
		calcDisc.setBounds(700, 25, 150, 35);
		calcDisc.addActionListener(manager);
		thisPane.add(calcDisc);
		
		thisPane.setSize(1024, 150);
		return thisPane;
	}

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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName() {
		if (customerText.getText().equals("")) {
			// throw new invalid argument exception
		} else {
			customerName = customerText.getText();
		}
	}
	
	public void updateTable(Order order) {
		tableList.clear();
		for (int i = 0; i < order.getItems().size(); i++){
			tableList.addElement(order.getItems().get(i).toString());
		}
		
		setSubTextText(order);
		this.validate();
	}

	public void setSubTextText(Order order){
		ArrayList<OrderItem> orderItems = order.getItems();
		BigDecimal total = new BigDecimal("0");
		for (int i = 0; i < orderItems.size(); i++){
			total = total.add(orderItems.get(i).getOrderItemTotal());
		}
		
		subText.setText(total.toString());
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
