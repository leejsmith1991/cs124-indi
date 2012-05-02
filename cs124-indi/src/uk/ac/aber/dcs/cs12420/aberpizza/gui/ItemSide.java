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

public class ItemSide extends ItemFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4448070404095939991L;

	private Manager manager;

	private JList sideList;
	private ItemSelector sideSelect = new ItemSelector();
	private ArrayList<String> sName, sDesc;
	private ArrayList<BigDecimal> sPrice;
	private DefaultListModel ml;

	private JPanel sideListPane, priceListPane, quantityPane, submitPane;
	private JTextField quantText;
	private JTextArea descriptionText;
	private JLabel itemPriceLabel;

	private String selectedItem, itemSize, itemDesc;

	private BigDecimal itemPrice;
	private int quantity;

	private Font f = new Font("Arial", Font.PLAIN, 12);

	public ItemSide(Manager manager) throws FileNotFoundException {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {

		}

		this.manager = manager;
		this.setTitle("Add Side to order");

		sName = new ArrayList<String>();
		sPrice = new ArrayList<BigDecimal>();
		sDesc = new ArrayList<String>();
		getFromFile();

		this.getContentPane().setLayout(new BorderLayout());
		JPanel topPanel = new JPanel(new BorderLayout());
		sideListPane = getItemPane();
		priceListPane = getPricePane();
		topPanel.add(sideListPane, BorderLayout.NORTH);
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

		int windowWidth = sideListPane.getWidth();
		int windowHeight = sideListPane.getHeight() + priceListPane.getHeight()
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
		for (int i = 0; i < sName.size(); i++) {
			ml.addElement(sName.get(i));
		}

		sideList = new JList(ml);
		JScrollPane scroll = new JScrollPane(sideList);
		scroll.setPreferredSize(new Dimension(300, 100));
		sideList.addListSelectionListener(sideSelect);
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
		JPanel thisPane = new JPanel();

		JLabel label = new JLabel("Item Price : £        ", SwingConstants.RIGHT);
		label.setPreferredSize(new Dimension(315, 25));
		thisPane.add(label);

		itemPriceLabel = new JLabel();
		itemPriceLabel.setPreferredSize(new Dimension(315, 25));
		thisPane.add(itemPriceLabel);
		thisPane.setSize(650, 30);
		return thisPane;
	}

	protected void setItemPrices(int selected) {
		itemPriceLabel.setText(sPrice.get(selected).toString());
		itemPrice = sPrice.get(selected);
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

	public ItemType getItemType() {
		return ItemType.SIDE;
	}

	public void setQuantity() {
		try {
			quantity = Integer.parseInt(quantText.getText());
			if (quantity == 0) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException nfe) {
			quantText.setText(JOptionPane.showInputDialog(null,
					"Invalid Quantity Entry: Enter valid Entry"));
			setQuantity();
		}
	}

	public int getQuantity() {
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