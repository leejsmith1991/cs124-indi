package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;

import javax.swing.*;

import uk.ac.aber.dcs.cs12420.aberpizza.data.ItemType;



public class MainFrame extends JFrame implements WindowListener, ActionListener{

	private MenuBar menuBar;
	private ItemTables tablePanel;

	private static final long serialVersionUID = 4978171281055317618L;
	
	public MainFrame() throws IOException{
		addWindowListener(this);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e){
			
		}
		
		JPanel mainPane = new JPanel(new GridLayout(1,2));
		mainPane.add(leftPanel());
		
		add(mainPane);
		menuBar = new MenuBar(this);
		setJMenuBar(menuBar);
		
		this.setResizable(false);
		setSize(1024, 768);
		setVisible(true);
		
	}
	
	private JPanel leftPanel() throws FileNotFoundException{
		SpringLayout sl;
		sl = new SpringLayout();
		
		JPanel mainPanel = new JPanel(sl);
		mainPanel.setBackground(Color.WHITE);
		JPanel buttons = getButtonsPanel();
		sl.putConstraint(SpringLayout.NORTH, buttons, 10, SpringLayout.NORTH, mainPanel);
		
		JPanel list = getItems("default");
		sl.putConstraint(SpringLayout.NORTH, list, 10, SpringLayout.SOUTH, buttons);
		
		mainPanel.add(buttons);
		mainPanel.add(list);
		
		
		
		return mainPanel;
	}
	
	private JPanel getButtonsPanel(){
		SpringLayout bpsl = new SpringLayout();
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.WHITE);
		JButton pizzas, sides, drinks;
		pizzas = new JButton("Pizzas");
		pizzas.addActionListener(this);
		sides = new JButton("Sides");
		sides.addActionListener(this);
		drinks = new JButton("Drinks");
		drinks.addActionListener(this);
		
		bpsl.putConstraint(SpringLayout.EAST, buttonPanel, 10, SpringLayout.EAST, pizzas);
		bpsl.putConstraint(SpringLayout.EAST, sides, 10, SpringLayout.WEST, pizzas);
		bpsl.putConstraint(SpringLayout.EAST, drinks, 10, SpringLayout.WEST, sides);
		
		buttonPanel.add(pizzas);
		buttonPanel.add(sides);
		buttonPanel.add(drinks);
		return buttonPanel;
	}
	
	public JPanel getItems(String type) throws FileNotFoundException{
		JPanel lists = new JPanel();
		if (type.equals("pizza") || type.equals("default")){
			tablePanel = new PizzasTable();
		} else if (type.equals("sides")){
			//tablePanel = new SidesTable();
		} else if (type.equals("drinks")){
			//tablePanel = new DrinksTable();
		}
		lists.add(tablePanel);
		this.validate();
		return lists;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		AddNewItemWindow aniw = null;
		if (action.equals("Add new Pizza")) {
			aniw = new AddNewItemWindow(ItemType.PIZZA);
		} else if (action.equals("Add new Side")) {
			aniw = new AddNewItemWindow(ItemType.SIDE);
		} else if (action.equals("Add new Drink")) {
			aniw = new AddNewItemWindow(ItemType.DRINK);
		} else if (action.equals("Pizzas")){
			try {
				getItems("pizza");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} 
		
	}
	
	/********************************************/
	////////////// Unwanted Methods //////////////
	/********************************************/
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	
}
