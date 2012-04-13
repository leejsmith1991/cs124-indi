package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;

public class SelectionPane extends JTabbedPane{
	private JList list;
	private DefaultListModel listModel;
	private JButton addToOrder;
	private MainFrame manager;
	public SelectionPane(MainFrame mainFrame) throws FileNotFoundException{
		this.manager = mainFrame;
		tabPlacement = JTabbedPane.TOP;
		setPreferredSize(new Dimension(512, 600));
		addTab("Pizza", populateLists("pizzas"));
		addTab("Sides", populateLists("sides"));
		addTab("Drinks", populateLists("drinks"));
		addToOrder = new JButton("Add to Order");
		addToOrder.addActionListener(mainFrame);
	}
	
	public Component populateLists(String filename) throws FileNotFoundException {
		JPanel listComponent = new JPanel();
		listModel = new DefaultListModel();

		Scanner sc = new Scanner(new BufferedInputStream(new FileInputStream(
				filename + ".txt")));

		while (sc.hasNextLine()) {
			listModel.addElement(sc.nextLine() + " - £" + sc.nextLine());
			sc.nextLine();
		}

		list = new JList(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		list.setVisible(true);
		listComponent.add(list, BorderLayout.WEST);

		this.setSize(new Dimension(512, 550));
		this.setBackground(Color.WHITE);
		return listComponent;
	}

	public String getSelected() {
		String selected = "";
		selected = (String) list.getSelectedValue();
		return selected;
	}
}
