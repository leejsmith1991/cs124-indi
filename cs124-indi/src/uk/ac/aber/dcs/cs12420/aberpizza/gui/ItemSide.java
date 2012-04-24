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

public class ItemSide extends ItemFrame implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4448070404095939991L;

	private Manager manager;

	private JList<String> sideList, priceList;
	private ArrayList<String> sName, sSmall, sMed, sLarge, sDesc;
	private DefaultListModel<String> ml;
	String[] prices = new String[3];

	private JPanel sideListPane, priceListPane, quantityPane, submitPane;
	private JTextField quantText;

	private String selectedPizza;
	private String sideSize;
	private String sidePrice;
	private String sideDesc;
	private int quantity;

	public ItemSide(Manager manager) throws FileNotFoundException {
		this.manager = manager;
		this.setTitle("Add Side to order");

		sName = new ArrayList<String>();
		sSmall = new ArrayList<String>();
		sMed = new ArrayList<String>();
		sLarge = new ArrayList<String>();
		sDesc = new ArrayList<String>();
		getFromFile();

		sideListPane = getSidePane();
		priceListPane = getPricePane();
		quantityPane = getQuantityPane();
		submitPane = getSubmitPane();
		
		this.setLayout(new GridLayout(4, 1));
		this.add(sideListPane);
		this.add(priceListPane);
		this.add(quantityPane);
		this.add(submitPane);
		
		this.setSize(new Dimension(500, 500));
		this.setVisible(true);
	}

	private JPanel getSidePane(){
		JPanel thisPane = new JPanel();
		
		ml = new DefaultListModel<String>();
		for (int i = 0; i < sName.size(); i++) {
			ml.addElement(sName.get(i));
		}

		sideList = new JList<String>(ml);
		SideSelector sideSelect = new SideSelector();
		sideList.addListSelectionListener(sideSelect);
		thisPane.add(sideList);
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
		prices[0] = sSmall.get(selected);
		prices[1] = sMed.get(selected);
		prices[2] = sLarge.get(selected);
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
				new FileReader("sides.txt")));

		while (sc.hasNextLine()) {
			sName.add(sc.nextLine());
			sSmall.add(sc.nextLine());
			sMed.add(sc.nextLine());
			sLarge.add(sc.nextLine());
			sDesc.add(sc.nextLine());
		}
	}

	public Item getOrderItem() {
		Item newItem = new Pizza(selectedPizza, new BigDecimal(sidePrice),
				sideSize, sideDesc);
		return newItem;
	}
	
	@Override
	public void setQuantity(){
		try {
		Integer.parseInt(quantText.getText());
		} catch (NumberFormatException nfe){
			//TODO implement error handle
			nfe.printStackTrace();
		}
	}
	
	public int getQuantity() {
		return quantity;
	}

	@Override
	public BigDecimal getSubTotal() {
		BigDecimal subtotal = new BigDecimal(sidePrice);
		subtotal.multiply(new BigDecimal(quantity));
		return subtotal;
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
	
	private class SideSelector implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			for (int a = 0; a < sName.size(); a++) {
				if (sName.get(a).equals(sideList.getSelectedValue())) {
					selectedPizza = sName.get(a);
					setPizzaPrices(a);
					sideDesc = sDesc.get(a);
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
				sideSize = "Small";
			} else if (selectedIndex == 1) {
				sideSize = "Medium";
			} else {
				sideSize = "Large";
			}
			sidePrice = prices[selectedIndex];
		}
	}

}