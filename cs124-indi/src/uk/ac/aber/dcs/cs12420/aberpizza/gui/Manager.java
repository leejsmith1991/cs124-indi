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
	Till till;

	private AddNewItemWindow addNewItem;
	private NewOrder no;
	private AddItemToOrder addItem;
	
	private Order newOrder;

	public Manager() {
		MainFrame mf = new MainFrame(this);
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
		} else if (action.equals("Add to Order")){
			try {
				addItemToOrder();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} 
	}

	public void createNewOrder(){
		newOrder = new Order();
	}

	public void addItemToOrder() throws FileNotFoundException{
		addItem = new AddItemToOrder(this);
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

}
