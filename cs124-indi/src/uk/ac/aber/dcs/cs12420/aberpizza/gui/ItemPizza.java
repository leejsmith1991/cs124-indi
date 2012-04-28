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

public class ItemPizza extends ItemFrame implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2180779048808264480L;

	private Manager manager;

	private JList pizzasList, priceList;
	private ArrayList<String> pName, pDesc;
	private ArrayList<BigDecimal> pSmall, pMed, pLarge;
	private DefaultListModel ml;
	BigDecimal[] prices = new BigDecimal[3];

	private JPanel pizzaListPane, priceListPane, quantityPane, submitPane;
	private JTextField quantText;
	
	private String selectedItem, itemSize, itemDesc;
	private BigDecimal itemPrice;
	private int quantity;
	
	public ItemPizza(Manager manager) throws FileNotFoundException {
		this.manager = manager;
		this.setTitle("Add Pizza to order");
		this.setLayout(null);
		pName = new ArrayList<String>();
		pSmall = new ArrayList<BigDecimal>();
		pMed = new ArrayList<BigDecimal>();
		pLarge = new ArrayList<BigDecimal>();
		pDesc = new ArrayList<String>();
		getFromFile();

		pizzaListPane = getPizzaPane();
		pizzaListPane.setBounds(0, 0, pizzaListPane.getWidth(),
				pizzaListPane.getHeight());

		priceListPane = getPricePane();
		priceListPane.setBounds(0, pizzaListPane.getHeight(),
				priceListPane.getWidth(), priceListPane.getHeight());

		quantityPane = getQuantityPane();
		quantityPane.setBounds(0, pizzaListPane.getHeight() + 90,
				quantityPane.getWidth(), quantityPane.getHeight());

		submitPane = getSubmitPane();
		submitPane.setBounds(0, pizzaListPane.getHeight() + 140,
				submitPane.getWidth(), submitPane.getHeight());

		this.add(pizzaListPane);
		this.add(priceListPane);
		this.add(quantityPane);
		this.add(submitPane);
		int windowWidth = pizzaListPane.getWidth();
		int windowHeight = pizzaListPane.getHeight()
				+ priceListPane.getHeight() + quantityPane.getHeight() + submitPane
				.getHeight() + 30;
		this.setSize(new Dimension(windowWidth, windowHeight));
		this.setResizable(false);
		this.setVisible(true);
	}

	private JPanel getPizzaPane() {
		JPanel thisPane = new JPanel(null);

		JLabel label = new JLabel("Select Pizza");
		label.setBounds(5, 5, 340, 25);
		thisPane.add(label);

		ml = new DefaultListModel();
		for (int i = 0; i < pName.size(); i++) {
			ml.addElement(pName.get(i));
		}

		pizzasList = new JList(ml);
		pizzasList
				.setBounds(30, label.getHeight() + 10, 300, pName.size() * 21);

		PizzaSelector pizzaSelect = new PizzaSelector();
		pizzasList.addListSelectionListener(pizzaSelect);

		thisPane.add(pizzasList);

		thisPane.setSize(label.getWidth() + pizzasList.getHeight() + 30,
				label.getHeight() + pizzasList.getHeight());
		return thisPane;
	}

	private JPanel getPricePane() {
		JPanel thisPane = new JPanel(null);

		JLabel label = new JLabel("Select Size");
		label.setBounds(5, 5, 340, 25);
		thisPane.add(label);

		JLabel smallLabel = new JLabel("Small:", SwingConstants.RIGHT);
		smallLabel.setBounds(30, 29, 50, 21);
		thisPane.add(smallLabel);

		JLabel medLabel = new JLabel("Medium:", SwingConstants.RIGHT);
		medLabel.setBounds(30, 47, 50, 21);
		thisPane.add(medLabel);

		JLabel largeLabel = new JLabel("Large:", SwingConstants.RIGHT);
		largeLabel.setBounds(30, 64, 50, 22);
		thisPane.add(largeLabel);

		priceList = new JList();
		priceList.setEnabled(false);
		PriceSelector priceSelect = new PriceSelector();
		priceList.addListSelectionListener(priceSelect);
		setPizzaPrices(0);
		priceList.setBounds(83, 30, 150, 60);
		thisPane.add(priceList);
		thisPane.setSize(450, 100);
		return thisPane;
	}

	private void setPizzaPrices(int selected) {
		prices[0] = pSmall.get(selected);
		prices[1] = pMed.get(selected);
		prices[2] = pLarge.get(selected);
		priceList.setSelectedIndex(0);
		priceList.setListData(prices);
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

	private JPanel getSubmitPane() {
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
				new FileReader("pizzas.txt")));

		while (sc.hasNextLine()) {
			pName.add(sc.nextLine());
			pSmall.add(new BigDecimal(sc.nextLine()));
			pMed.add(new BigDecimal(sc.nextLine()));
			pLarge.add(new BigDecimal(sc.nextLine()));
			pDesc.add(sc.nextLine());
		}
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
	
	public ItemType getItemType(){
		return ItemType.PIZZA;
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

	private class PizzaSelector implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			for (int a = 0; a < pName.size(); a++) {
				if (pName.get(a).equals(pizzasList.getSelectedValue())) {
					selectedItem = pName.get(a);
					setPizzaPrices(a);
					itemDesc = pDesc.get(a);
					priceList.setSelectedIndex(0);
					priceList.setEnabled(true);
					break;
				}
			}
		}
	}

	/**
	 * Private class to enable separate selection of items in the Price List
	 * from Pizzas List
	 * 
	 * @author Lee Smith
	 * @see javax.swing.event.ListSelectionListener
	 */

	private class PriceSelector implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			int selectedIndex = priceList.getSelectedIndex();
			if (selectedIndex == -1) {
				selectedIndex = 0;
			}
			if (selectedIndex == 0) {
				itemSize = "Small";
			} else if (selectedIndex == 1) {
				itemSize = "Medium";
			} else {
				itemSize = "Large";
			}
			itemPrice = prices[selectedIndex];
		}
	}

}