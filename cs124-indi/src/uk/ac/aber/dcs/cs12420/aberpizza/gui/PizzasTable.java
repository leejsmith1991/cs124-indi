package uk.ac.aber.dcs.cs12420.aberpizza.gui;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.util.Scanner;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class PizzasTable extends ItemTables {
	private Vector<String[]> list = new Vector<String[]>();
	private JTable table;
	private DefaultTableModel itemData;
	
	
	
	public PizzasTable() throws FileNotFoundException{
		
		
		itemData = new DefaultTableModel();
		populateArrayList();
		
		table = new JTable(itemData);
		this.add(table);
		for (int i = 0; i < itemData.getRowCount();i++){
			System.out.println(itemData.getValueAt(i, 0));
			System.out.println(itemData.getValueAt(i, 1));
			System.out.println(itemData.getValueAt(i, 2));
			System.out.println(itemData.getValueAt(i, 3));
			System.out.println(itemData.getValueAt(i, 4));
		}
	}
	@Override
	public void populateArrayList() throws FileNotFoundException{
		Scanner sc = new Scanner(new BufferedReader(new FileReader("pizzas.txt")));
		String[] line = new String[5];
		while (sc.hasNextLine()){
			
			for (int i = 0; i < 5; i++){
				line[i] = sc.nextLine();
			}
			//list.addElement(line);
			itemData.addRow(line);			
		}
	}
	
	public String getSelectedItem(){
		int selectedRow = table.getSelectedRow();
		String selectedItem = "";
		return selectedItem;
	}
	
}