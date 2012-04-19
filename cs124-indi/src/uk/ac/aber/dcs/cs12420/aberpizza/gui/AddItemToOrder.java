package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import uk.ac.aber.dcs.cs12420.aberpizza.data.Item;

public class AddItemToOrder extends JPanel implements ActionListener {
	private Manager manager;
	
	private JPanel textHead, itemButtons;
	private JLabel headLabel;

	private Item item;
	private int quantity;

	private ItemFrame itemFrame;

	public AddItemToOrder(Manager manager) throws FileNotFoundException {
		this.manager = manager;
		this.setSize(512, 700);
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());
		itemButtons = getButtonsPanel();
		this.add(itemButtons, BorderLayout.WEST);
	}

	private JPanel getButtonsPanel() {
		
		JPanel buttonPanel = new JPanel();
		
		return buttonPanel;
	}
	
	public int getQuantity(){
		return quantity;
	} 

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.equals("Pizzas")) {
			try {
				itemFrame = new ItemPizza(manager);
			} catch (FileNotFoundException e1) {

			}
		}

	}
}
