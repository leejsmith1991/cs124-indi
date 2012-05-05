package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import uk.ac.aber.dcs.cs12420.aberpizza.data.*;

/**
 * This is the Main class within the program.<br>
 * <br>
 * Every action which is essential to the operation of the program happens in
 * this class.<br>
 * <br>
 * As most of the items selected react to a button press
 * <code>ActionListener</code> is implemented to handle the action of the user
 * pressing the buttons. This is the first class run by the
 * <code>main(String[] args)</code> method. Thus the till load is carried out
 * within the constructor of this class.<br>
 * <br>
 * <code>WindowListener</code> is also implemented so that if the user closed
 * the application via the exit button on the window not through, End Day button
 * or File -> Exit the application runs the save method within the Till class
 * saving the current days till
 * 
 * @see ActionListener
 * 
 * @author Lee Smith
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
	 * Constructs the main application. Till.<i>load()</i> method is run to load
	 * the XML file associated with the current day. MainFrame is created
	 * passing in this Class to handle ActionListening, Till to use the current
	 * Till state to populate JList of orders for today, and boolean set to true
	 * to tell the mainframe that today is the current day and to allow access
	 * to all the functionality of the till
	 * 
	 * @throws IOException
	 */

	public Manager() throws IOException {
		till = Till.load();
		mf = new MainFrame(this, till, true);
		mfPrev = null;
	}

	/**
	 * Overrides the method inherited from ActionListener, handles button
	 * presses from within majority of the program, running private methods
	 * within this class associated with the action that is required to happen.
	 */
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
				JOptionPane
						.showMessageDialog(null,
								"Error occured, Seek administator help, Missing Resource File");
			}
		} else if (action.equals("Add Side")) {
			try {
				fireItemWindow(ItemType.SIDE);
			} catch (FileNotFoundException e2) {
				JOptionPane
						.showMessageDialog(null,
								"Error occured, Seek administator help, Missing Resource File");
			}
		} else if (action.equals("Add Drink")) {
			try {
				fireItemWindow(ItemType.DRINK);
			} catch (FileNotFoundException e3) {
				JOptionPane
						.showMessageDialog(null,
								"Error occured, Seek administator help, Missing Resource File");
			}
		} else if (action.equals("Add to Order")) {
			addItemToOrder();
		} else if (action.equals("Pay")) {
			amt = new AmountTendered(this, customerOrder.getOrderTotal());
			amt.setLocationRelativeTo(no);
		} else if (action.equals("Pay Order")) {
			if (amt.getChange().signum() == -1) {
				JOptionPane.showMessageDialog(null,
						"Insufficent Fund recieved, Check input amount");
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
			}
		} else if (action.equals("Update Quantity")) {
			updateQuantity();
		} else if (action.equals("Remove Item")) {
			removeItemFromOrder();
		} else if (action.equals("Exit") || action.equals("End Day")) {
			if (mf.isFocused()) {
				try {
					till.save();
					mf.dispose();
				} catch (IOException e1) {

				}
			} else {
				mfPrev.dispose();
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
			for (int h = 0; h < till.getOrdersArray().size(); h++) {
				totalItemsPurchased = totalItemsPurchased
						+ till.getOrdersArray().get(h).getItems().size();
			}
			dayInfo = dayInfo + "Total number of Items Purchsed: "
					+ totalItemsPurchased + "\n";
			dayInfo = dayInfo + "Total money taken for day: £"
					+ till.getTotalForDay().toString();

			JOptionPane.showMessageDialog(null, dayInfo);

		} else if (action.equals("About")) {
			JOptionPane
					.showMessageDialog(
							null,
							"Aber Pizza v1.0 \nCreated by Lee Smith (ljs5)\n\nAberystwyth Univeristy 2011/14 \nDepartment of Computer Science");
		}
	}

	/**
	 * Displays the NewOrder class frame. Allowing the user to create a new
	 * order, a New order is also created.
	 */
	private void createNewOrder() {
		no = new NewOrder(this);
		customerOrder = new Order();
	}

	/**
	 * This method handles when the user presses and whichever item is pressed.
	 * To add a new Pizza to the till an ItemPizza is create and similar for
	 * Drinks and Sides.
	 * 
	 * @see ItemFrame
	 * 
	 * @param type
	 * @throws FileNotFoundException
	 */
	private void fireItemWindow(ItemType type) throws FileNotFoundException {
		if (type == ItemType.PIZZA) {
			itemFrame = (ItemFrame) new ItemPizza(this);
		} else if (type == ItemType.SIDE) {
			itemFrame = (ItemFrame) new ItemSide(this);
		} else if (type == ItemType.DRINK) {
			itemFrame = (ItemFrame) new ItemDrink(this);
		}
		itemFrame.setLocationRelativeTo(mf);
	}

	/**
	 * When the user attempts to change the quantity of an item within the
	 * NewOrder class frame, this method handles that operation, displaying a
	 * JOptionPane to allow the user to input the new quantity, then changing
	 * the appropriate data within the customersOrder, finally running the
	 * updateTable method, passing in the updated customerOrder, in the NewOrder
	 * class to allow the changes that have been made to the quantity to be
	 * displayed to the user. <br>
	 * <br>
	 * It also checks the input that the user has made to ensure that an
	 * incorrect character, such as a letter, is entered, and displays a message
	 * informing the user of the mistake
	 * 
	 * @see Order#updateItemQuantity(Item, int)
	 * @see NewOrder#updateTable(Order)
	 */
	private void updateQuantity() {
		int newQuant = 0;
		try {
			String newQuantInput = JOptionPane.showInputDialog(null,
					"Enter new Quantity", JOptionPane.OK_CANCEL_OPTION);
			newQuant = Integer.parseInt(newQuantInput);
			if (newQuant == 0) {
				removeItemFromOrder();
			} else {
				customerOrder.updateItemQuantity(customerOrder.getItemAt(no
						.getSelectedIndex()), newQuant);
				customerOrder.updateSubTotal();
				no.updateTable(customerOrder);
			}
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null,
					"Error in entry, Only whole number are allowed");
		}
	}

	/**
	 * This method is run when the user elects to remove an item from the order.
	 * Similarly to the updateQuantity method the method initially updates the
	 * customerOrder and then uses this to update the Table within the NewOrder
	 * class
	 * 
	 * @see Order#removeItem(int)
	 */
	private void removeItemFromOrder() {
		customerOrder.removeItem(no.getSelectedIndex());
		customerOrder.updateSubTotal();
		no.updateTable(customerOrder);
	}

	/**
	 * This method is run when the user presses the "Add to Order" button on the
	 * ItemFrame, retrieves the information that has been entered from the
	 * ItemFrame and constructs the appropriate class from the data package.
	 * Then adding a new OrderItem to the order based on which type of item
	 * wants to be added.
	 * 
	 * @see Order#addItem(Item, int)
	 */
	private void addItemToOrder() {
		i = null;
		if (itemFrame.getItemType() == ItemType.PIZZA) {
			i = new Pizza(itemFrame.getSelectedItem(),
					itemFrame.getItemPrice(), itemFrame.getItemSize(),
					itemFrame.getItemDesc());
		} else if (itemFrame.getItemType() == ItemType.SIDE) {
			i = new Side(itemFrame.getSelectedItem(), itemFrame.getItemPrice(),
					"", itemFrame.getItemDesc());
		} else {
			i = new Drink(itemFrame.getSelectedItem(),
					itemFrame.getItemPrice(), "", itemFrame.getItemDesc());
		}
		try {
			if (itemFrame.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(null,
						"Unable to Complete, No Item Selected");
			} else {
				itemFrame.setQuantity();
				customerOrder.addItem((Item) i, itemFrame.getQuantity());
				no.updateTable(customerOrder);
				calculateDiscount();
				itemFrame.dispose();
			}
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "Negative Quantity Entered");
		}

	}

	/**
	 * This method is run after an item has been added to the order, uses the
	 * <code>getDiscount()</code> method from the Order class and then updating
	 * the NewOrder information based on the data that has been changed within
	 * the Order
	 * 
	 * @see Order#getDiscount()
	 * @see NewOrder#setDiscountText(Order)
	 */

	private void calculateDiscount() {
		customerOrder.setOrderTotal(customerOrder.getDiscount());
		no.setDiscountText(customerOrder);
	}

	/**
	 * This method is run when the user elects to enter a new Item into the
	 * tills system. Creating a AddNewItemWindow class and using which ItemType
	 * has been selected to generate the correct window
	 * 
	 * @see AddNewItemWindow
	 * @see ItemType
	 * 
	 * @param type
	 */

	private void createNewItem(ItemType type) {
		if (type == ItemType.PIZZA) {
			new AddNewItemWindow(ItemType.PIZZA);
		} else if (type == ItemType.SIDE) {
			new AddNewItemWindow(ItemType.SIDE);
		} else {
			new AddNewItemWindow(ItemType.DRINK);
		}
	}

	/**
	 * Invoked when the use presses the "Pay" button on the NewOrder class, adds
	 * the Customers order to the till and closes the window
	 * 
	 * @see Till#addOrder(Order)
	 */
	private void addOrderToTill() {
		no.setCustomerName();
		customerOrder.setCustomerName(no.getCustomerName());
		JOptionPane.showMessageDialog(null, customerOrder.getReceipt());
		till.addOrder(customerOrder);
	}

	/**
	 * Invoked when the user selects File -> Load Previous Day. Uses a
	 * JFileChooser to get the file/day which the user wishes to load. If the
	 * selected day is not the same as the current day then a new MainFrame is
	 * created, except the boolean representing the day is set to false,
	 * disabling the <code>"Create New Order"</code> button therefore not
	 * allowing the user to enter a new order into the till. Also prevents the
	 * file from saving over the file due to the XML Serialization re-writing
	 * the data. The user also cannot load the till if the data specifies that
	 * it is the same till as todays.
	 * 
	 * @see Till#loadPrevious(String)
	 * @see JFileChooser
	 * 
	 * @throws IOException
	 */
	private void loadPreviousDay() throws IOException {
		JFileChooser fc = new JFileChooser();
		File f = new File("./TillSaves/");
		fc.setCurrentDirectory(f);
		fc.setApproveButtonText("Load");
		int fcReturnVal = fc.showOpenDialog(null);

		if (fcReturnVal == JFileChooser.APPROVE_OPTION) {
			String oldPathName = fc.getSelectedFile().getName();
			if (!oldPathName.equals(till.getToday() + ".xml")) {
				Till till = Till.loadPrevious(oldPathName);
				mfPrev = new MainFrame(this, till, false);
			} else {
				JOptionPane.showMessageDialog(null,
						"Todays Till is already open");
			}
		}
	}

	/**
	 * Overridden from the <code>WindowListener</code> interface so if the user
	 * closes the window from the " X " button on the window the application
	 * automatically saves itself. A check is also made to check the focus of
	 * window, to prevent the application overwriting the XML File if the
	 * current window is not todays version of the till
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		if (e.getSource() == mfPrev) {
			mfPrev.dispose();
		} else if (e.getSource().equals(mf)) {
			try {
				till.save();
				mf.dispose();
			} catch (IOException e1) {

			}

		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		//
	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {

	}

	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}
}
