package uk.ac.aber.dcs.cs124indi.gui;

import java.util.LinkedList;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7276006453724246248L;

	private JMenu file = new JMenu("File"), admin = new JMenu("Admin"),
			help = new JMenu("Help");
	private LinkedList<JMenuItem> fileItems, adminItems, helpItems;

	public MenuBar() {
		this.add(file);
		this.add(admin);
		this.add(help);

		this.addFileItems();

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
}
