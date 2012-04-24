package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;

import uk.ac.aber.dcs.cs12420.aberpizza.data.Drink;
import uk.ac.aber.dcs.cs12420.aberpizza.data.Item;
import uk.ac.aber.dcs.cs12420.aberpizza.data.ItemType;
import uk.ac.aber.dcs.cs12420.aberpizza.data.Order;
import uk.ac.aber.dcs.cs12420.aberpizza.data.OrderItem;
import uk.ac.aber.dcs.cs12420.aberpizza.data.Pizza;
import uk.ac.aber.dcs.cs12420.aberpizza.data.Side;
import uk.ac.aber.dcs.cs12420.aberpizza.data.Till;

public class Manager implements ActionListener {
	private Till till;

	private AddNewItemWindow addNewItem;
	private NewOrder no;
	private ItemFrame itemFrame;
	private String xmlFileName;

	private Order customerOrder;

	public Manager() throws IOException {
		MainFrame mf = new MainFrame(this);
		
		till = new Till();
		xmlFileName = till.getXMLFileName();
				
		File f = new File("cs124-indi/TillSaves" + xmlFileName + ".xml");
		System.out.println(f.exists());
		
		if (!f.exists()){
			till.save();
		}
		
		till = Till.load();
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
		} else if (action.equals("Pay Order")){
			addOrderToTill();
		} else if (action.equals("Save State")){
			try {
				till.save();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
		} else if (type == ItemType.DRINK){
			itemFrame = (ItemFrame) new ItemDrink(this);
		}
	}

	private void addItemToOrder() {
		Item i = null;
		if (itemFrame.getItemType() == ItemType.PIZZA){
			i = new Pizza(itemFrame.getSelectedItem(), new BigDecimal(itemFrame.getItemPrice()), itemFrame.getItemSize(), itemFrame.getItemDesc());
		} else if (itemFrame.getItemType() == ItemType.SIDE){
			i = new Side(itemFrame.getSelectedItem(), new BigDecimal(itemFrame.getItemPrice()), itemFrame.getItemSize(), itemFrame.getItemDesc());
		} else {
			i = new Drink(itemFrame.getSelectedItem(), new BigDecimal(itemFrame.getItemPrice()), itemFrame.getItemSize(), itemFrame.getItemDesc());
		}
		customerOrder.addItem(i, itemFrame.getQuantity());
		no.addItemToTable(i.getName(), itemFrame.getQuantity(), itemFrame.getSubTotal());
		itemFrame.dispose();
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
	
	private void addOrderToTill(){
		till.addOrder(customerOrder);
	}
}
