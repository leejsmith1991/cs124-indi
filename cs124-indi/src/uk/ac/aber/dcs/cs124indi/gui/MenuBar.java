package uk.ac.aber.dcs.cs124indi.gui;

import java.util.LinkedList;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import uk.ac.aber.dcs.cs124indi.controller.*;

public class MenuBar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7276006453724246248L;
	private Manager manager;
	private JMenu file = new JMenu("File"), admin = new JMenu("Admin"),
			help = new JMenu("Help");
	private LinkedList<JMenuItem> fileItems, adminItems, helpItems;

	public MenuBar(Manager manager) {
		this.manager = manager;
		
		this.add(file);
		this.add(admin);
		this.add(help);

		this.addFileItems();
		this.addAdminItems();
		this.addHelpItems();

	}

	private void addFileItems() {
		fileItems = new LinkedList<JMenuItem>();
		fileItems.add(new JMenuItem("Load State"));
		fileItems.add(new JMenuItem("Save State"));
		fileItems.add(new JMenuItem("Exit"));

		for (JMenuItem i : fileItems) {
			i.addActionListener(manager);
			file.add(i);
		}
	}
	
	private void addAdminItems(){
		adminItems = new LinkedList<JMenuItem>();
		adminItems.add(new JMenuItem("Add new Pizza"));
		adminItems.add(new JMenuItem("Add new Side"));
		adminItems.add(new JMenuItem("Add new Drink"));
		
		for (JMenuItem i : adminItems){
			i.addActionListener(manager);
			admin.add(i);
		}
	}
	
	private void addHelpItems(){
		helpItems = new LinkedList<JMenuItem>();
		helpItems.add(new JMenuItem("Help"));
		helpItems.add(new JMenuItem("About"));
		
		for (JMenuItem i : helpItems){
			i.addActionListener(manager);
			help.add(i);
		}
	}
}