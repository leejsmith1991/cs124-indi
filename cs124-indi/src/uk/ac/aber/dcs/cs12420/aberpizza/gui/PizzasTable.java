package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
//import java.math.BigDecimal;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;

public class PizzasTable extends JFrame implements ActionListener{

	private JList pizzasList, priceList;
	private ArrayList<String> pName, pSmall, pMed, pLarge, pDesc;
	private DefaultListModel ml;
	String[] prices = new String[3];

	private JPanel pizzaPane, pricePane;
	
	private String selectedPizza;
	private String pizzaPrice;
	private int quantity;

	public PizzasTable() throws FileNotFoundException {
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
		this.setLayout(new GridLayout(4,1));
		this.add(pizzaPane);
		
		pricePane = new JPanel();
		
		pricePane.add(getPricePane());
		this.add(pricePane);
		setPizzaPrices(0);
		this.add(getQuantityPane());
		this.add(getSubmitPane());
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
		return priceList;
	}

	private void setPizzaPrices(int selected){
		prices[0] = pSmall.get(selected);
		prices[1] = pMed.get(selected);
		prices[2] = pLarge.get(selected);

		priceList.setListData(prices);
		this.validate();
	}
	
	private JPanel getQuantityPane(){
		JPanel quantityPane = new JPanel(new GridLayout(1,2));
		JLabel quantLabel = new JLabel("Enter Quantity :");
		JTextField quantText = new JTextField("1");
		quantityPane.add(quantLabel);
		quantityPane.add(quantText);
		return quantityPane;
		
	}
	
	public JPanel getSubmitPane(){
		JPanel submitPane = new JPanel();
		
		JButton submit = new JButton("Add to Order");
		submitPane.add(submit);
		submit.addActionListener(this);
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Add to Order")){
			
		}	
	}
	
	private class PizzaSelector implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			for (int a = 0; a < pName.size(); a++) {
				if (pName.get(a).equals(pizzasList.getSelectedValue())) {
					selectedPizza = pName.get(a);
					setPizzaPrices(a);
					break;
				}
			}
		}
	}
	private class PriceSelector implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			pizzaPrice = prices[priceList.getSelectedIndex()];
		}
	}
	
	
}