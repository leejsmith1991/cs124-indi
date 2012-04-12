package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JComboBox;

import uk.ac.aber.dcs.cs12420.aberpizza.data.ItemType;

public class Manager implements ActionListener, MouseListener {
	private MainFrame mf;
	
	public Manager() throws IOException {
		mf = new MainFrame(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

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
		}
	}

}
