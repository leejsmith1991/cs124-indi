package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.Color;
import java.util.LinkedList;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7276006453724246248L;
	private Manager manager;
	private JMenu file = new JMenu("File"), admin = new JMenu("Admin"),
			about = new JMenu("About");
	private LinkedList<JMenuItem> fileItems, adminItems, helpItems;
	private boolean thisDay;

	/**
	 * Constucts a new MenuBar using manager as the ActionListener, and a
	 * boolean representing the current day to disable the save action in the
	 * menu if the version of till is not todays
	 * 
	 * @param manager
	 * @param thisDay
	 */
	public MenuBar(Manager manager, boolean thisDay) {
		this.manager = manager;
		this.thisDay = thisDay;
		this.add(file);
		this.add(admin);
		this.add(about);

		this.setBackground(Color.WHITE);

		this.addFileItems();
		this.addAdminItems();
		this.addHelpItems();

	}

	/**
	 * Creates the File items in the menu
	 */
	private void addFileItems() {
		fileItems = new LinkedList<JMenuItem>();
		JMenuItem loadPrev, save, exit;
		fileItems.add(loadPrev = new JMenuItem("Load Previous Day"));
		fileItems.add(save = new JMenuItem("Save State"));
		fileItems.add(exit = new JMenuItem("Exit"));

		loadPrev.setEnabled(true);
		save.setEnabled(true);
		exit.setEnabled(true);

		if (!thisDay) {
			save.setEnabled(false);
		}

		for (JMenuItem i : fileItems) {
			i.addActionListener(manager);
			file.add(i);
		}
	}

	/**
	 * Creates the Administative items in the menu, to add a new till item to the till
	 */
	private void addAdminItems() {
		adminItems = new LinkedList<JMenuItem>();
		adminItems.add(new JMenuItem("Add new Pizza"));
		adminItems.add(new JMenuItem("Add new Side"));
		adminItems.add(new JMenuItem("Add new Drink"));

		for (JMenuItem i : adminItems) {
			i.addActionListener(manager);
			admin.add(i);
		}
	}
	/**
	 * Creates the About items in the menu
	 */
	private void addHelpItems() {
		helpItems = new LinkedList<JMenuItem>();
		helpItems.add(new JMenuItem("About"));

		for (JMenuItem i : helpItems) {
			i.addActionListener(manager);
			about.add(i);
		}
	}
}
