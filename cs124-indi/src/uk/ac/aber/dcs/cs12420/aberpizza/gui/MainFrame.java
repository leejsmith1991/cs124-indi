package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import uk.ac.aber.dcs.cs12420.aberpizza.data.Manager;

public class MainFrame extends JFrame implements WindowListener{

	private Manager manager;
	
	private MenuBar menuBar;
	private static final long serialVersionUID = 4978171281055317618L;
	
	public MainFrame(uk.ac.aber.dcs.cs12420.aberpizza.data.Manager manager){
		this.manager = manager;
		
		addWindowListener(this);
		
		menuBar = new MenuBar(manager);
		
		setJMenuBar(menuBar);
		
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
