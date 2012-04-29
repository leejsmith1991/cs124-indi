package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import uk.ac.aber.dcs.cs12420.aberpizza.data.*;

/*
 * THINGS TO ASK NEIL
 * 
 * 1. DATE - SAVING RELATIVE TO XML SAVE NOT ORDER TIME
 * 2. LAYOUTS
 * 3. EXCEPTIONS
 * 
 */

public class Manager implements ActionListener, WindowListener {
	private Till till;
	private NewOrder no;
	private AmountTendered amt;
	private ItemFrame itemFrame;
	private MainFrame mf, mfPrev;
	private Item i;
	private Order customerOrder;

	/**
	 * 
	 * @throws IOException
	 */

	public Manager() throws IOException {
		till = Till.load();
		mf = new MainFrame(this, till, true);
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
		} else if (action.equals("Exit") || action.equals("End Day")) {
			try {
				till.save();
				mf.dispose();
			} catch (IOException e1) {

			}

		} else if (action.equals("Load Previous Day")) {
			try {
				loadPreviousDay();
			} catch (IOException e1) {

			}
		} else if (action.equals("View Sales History")) {
			String dayInfo;
			dayInfo = "Total Number of Orders: " + till.getOrdersArray().size()
					+ "\n";
			int totalItemsPurchased = 0;
			BigDecimal priceTotal = new BigDecimal("0.00");
			for (int h = 0; h < till.getOrdersArray().size(); h++) {
				totalItemsPurchased = totalItemsPurchased
						+ till.getOrdersArray().get(h).getItems().size();
			}
			dayInfo = dayInfo + "Total number of Items Purchsed: "
					+ totalItemsPurchased + "\n";
			dayInfo = dayInfo + "Total money taken for day: £"
					+ till.getTotalForDay().toString();

			JOptionPane.showMessageDialog(null, dayInfo);

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
		customerOrder.addItem((Item) i, itemFrame.getQuantity());
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
			new AddNewItemWindow(ItemType.PIZZA);
		} else if (type == ItemType.SIDE) {
			new AddNewItemWindow(ItemType.SIDE);
		} else {
			new AddNewItemWindow(ItemType.DRINK);
		}
	}

	private void addOrderToTill() {
		no.setCustomerName();
		customerOrder.setCustomerName(no.getCustomerName());
		JOptionPane.showMessageDialog(null, customerOrder.getReceipt());
		till.addOrder(customerOrder);
	}

	private void loadPreviousDay() throws IOException {
		JFileChooser fc = new JFileChooser();
		File f = new File("./TillSaves/");
		fc.setCurrentDirectory(f);
		int fcReturnVal = fc.showOpenDialog(null);

		if (fcReturnVal == JFileChooser.APPROVE_OPTION) {
			String oldPathName = fc.getSelectedFile().getName();
			Till till = Till.loadPrevious(oldPathName);
			mfPrev = new MainFrame(this, till, false);
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowClosing(WindowEvent e) {
		if (e.getSource() == mfPrev){
			System.out.println("A");
			mfPrev.dispose();
		} else if (e.getSource().equals(mf)){
			System.out.println("B");
			try {
				till.save();
				mf.dispose();
			} catch (IOException e1) {

			}
			
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}
}
