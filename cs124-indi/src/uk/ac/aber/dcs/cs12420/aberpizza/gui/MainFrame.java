package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;



public class MainFrame extends JFrame implements WindowListener{

	private Manager manager;
	
	private MenuBar menuBar;
	private SelectionBar selectionBar;
	private SelectionTabbed selectionTab;

	private static final long serialVersionUID = 4978171281055317618L;
	
	public MainFrame(uk.ac.aber.dcs.cs12420.aberpizza.gui.Manager manager) throws IOException{
		this.manager = manager;
		
		addWindowListener(this);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e){
			
		}
		
		
		JPanel mainPane = new JPanel(new GridLayout(1,2));
		mainPane.add(selectionTab());
		/*		
		selectionBar = new SelectionBar(manager);
		add(selectionBar, BorderLayout.WEST);
		
		
		selectionTab = new SelectionTabbed(manager);
		add(selectionTab, BorderLayout.WEST);
		*/
		//add(selectionTab(), BorderLayout.WEST);
		add(mainPane);
		menuBar = new MenuBar(manager);
		setJMenuBar(menuBar);
		
		this.setResizable(false);
		setSize(1024, 768);
		setVisible(true);
		
	}
	
	private JPanel selectionTab(){
		JPanel orderItems =  new JPanel();
		JTabbedPane tb = new JTabbedPane(JTabbedPane.TOP);
		tb.setPreferredSize(new Dimension(512, 600));
		tb.addTab("Pizza", new PizzasPane());
		tb.addTab("Sides", new SidesPane());
		tb.addTab("Drinks", new DrinksPane());
		orderItems.add(tb);
		return orderItems;
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
