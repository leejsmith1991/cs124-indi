package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.event.*;
import java.io.*;

import javax.swing.*;

public class NewOrder extends JFrame implements WindowListener{

	private static final long serialVersionUID = 4978171281055317618L;
	private Manager manager;
	public NewOrder(Manager manager) throws IOException {
		this.manager = manager;
		
		this.setResizable(false);
		setSize(1024, 768);
		setVisible(true);
	}

	/********************************************/
	// //////////// Unwanted Methods //////////////
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
