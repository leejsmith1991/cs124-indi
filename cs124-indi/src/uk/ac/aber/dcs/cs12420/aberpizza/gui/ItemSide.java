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

	private String selectedItem, itemSize, itemPrice, itemDesc;
	private int quantity;
	
	public ItemSide(Manager manager) throws FileNotFoundException {
		this.manager = manager;
		this.setTitle("Add Side to order");
		this.setLayout(null);
		sName = new ArrayList<String>();
		sSmall = new ArrayList<String>();
		sMed = new ArrayList<String>();
		sLarge = new ArrayList<String>();
		sDesc = new ArrayList<String>();
		getFromFile();

		sideListPane = getDrinkPane();
		sideListPane.setBounds(0, 0, sideListPane.getWidth(),
				sideListPane.getHeight());

		priceListPane = getPricePane();
		priceListPane.setBounds(0, sideListPane.getHeight(),
				priceListPane.getWidth(), priceListPane.getHeight());

		quantityPane = getQuantityPane();
		quantityPane.setBounds(0, sideListPane.getHeight() + 90,
				quantityPane.getWidth(), quantityPane.getHeight());

		submitPane = getSubmitPane();
		submitPane.setBounds(0, sideListPane.getHeight() + 140,
				submitPane.getWidth(), submitPane.getHeight());

		this.add(sideListPane);
		this.add(priceListPane);
		this.add(quantityPane);
		this.add(submitPane);
		int windowWidth = sideListPane.getWidth();
		int windowHeight = sideListPane.getHeight()
				+ priceListPane.getHeight() + quantityPane.getHeight() + submitPane
				.getHeight() + 30;
		this.setSize(new Dimension(windowWidth, windowHeight));
		this.setResizable(false);
		this.setVisible(true);
	}

	private JPanel getDrinkPane() {
		JPanel thisPane = new JPanel(null);

		JLabel label = new JLabel("Select Drink");
		label.setBounds(5, 5, 340, 25);
		thisPane.add(label);

		ml = new DefaultListModel<String>();
		for (int i = 0; i < sName.size(); i++) {
			ml.addElement(sName.get(i));
		}

		sideList = new JList<String>(ml);
		sideList.setBounds(30, label.getHeight() + 10, 300, sName.size() * 21);

		SideSelector drinkSelect = new SideSelector();
		sideList.addListSelectionListener(drinkSelect);
		thisPane.add(sideList);
		thisPane.setSize(label.getWidth() + 10, sideList.getHeight() + 30);
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

		priceList = new JList<String>();
		PriceSelector priceSelect = new PriceSelector();
		priceList.addListSelectionListener(priceSelect);
		setPizzaPrices(0);
		priceList.setBounds(83, 30, 150, 60);
		thisPane.add(priceList);
		thisPane.setSize(450, 100);
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
		Scanner sc = new Scanner(
				new BufferedReader(new FileReader("sides.txt")));

		while (sc.hasNextLine()) {
			sName.add(sc.nextLine());
			sSmall.add(sc.nextLine());
			sMed.add(sc.nextLine());
			sLarge.add(sc.nextLine());
			sDesc.add(sc.nextLine());
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

	public String getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(String itemPrice) {
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
		return ItemType.SIDE;
	}
	
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
	public BigDecimal getSubTotal() {
		BigDecimal subtotal = new BigDecimal(itemPrice);
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
	 * Private class to enable separate selection of items in the Pizzas List
	 * from Price List
	 * 
	 * @author Lee Smith
	 * @see javax.swing.event.ListSelectionListener
	 */

	private class SideSelector implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			for (int a = 0; a < sName.size(); a++) {
				if (sName.get(a).equals(sideList.getSelectedValue())) {
					selectedItem = sName.get(a);
					setPizzaPrices(a);
					itemDesc = sDesc.get(a);
					priceList.setSelectedIndex(0);
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