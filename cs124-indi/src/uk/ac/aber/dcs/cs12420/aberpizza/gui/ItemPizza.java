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

	private Manager manager;

	private JList pizzasList, priceList;
	private ArrayList<String> pName, pSmall, pMed, pLarge, pDesc;
	private DefaultListModel ml;
	String[] prices = new String[3];

	private JPanel pizzaPane, pricePane;
	private JTextField quantText;

	private String selectedPizza;
	private String pizzaSize;
	private String pizzaPrice;
	private String pizzaDesc;
	private int quantity;

	public ItemPizza(Manager manager) throws FileNotFoundException {
		this.manager = manager;
		this.setSize(new Dimension(500, 500));
		this.setVisible(true);

		pName = new ArrayList<String>();
		pSmall = new ArrayList<String>();
		pMed = new ArrayList<String>();
		pLarge = new ArrayList<String>();
		pDesc = new ArrayList<String>();
		getFromFile();

		pizzaPane = new JPanel();
		pizzaPane.add(getPizzaList());
		this.setLayout(new GridLayout(4, 1));
		this.add(pizzaPane);

		pricePane = new JPanel();

		pricePane.add(getPricePane());
		this.add(pricePane);

		this.add(getQuantityPane());
		this.add(getSubmitPane());
		
		this.setVisible(true);
	}

	private JList getPizzaList() {
		ml = new DefaultListModel();
		for (int i = 0; i < pName.size(); i++) {
			ml.addElement(pName.get(i));
		}

		pizzasList = new JList(ml);
		PizzaSelector pizzaSelect = new PizzaSelector();
		pizzasList.addListSelectionListener(pizzaSelect);

		return pizzasList;
	}

	private JList getPricePane() {
		priceList = new JList();
		PriceSelector priceSelect = new PriceSelector();
		priceList.addListSelectionListener(priceSelect);
		setPizzaPrices(0);
		return priceList;
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
				new FileReader("pizzas.txt")));

		while (sc.hasNextLine()) {
			pName.add(sc.nextLine());
			pSmall.add(sc.nextLine());
			pMed.add(sc.nextLine());
			pLarge.add(sc.nextLine());
			pDesc.add(sc.nextLine());
		}
	}

	public Item getOrderItem() {
		Item newItem = new Pizza(selectedPizza, new BigDecimal(pizzaPrice),
				pizzaSize, pizzaDesc);
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

	private class PizzaSelector implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			for (int a = 0; a < pName.size(); a++) {
				if (pName.get(a).equals(pizzasList.getSelectedValue())) {
					selectedPizza = pName.get(a);
					setPizzaPrices(a);
					pizzaDesc = pDesc.get(a);
					priceList.setSelectedIndex(0);
					break;
				}
			}
		}
	}

	private class PriceSelector implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			int selectedIndex = priceList.getSelectedIndex();
			if (selectedIndex == -1) {
				selectedIndex = 0;
			}
			if (selectedIndex == 0) {
				pizzaSize = "Small";
			} else if (selectedIndex == 1) {
				pizzaSize = "Medium";
			} else {
				pizzaSize = "Large";
			}
			pizzaPrice = prices[selectedIndex];
		}
	}

}