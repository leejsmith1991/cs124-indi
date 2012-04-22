package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import uk.ac.aber.dcs.cs12420.aberpizza.data.ItemType;
import uk.ac.aber.dcs.cs12420.aberpizza.data.Order;
import uk.ac.aber.dcs.cs12420.aberpizza.data.OrderItem;
import uk.ac.aber.dcs.cs12420.aberpizza.data.Till;

public class Manager implements ActionListener {
	private Till till;

	private AddNewItemWindow addNewItem;
	private NewOrder no;
	private ItemFrame itemFrame;

	private Order customerOrder;

	public Manager() {
		MainFrame mf = new MainFrame(this);
		till = new Till();
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
		} else if (action.equals("Pizzas")) {
			try {
				fireItemWindow(ItemType.PIZZA);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (action.equals("Sides")) {
			try {
				fireItemWindow(ItemType.SIDE);
			} catch (FileNotFoundException e2) {

			}
		} else if (action.equals("Drinks")) {
			try {
				fireItemWindow(ItemType.DRINK);
			} catch (FileNotFoundException e3) {

			}
		} else if (action.equals("Add to Order")) {
			addItemToOrder();
		} else if (action.equals("Pay Order")){
			
		} else if (action.equals("Save State")){
			try {
				till.save();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public void createNewOrder() {
		no = new NewOrder(this);
		customerOrder = new Order();
	}

	public void fireItemWindow(ItemType type) throws FileNotFoundException {
		if (type == ItemType.PIZZA) {
			itemFrame = (ItemFrame) new ItemPizza(this);
		} else if (type == ItemType.SIDE) {
			// TODO implement ItemSide
			// itemFrame = new ItemSide(this);
		} else {
			// TODO implement ItemDrink
			// itemFrame = new ItemDrink(this);
		}
	}

	private void addItemToOrder() {
		customerOrder.addItem(itemFrame.getOrderItem(), itemFrame.getQuantity());
		itemFrame.dispose();
	}

	public void createNewItem(ItemType type) {
		if (type == ItemType.PIZZA) {
			addNewItem = new AddNewItemWindow(ItemType.PIZZA);
		} else if (type == ItemType.SIDE) {
			addNewItem = new AddNewItemWindow(ItemType.SIDE);
		} else {
			addNewItem = new AddNewItemWindow(ItemType.DRINK);
		}
	}
	private void addOrderToTill(){
		
	}
}
