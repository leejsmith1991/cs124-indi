package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.math.BigDecimal;
//import java.math.BigDecimal;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;

import uk.ac.aber.dcs.cs12420.aberpizza.data.*;

public class ItemDrink extends ItemFrame implements KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4909990140586413688L;

	private Manager manager;

	private JList drinkList;
	private ArrayList<String> dName, dDesc;
	private ArrayList<BigDecimal> dPrice;
	private DefaultListModel ml;

	private JPanel drinkListPane, priceListPane, quantityPane, submitPane;
	private JTextField quantText;
	private JTextArea descriptionText;
	private JLabel itemPriceLabel;

	private String selectedItem, itemSize, itemDesc;

	BigDecimal itemPrice;
	private int quantity;

	public ItemDrink(Manager manager) throws FileNotFoundException {
		this.manager = manager;
		this.setTitle("Add Drink to order");
		this.setLayout(null);
		dName = new ArrayList<String>();
		dPrice = new ArrayList<BigDecimal>();
		dDesc = new ArrayList<String>();
		getFromFile();

		drinkListPane = getDrinkPane();
		drinkListPane.setBounds(0, 0, drinkListPane.getWidth(),
				drinkListPane.getHeight());

		priceListPane = getPricePane();
		priceListPane.setBounds(0, drinkListPane.getHeight(),
				priceListPane.getWidth(), priceListPane.getHeight());

		quantityPane = getQuantityPane();
		quantityPane.setBounds(0, drinkListPane.getHeight() + 30,
				quantityPane.getWidth(), quantityPane.getHeight());

		submitPane = getSubmitPane();
		submitPane.setBounds(0, drinkListPane.getHeight() + 80,
				submitPane.getWidth(), submitPane.getHeight());

		this.add(drinkListPane);
		this.add(priceListPane);
		this.add(quantityPane);
		this.add(submitPane);
		int windowWidth = drinkListPane.getWidth();
		int windowHeight = drinkListPane.getHeight()
				+ priceListPane.getHeight() + quantityPane.getHeight()
				+ submitPane.getHeight() + 30;
		this.setSize(new Dimension(windowWidth, windowHeight));
		this.setResizable(false);
		this.setVisible(true);
	}

	private JPanel getDrinkPane() {
		JPanel thisPane = new JPanel(null);

		JLabel label = new JLabel("Select Drink");
		label.setBounds(5, 5, 340, 25);
		thisPane.add(label);

		ml = new DefaultListModel();
		for (int i = 0; i < dName.size(); i++) {
			ml.addElement(dName.get(i));
		}

		drinkList = new JList(ml);
		drinkList.setBounds(30, label.getHeight() + 10, 300, dName.size() * 21);

		DrinkSelector drinkSelect = new DrinkSelector();
		drinkList.addListSelectionListener(drinkSelect);
		thisPane.add(drinkList);
		
		descriptionText = new JTextArea("");
		descriptionText.setWrapStyleWord(true);
		descriptionText.setLineWrap(true);
		descriptionText.setBounds(230, label.getHeight() + 10, 200, 150);
		descriptionText.setEnabled(false);
		thisPane.add(descriptionText);
		
		thisPane.setSize(label.getWidth() + 10, drinkList.getHeight() + 30);
		return thisPane;
	}

	private JPanel getPricePane() {
		JPanel thisPane = new JPanel(null);

		JLabel label = new JLabel("Select Size");
		label.setBounds(5, 5, 340, 25);
		thisPane.add(label);

		itemPriceLabel = new JLabel();
		itemPriceLabel.setBounds(110, 5, 50, 25);
		thisPane.add(itemPriceLabel);

		thisPane.setSize(450, 30);
		return thisPane;
	}

	private void setDrinkPrices(int selected) {
		itemPriceLabel.setText(dPrice.get(selected).toString());
		itemPrice = dPrice.get(selected);
		this.validate();
	}

	private JPanel getQuantityPane() {
		JPanel thisPane = new JPanel(null);
		JLabel quantLabel = new JLabel("Enter Quantity :", SwingConstants.RIGHT);
		quantLabel.setBounds(5, 17, 89, 15);

		quantText = new JTextField("");
		quantText.addKeyListener(this);
		quantText.setBounds(100, 10, 200, 30);

		thisPane.add(quantLabel);
		thisPane.add(quantText);
		thisPane.setSize(360, 50);
		return thisPane;
	}

	public JPanel getSubmitPane() {
		JPanel thisPane = new JPanel(null);

		JButton submit = new JButton("Add to Order");
		submit.setBounds(100, 10, 150, 30);
		thisPane.add(submit);
		submit.addActionListener(manager);
		thisPane.setSize(360, 50);

		return thisPane;
	}

	public void getFromFile() throws FileNotFoundException {
		Scanner sc = new Scanner(new BufferedReader(
				new FileReader("drinks.txt")));

		while (sc.hasNextLine()) {
			dName.add(sc.nextLine());
			dPrice.add(new BigDecimal(sc.nextLine()));
			dDesc.add(sc.nextLine());
		}
	}

	public Item getOrderItem() {
		Item newItem = new Drink(selectedItem, itemPrice, itemSize, itemDesc);
		return newItem;
	}

	public String getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(String selectedItem) {
		this.selectedItem = selectedItem;
	}

	public String getItemSize() {
		return itemSize;
	}

	public void setItemSize(String itemSize) {
		this.itemSize = itemSize;
	}

	public BigDecimal getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public ItemType getItemType() {
		return ItemType.DRINK;
	}

	@Override
	public void setQuantity() {
		try {
			Integer.parseInt(quantText.getText());
		} catch (NumberFormatException nfe) {
			// TODO implement error handle
			nfe.printStackTrace();
		}
	}

	public int getQuantity() {
		return quantity;
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		try {
			quantity = Integer.parseInt(quantText.getText());
			System.out.println(quantity);
		} catch (NumberFormatException nfe) {

		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

	/**
	 * Private class to enable separate selection of items in the Pizzas List
	 * from Price List
	 * 
	 * @author Lee Smith
	 * @see javax.swing.event.ListSelectionListener
	 */

	private class DrinkSelector implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			for (int a = 0; a < dName.size(); a++) {
				if (dName.get(a).equals(drinkList.getSelectedValue())) {
					selectedItem = dName.get(a);
					setDrinkPrices(a);
					descriptionText.setText(itemDesc);
					itemDesc = dDesc.get(a);
					break;
				}
			}
		}
	}
}