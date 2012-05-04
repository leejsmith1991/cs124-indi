package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.*;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;

import uk.ac.aber.dcs.cs12420.aberpizza.data.*;

public class ItemPizza extends ItemFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2180779048808264480L;

	private Manager manager;

	private JList pizzasList, priceList;
	private ArrayList<String> pName, pDesc;
	private ArrayList<BigDecimal> pSmall, pMed, pLarge;
	private DefaultListModel ml;
	private ItemSelector pizzaSelect = new ItemSelector();
	private PriceSelector priceSelect = new PriceSelector();
	private BigDecimal[] prices = new BigDecimal[3];

	private JPanel pizzaListPane, priceListPane, quantityPane, submitPane;
	private JTextField quantText;
	private JTextArea descriptionText;
	
	private String selectedItem, itemSize, itemDesc;
	private BigDecimal itemPrice;
	private int quantity;
	
	private Font f = new Font("Arial", Font.PLAIN, 12);
	
	public ItemPizza(Manager manager) throws FileNotFoundException {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e){
			
		}
		
		this.manager = manager;
		this.setTitle("Add Pizza to order");

		pName = new ArrayList<String>();
		pSmall = new ArrayList<BigDecimal>();
		pMed = new ArrayList<BigDecimal>();
		pLarge = new ArrayList<BigDecimal>();
		pDesc = new ArrayList<String>();
		getFromFile();

		this.getContentPane().setLayout(new BorderLayout());
		JPanel topPanel = new JPanel(new BorderLayout());
		pizzaListPane = getItemPane();
		priceListPane = getPricePane();
		topPanel.add(pizzaListPane, BorderLayout.NORTH);
		topPanel.add(priceListPane, BorderLayout.SOUTH);
		topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
		this.add(topPanel, BorderLayout.NORTH);
		
		quantityPane = getQuantityPane();
		submitPane = getSubmitPane();
		this.add(quantityPane, BorderLayout.CENTER);
		this.add(submitPane, BorderLayout.SOUTH);

		int windowWidth = pizzaListPane.getWidth();
		int windowHeight = pizzaListPane.getHeight()
				+ priceListPane.getHeight() + quantityPane.getHeight() + submitPane
				.getHeight() + 30;
		this.setSize(new Dimension(windowWidth, windowHeight));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		
	}

	protected JPanel getItemPane() {
		JPanel thisPane = new JPanel(new BorderLayout());

		JLabel label = new JLabel("Select Pizza");
		label.setPreferredSize(new Dimension(340, 25));
		thisPane.add(label, BorderLayout.NORTH);

		ml = new DefaultListModel();
		for (int i = 0; i < pName.size(); i++) {
			ml.addElement(pName.get(i));
		}

		pizzasList = new JList(ml);
		pizzasList.setPreferredSize(new Dimension(300, 100));
		pizzasList.addListSelectionListener(pizzaSelect);
		JScrollPane scroll = new JScrollPane(pizzasList);
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
		JPanel thisPane = new JPanel(new BorderLayout());

		JLabel label = new JLabel("Select Size of Pizza");
		label.setPreferredSize(new Dimension(340, 25));
		thisPane.add(label, BorderLayout.NORTH);
		
		JPanel sizeLabel = new JPanel(new GridLayout(3,1));
		
		JLabel smallLabel = new JLabel("Small:", SwingConstants.RIGHT);
		sizeLabel.add(smallLabel, BorderLayout.NORTH);

		JLabel medLabel = new JLabel("Medium:", SwingConstants.RIGHT);
		sizeLabel.add(medLabel);

		JLabel largeLabel = new JLabel("Large:", SwingConstants.RIGHT);
		sizeLabel.add(largeLabel);
		sizeLabel.setPreferredSize(new Dimension(50, smallLabel.getFontMetrics(smallLabel.getFont()).getHeight()*3 + 8));
		sizeLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
		thisPane.add(sizeLabel, BorderLayout.WEST);

		priceList = new JList();
		priceList.setEnabled(false);
		priceList.addListSelectionListener(priceSelect);
		setItemPrices(0);
		priceList.setPreferredSize(new Dimension(150, priceList.getSize().height));
		priceList.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		thisPane.add(priceList, BorderLayout.CENTER);
		thisPane.setSize(650, priceList.getSize().height + 50);
		thisPane.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		
		return thisPane;
	}

	protected void setItemPrices(int selected) {
		prices[0] = pSmall.get(selected);
		prices[1] = pMed.get(selected);
		prices[2] = pLarge.get(selected);
		priceList.setSelectedIndex(0);
		priceList.setListData(prices);
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
		thisPane.setSize(650, 50);

		return thisPane;
	}

	public void getFromFile() throws FileNotFoundException {
		Scanner sc = new Scanner(new BufferedReader(
				new FileReader("src/pizzas.txt")));

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
	
	public ItemType getItemType(){
		return ItemType.PIZZA;
	}
	
	public void setQuantity() {
		try {
			quantity = Integer.parseInt(quantText.getText());
			if (quantity == 0){
				throw new NumberFormatException();
			}
		} catch (NumberFormatException nfe) {
			quantText.setText(JOptionPane.showInputDialog(null, "Invalid Quantity Entry: Enter valid Quantity"));
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
			for (int a = 0; a < pName.size(); a++) {
				if (pName.get(a).equals(pizzasList.getSelectedValue())) {
					selectedItem = pName.get(a);
					setItemPrices(a);
					itemDesc = pDesc.get(a);
					descriptionText.setText(itemDesc);
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