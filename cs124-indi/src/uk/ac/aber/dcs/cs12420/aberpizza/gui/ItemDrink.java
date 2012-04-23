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

public class ItemDrink extends ItemFrame implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4909990140586413688L;

	private Manager manager;

	private JList<String> drinkList, priceList;
	private ArrayList<String> dName, dSmall, dMed, dLarge, dDesc;
	private DefaultListModel<?> ml;
	String[] prices = new String[3];

	private JPanel drinkListPane, priceListPane, quantityPane, submitPane;
	private JTextField quantText;

	private String selectedDrink;
	private String drinkSize;
	private String drinkPrice;
	private String drinkDesc;
	private int quantity;

	public ItemDrink(Manager manager) throws FileNotFoundException {
		this.manager = manager;
		this.setTitle("Add Drink to order");
		
		dName = new ArrayList<String>();
		dSmall = new ArrayList<String>();
		dMed = new ArrayList<String>();
		dLarge = new ArrayList<String>();
		dDesc = new ArrayList<String>();
		getFromFile();

		drinkListPane = getDrinkPane();
		priceListPane = getPricePane();
		quantityPane = getQuantityPane();
		submitPane = getSubmitPane();
		
		this.setLayout(new GridLayout(4, 1));
		this.add(drinkListPane);
		this.add(priceListPane);
		this.add(quantityPane);
		this.add(submitPane);
		
		this.setSize(new Dimension(500, 500));
		this.setVisible(true);
	}

	private JPanel getDrinkPane(){
		JPanel thisPane = new JPanel();
		
		DefaultListModel<String> ml = new DefaultListModel<String>();
		for (int i = 0; i < dName.size(); i++) {
			ml.addElement(dName.get(i));
		}

		drinkList = new JList<String>(ml);
		DrinkSelector drinkSelect = new DrinkSelector();
		drinkList.addListSelectionListener(drinkSelect);
		thisPane.add(drinkList);
		return thisPane;
	}
	
	private JPanel getPricePane() {
		JPanel thisPane = new JPanel();
		priceList = new JList<String>();
		PriceSelector priceSelect = new PriceSelector();
		priceList.addListSelectionListener(priceSelect);
		setPizzaPrices(0);
		thisPane.add(priceList);
		return thisPane;
	}

	private void setPizzaPrices(int selected) {
		prices[0] = dSmall.get(selected);
		prices[1] = dMed.get(selected);
		prices[2] = dLarge.get(selected);
		priceList.setSelectedIndex(0);
		priceList.setListData(prices);
		this.validate();
	}

	private JPanel getQuantityPane() {
		JPanel quantityPane = new JPanel(new GridLayout(1, 2));
		JLabel quantLabel = new JLabel("Enter Quantity :");
		quantText = new JTextField("1");
		quantText.addKeyListener(this);
		quantityPane.add(quantLabel);
		quantityPane.add(quantText);
		return quantityPane;
	}

	public JPanel getSubmitPane() {
		JPanel submitPane = new JPanel();

		JButton submit = new JButton("Add to Order");
		submitPane.add(submit);
		submit.addActionListener(manager);
		return submitPane;
	}

	public void getFromFile() throws FileNotFoundException {
		Scanner sc = new Scanner(new BufferedReader(
				new FileReader("drink.txt")));

		while (sc.hasNextLine()) {
			dName.add(sc.nextLine());
			dSmall.add(sc.nextLine());
			dMed.add(sc.nextLine());
			dLarge.add(sc.nextLine());
			dDesc.add(sc.nextLine());
		}
	}

	public Item getOrderItem() {
		Item newItem = new Drink(selectedDrink, new BigDecimal(drinkPrice),
				drinkSize, drinkDesc);
		return newItem;
	}
	
	public int getQuantity() {
		return quantity;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
		try {
			quantity = Integer.parseInt(quantText.getText());
		} catch (NumberFormatException nfe) {

		}
	}

	/**
	 * Private class to enable separate selection of items in the Pizzas List from Price List
	 * @author Lee Smith
	 * @see javax.swing.event.ListSelectionListener 
	 */
	
	private class DrinkSelector implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			for (int a = 0; a < dName.size(); a++) {
				if (dName.get(a).equals(drinkList.getSelectedValue())) {
					selectedDrink = dName.get(a);
					setPizzaPrices(a);
					drinkDesc = dDesc.get(a);
					priceList.setSelectedIndex(0);
					break;
				}
			}
		}
	}

	/**
	 * Private class to enable separate selection of items in the Price List from Pizzas List
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
				drinkSize = "Small";
			} else if (selectedIndex == 1) {
				drinkSize = "Medium";
			} else {
				drinkSize = "Large";
			}
			drinkPrice = prices[selectedIndex];
		}
	}

}