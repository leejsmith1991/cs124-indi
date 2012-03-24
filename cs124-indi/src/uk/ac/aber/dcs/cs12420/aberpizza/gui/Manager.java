package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import uk.ac.aber.dcs.cs12420.aberpizza.data.ItemType;



public class Manager implements ActionListener, MouseListener{

	private MainFrame window;
	
	public Manager(){
		window = new MainFrame(this);
		window.setTitle("AberPizza");
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		
		if (action.equals("Add new Pizza")){
			createNewItem(ItemType.PIZZA);
		} else if (action.equals("Add new Side")){
			createNewItem(ItemType.SIDE);
		} else if (action.equals("Add new Drink")){
			createNewItem(ItemType.DRINK);
		}
		
	}
	private void createNewItem(ItemType type){
		AddNewItemWindow addNew = new AddNewItemWindow(type);
	}
}
