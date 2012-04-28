package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;

import javax.swing.JOptionPane;

import uk.ac.aber.dcs.cs12420.aberpizza.data.*;

public class Manager implements ActionListener, MouseListener {
	private Till till;

	private AddNewItemWindow addNewItem;
	private NewOrder no;
	private AmountTendered amt;
	private ItemFrame itemFrame;
	private String xmlFileName;
	private MainFrame mf;
	private Item i;
	private Order customerOrder;

	public Manager() throws IOException {
		till = Till.load();
		mf = new MainFrame(this, till.getOrdersArray());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();

		// Manages the MainFrame Buttons
		if (action.equals("Create New Order")) {
			createNewOrder();
			// Manages Menu Button for creating a new Item
		} else if (action.equals("Add new Pizza")) {
			createNewItem(ItemType.PIZZA);
		} else if (action.equals("Add new Side")) {
			createNewItem(ItemType.SIDE);
		} else if (action.equals("Add new Drink")) {
			createNewItem(ItemType.DRINK);

			// Manages creating a new OrderItem
		} else if (action.equals("Add Pizza")) {
			try {
				fireItemWindow(ItemType.PIZZA);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (action.equals("Add Side")) {
			try {
				fireItemWindow(ItemType.SIDE);
			} catch (FileNotFoundException e2) {

			}
		} else if (action.equals("Add Drink")) {
			try {
				fireItemWindow(ItemType.DRINK);
			} catch (FileNotFoundException e3) {

			}
		} else if (action.equals("Add to Order")) {
			addItemToOrder();
		} else if (action.equals("Pay")) {
			amt = new AmountTendered(this, customerOrder.getOrderTotal());
		} else if (action.equals("Pay Order")) {
			if (amt.getChange().signum() == -1) {
				// throw new massive exception
			} else {
				amt.showChangeAmount(amt.getChange());
				addOrderToTill();
			}
		} else if (action.equals("Complete Order")) {
			amt.dispose();
			amt.getChangeFrame().dispose();
			no.dispose();
			mf.updateArrayList(till.getOrdersArray());
		} else if (action.equals("Save State")) {
			try {
				till.save();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (action.equals("Update Quantity")) {
			updateQuantity();
		} else if (action.equals("Remove Item")) {
			removeItemFromOrder();
		} else if (action.equals("Calculate Discount")) {
			calculateDiscount();
		}
	}

	private void createNewOrder() {
		no = new NewOrder(this);
		customerOrder = new Order();
	}

	private void fireItemWindow(ItemType type) throws FileNotFoundException {
		if (type == ItemType.PIZZA) {
			itemFrame = (ItemFrame) new ItemPizza(this);
		} else if (type == ItemType.SIDE) {
			itemFrame = (ItemFrame) new ItemSide(this);
		} else if (type == ItemType.DRINK) {
			itemFrame = (ItemFrame) new ItemDrink(this);
		}
	}

	private void updateQuantity() {
		int newQuant = 0;
		try {
			String newQuantInput = JOptionPane.showInputDialog(null,
					"Enter new Quantity", JOptionPane.OK_CANCEL_OPTION);
			newQuant = Integer.parseInt(newQuantInput);
			if (newQuant == 0) {
				removeItemFromOrder();
			} else {
				customerOrder.updateItemQuantity(
						customerOrder.getItemAt(no.getSelectedIndex()),
						newQuant);
				customerOrder.updateSubTotal();
				no.updateTable(customerOrder);
			}
		} catch (NumberFormatException nfe) {

		}

	}

	private void removeItemFromOrder() {
		customerOrder.removeItem(no.getSelectedIndex());
		customerOrder.updateSubTotal();
		no.updateTable(customerOrder);
	}

	private void addItemToOrder() {
		i = null;
		if (itemFrame.getItemType() == ItemType.PIZZA) {
			i = new Pizza(itemFrame.getSelectedItem(),
					itemFrame.getItemPrice(), itemFrame.getItemSize(),
					itemFrame.getItemDesc());
		} else if (itemFrame.getItemType() == ItemType.SIDE) {
			i = new Side(itemFrame.getSelectedItem(), itemFrame.getItemPrice(),
					itemFrame.getItemSize(), itemFrame.getItemDesc());
		} else {
			i = new Drink(itemFrame.getSelectedItem(),
					itemFrame.getItemPrice(), itemFrame.getItemSize(),
					itemFrame.getItemDesc());
		}
		customerOrder.addItem(i, itemFrame.getQuantity());
		no.updateTable(customerOrder);
		calculateDiscount();
		itemFrame.dispose();
		
	}

	private void calculateDiscount() {
		customerOrder.setOrderTotal(customerOrder.getDiscount());
		no.setDiscountText(customerOrder);
	}

	private void createNewItem(ItemType type) {
		if (type == ItemType.PIZZA) {
			addNewItem = new AddNewItemWindow(ItemType.PIZZA);
		} else if (type == ItemType.SIDE) {
			addNewItem = new AddNewItemWindow(ItemType.SIDE);
		} else {
			addNewItem = new AddNewItemWindow(ItemType.DRINK);
		}
	}

	private void addOrderToTill() {
		no.setCustomerName();
		customerOrder.setCustomerName(no.getCustomerName());
		till.addOrder(customerOrder);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
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

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
