package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

public class SelectionPane extends JPanel {
	private JList list;
	private DefaultListModel listModel;

	public SelectionPane(String filename) throws FileNotFoundException {
		listModel = new DefaultListModel();

		Scanner sc = new Scanner(new BufferedInputStream(new FileInputStream(
				filename + ".txt")));

		while (sc.hasNextLine()) {
			listModel.addElement(sc.nextLine());
			sc.nextLine();
			sc.nextLine();
		}

		list = new JList(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		list.setVisible(true);
		add(list, BorderLayout.WEST);

		this.setSize(new Dimension(512, 550));
		this.setBackground(Color.WHITE);
	}

	public String getSelected() {
		String selected = "";
		selected = (String) list.getSelectedValue();
		return selected;
	}
}
