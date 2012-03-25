package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;



public class MainFrame extends JFrame implements WindowListener{

	private Manager manager;
	
	private MenuBar menuBar;
	private SelectionBar selectionBar;
	private static final long serialVersionUID = 4978171281055317618L;
	
	public MainFrame(uk.ac.aber.dcs.cs12420.aberpizza.gui.Manager manager){
		this.manager = manager;
		
		addWindowListener(this);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e){
			
		}
		
		final JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setPreferredSize(this.getPreferredSize());
		
		selectionBar = new SelectionBar(manager);
		mainPanel.add(selectionBar, BorderLayout.NORTH);
		mainPanel.setVisible(true);
		add(selectionBar, BorderLayout.NORTH);
		
		menuBar = new MenuBar(manager);
		setJMenuBar(menuBar);
	
		this.setResizable(false);
		setSize(1024, 768);
		setVisible(true);
		
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
