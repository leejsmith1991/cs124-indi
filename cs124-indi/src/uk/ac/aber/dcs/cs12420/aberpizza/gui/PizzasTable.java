package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PizzasTable extends ItemTables {
	String[][] pizza;
	private DefaultTableModel itemData;
	private JTable table;
	
	
	public PizzasTable() throws FileNotFoundException{
		
		String [] headers = {"Pizza Name", "Small", "Medium", "Large", "Desc"};
		populateArrayList();
		
		itemData = new DefaultTableModel(pizza, headers);
		table = new JTable(itemData);
		this.add(table);
		
	}
	
	public void populateArrayList() throws FileNotFoundException{
		Scanner sc = new Scanner(new BufferedInputStream(new FileInputStream("pizzas.txt")));
		
		int j = 0;
		while (sc.hasNextLine()){
			pizza = new String[5][];
			
			for (int i = 0; i < 5; i++){
				pizza[j][i] = sc.nextLine();
			}
			j++;
		}
	}
	
	public String getSelectedItem(){
		int selectedRow = table.getSelectedRow();
		String selectedItem = pizza[selectedRow][0];
		return selectedItem;
	}
}