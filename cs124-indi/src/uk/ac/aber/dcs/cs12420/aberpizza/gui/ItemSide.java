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

	private JList sideList;
	private ArrayList<String> sName, sDesc;
	private ArrayList<BigDecimal>  sPrice;
	private DefaultListModel ml;

	private JPanel sideListPane, priceListPane, quantityPane, submitPane;
	private JTextField quantText;
	private JTextArea descriptionText;
	private JLabel itemPriceLabel;

	private String selectedItem, itemSize, itemDesc;

	private BigDecimal itemPrice;
	private int quantity;
	
	public ItemSide(Manager manager) throws FileNotFoundException {
		this.manager = manager;
		this.setTitle("Add Side to order");
		this.setLayout(null);
		sName = new ArrayList<String>();
		sPrice = new ArrayList<BigDecimal>();
		sDesc = new ArrayList<String>();
		getFromFile();

		sideListPane = getItemPane();
		sideListPane.setBounds(0, 0, sideListPane.getWidth(),
				sideListPane.getHeight());

		priceListPane = getPricePane();
		priceListPane.setBounds(0, sideListPane.getHeight(),
				priceListPane.getWidth(), priceListPane.getHeight());

		quantityPane = getQuantityPane();
		quantityPane.setBounds(0, sideListPane.getHeight() + 30,
				quantityPane.getWidth(), quantityPane.getHeight());

		submitPane = getSubmitPane();
		submitPane.setBounds(0, sideListPane.getHeight() + 80,
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

	protected JPanel getItemPane() {
		JPanel thisPane = new JPanel(null);

		JLabel label = new JLabel("Select Drink");
		label.setBounds(5, 5, 340, 25);
		thisPane.add(label);

		ml = new DefaultListModel();
		for (int i = 0; i < sName.size(); i++) {
			ml.addElement(sName.get(i));
		}

		sideList = new JList(ml);
		sideList.setBounds(30, label.getHeight() + 10, 300, sName.size() * 21);

		SideSelector drinkSelect = new SideSelector();
		sideList.addListSelectionListener(drinkSelect);
		thisPane.add(sideList);
		
		descriptionText = new JTextArea("");
		descriptionText.setWrapStyleWord(true);
		descriptionText.setLineWrap(true);
		descriptionText.setBounds(230, label.getHeight() + 10, 200, 150);
		descriptionText.setEnabled(false);
		thisPane.add(descriptionText);
		thisPane.setSize(label.getWidth() + 10, sideList.getHeight() + 30);
		return thisPane;
	}

	protected JPanel getPricePane() {
		JPanel thisPane = new JPanel(null);

		JLabel label = new JLabel("Item Price:", SwingConstants.RIGHT);
		label.setBounds(5, 5, 100, 25);
		thisPane.add(label);

		itemPriceLabel = new JLabel("");
		itemPriceLabel.setBounds(110, 5, 50, 25);
		thisPane.add(itemPriceLabel);

		thisPane.setSize(450, 30);
		return thisPane;
	}


	protected void setItemPrices(int selected) {
		itemPriceLabel.setText(sPrice.get(selected).toString());
		itemPrice = sPrice.get(selected);
		this.validate();
	}

	protected JPanel getQuantityPane() {
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

	protected JPanel getSubmitPane() {
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
			sPrice.add(new BigDecimal(sc.nextLine()));
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

	public ItemType getItemType(){
		return ItemType.SIDE;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
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

	private class SideSelector implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			for (int a = 0; a < sName.size(); a++) {
				if (sName.get(a).equals(sideList.getSelectedValue())) {
					selectedItem = sName.get(a);
					setItemPrices(a);
					descriptionText.setText(itemDesc);
					itemDesc = sDesc.get(a);
					break;
				}
			}
		}
	}
}