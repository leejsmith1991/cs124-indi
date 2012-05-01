package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.*;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;

import uk.ac.aber.dcs.cs12420.aberpizza.data.*;

public class ItemDrink extends ItemFrame{

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

	private BigDecimal itemPrice;
	private int quantity;
	
	private Font f = new Font("Arial", Font.PLAIN, 12);

	public ItemDrink(Manager manager) throws FileNotFoundException {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {

		}

		this.manager = manager;
		this.setTitle("Add Drink to order");

		dName = new ArrayList<String>();
		dPrice = new ArrayList<BigDecimal>();
		dDesc = new ArrayList<String>();
		getFromFile();

		this.getContentPane().setLayout(new BorderLayout());
		JPanel topPanel = new JPanel(new BorderLayout());
		drinkListPane = getItemPane();
		priceListPane = getPricePane();
		topPanel.add(drinkListPane, BorderLayout.NORTH);
		topPanel.add(priceListPane, BorderLayout.SOUTH);
		this.add(topPanel, BorderLayout.NORTH);

		JPanel bottomPanel = new JPanel();
		quantityPane = getQuantityPane();
		submitPane = getSubmitPane();
		bottomPanel.add(quantityPane, BorderLayout.NORTH);
		bottomPanel.add(submitPane, BorderLayout.SOUTH);
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
		bottomPanel.setPreferredSize(new Dimension(650, quantityPane
				.getHeight() + submitPane.getHeight() + 20));
		this.add(bottomPanel, BorderLayout.SOUTH);

		int windowWidth = drinkListPane.getWidth();
		int windowHeight = drinkListPane.getHeight() + priceListPane.getHeight()
				+ quantityPane.getHeight() + bottomPanel.getHeight() + 25;
		this.setSize(new Dimension(windowWidth, windowHeight));
		this.setResizable(false);
		this.setVisible(true);
	}

	protected JPanel getItemPane() {
		JPanel thisPane = new JPanel(new BorderLayout());

		JLabel label = new JLabel("Select Drink");
		label.setPreferredSize(new Dimension(340, 25));
		thisPane.add(label, BorderLayout.NORTH);

		ml = new DefaultListModel();
		for (int i = 0; i < dName.size(); i++) {
			ml.addElement(dName.get(i));
		}

		drinkList = new JList(ml);
		JScrollPane scroll = new JScrollPane(drinkList);
		scroll.setPreferredSize(new Dimension(300, 100));
		drinkList.addListSelectionListener(new ItemSelector());
		thisPane.add(scroll, BorderLayout.WEST);

		descriptionText = new JTextArea("");
		descriptionText.setFont(f);
		descriptionText.setWrapStyleWord(true);
		descriptionText.setLineWrap(true);
		descriptionText.setPreferredSize(new Dimension(300, 100));
		descriptionText.setEditable(false);

		thisPane.add(descriptionText, BorderLayout.EAST);
		thisPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
		thisPane.setSize(new Dimension(650, 200));
		return thisPane;
	}

	protected JPanel getPricePane() {
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

	protected void setItemPrices(int selected) {
		itemPriceLabel.setText(dPrice.get(selected).toString());
		itemPrice = dPrice.get(selected);
		this.validate();
	}

	protected JPanel getQuantityPane() {
		JPanel thisPane = new JPanel(new BorderLayout());
		JLabel quantLabel = new JLabel("Enter Quantity :", SwingConstants.RIGHT);
		quantLabel.setPreferredSize(new Dimension(200, 25));

		quantText = new JTextField("0");
		quantText.setPreferredSize(new Dimension(350, 20));
		thisPane.add(quantLabel, BorderLayout.WEST);
		thisPane.add(quantText, BorderLayout.EAST);
		thisPane.setSize(650, 30);

		return thisPane;
	}

	protected JPanel getSubmitPane() {
		JPanel thisPane = new JPanel();

		JButton submit = new JButton("Add to Order");
		submit.setFont(f);
		submit.setPreferredSize(new Dimension(150, 30));
		thisPane.add(submit);
		submit.addActionListener(manager);
		thisPane.setSize(650, 30);

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

	public ItemType getItemType() {
		return ItemType.DRINK;
	}

	public void setQuantity() {
		try {
			quantity = Integer.parseInt(quantText.getText());
			if (quantity == 0){
				throw new NumberFormatException();
			}
		} catch (NumberFormatException nfe) {
			quantText.setText(JOptionPane.showInputDialog(null, "Invalid Quantity Entry: Enter valid Quantity"));
		}
	}

	public int getQuantity() {
		setQuantity();
		return quantity;
	}

	/**
	 * Private class to enable separate selection of items in the Pizzas List
	 * from Price List
	 * 
	 * @author Lee Smith
	 * @see javax.swing.event.ListSelectionListener
	 */

	private class ItemSelector implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			for (int a = 0; a < dName.size(); a++) {
				if (dName.get(a).equals(drinkList.getSelectedValue())) {
					selectedItem = dName.get(a);
					setItemPrices(a);
					descriptionText.setText(itemDesc);
					itemDesc = dDesc.get(a);
					break;
				}
			}
		}
	}
}