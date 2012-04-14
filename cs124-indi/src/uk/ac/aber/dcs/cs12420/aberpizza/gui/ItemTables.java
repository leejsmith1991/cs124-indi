package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.io.FileNotFoundException;

import javax.swing.JPanel;


public abstract class ItemTables extends JPanel {
	
	
	public ItemTables(){
		this.setEnabled(false);
	}
	
	public abstract void populateArrayList() throws FileNotFoundException;
	
	public abstract String getSelectedItem();
}
