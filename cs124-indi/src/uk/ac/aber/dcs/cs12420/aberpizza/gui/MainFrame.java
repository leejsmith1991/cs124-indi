package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

import uk.ac.aber.dcs.cs12420.aberpizza.data.ItemType;
import uk.ac.aber.dcs.cs12420.aberpizza.data.Till;



public class MainFrame extends JFrame implements WindowListener, ActionListener{

	private MenuBar menuBar;
	private AddItemToOrderPanel leftPanel;
	private Till till;
	private static final long serialVersionUID = 4978171281055317618L;
	
	public MainFrame() throws IOException{
		addWindowListener(this);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e){
			
		}
		
		add(new AddItemToOrderPanel());
		menuBar = new MenuBar(this);
		setJMenuBar(menuBar);
		
		till = new Till();
		
		this.setResizable(false);
		setSize(1024, 768);
		setVisible(true);
		
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
			
		} else if (action.equals("Save State")){
			try {
				till.save();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (action.equals("Load State")){
			try {
				Till.load("./");
			} catch (IOException e1) {
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
