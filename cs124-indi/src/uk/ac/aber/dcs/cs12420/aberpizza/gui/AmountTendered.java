package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AmountTendered extends JFrame implements ActionListener, KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1622407942915387382L;

	private Manager manager;
	
	private BigDecimal amountTendered;
	
	private JLabel label;
	private JTextField tenderedText;
	private JButton payOrder, cancel;
	
	public AmountTendered (Manager manager){
		this.manager = manager;
		this.setLayout(new GridLayout(3,1));
		
		label = new JLabel("Enter amount tendered");
		this.add(label);
		
		tenderedText = new JTextField();
		tenderedText.addKeyListener(this);
		this.add(tenderedText);
		
		JPanel buttonPane = new JPanel(new GridLayout(1,2));
		payOrder = new JButton("Pay Order");
		payOrder.addActionListener(this.manager);
		buttonPane.add(payOrder);
		cancel = new JButton("Cancel");
		cancel.addActionListener(this);
		buttonPane.add(cancel);
		this.add(buttonPane);
		this.setSize(new Dimension(200, 100));
		this.setVisible(true);
	}

	public BigDecimal getTenderedAmount(){
		return amountTendered;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Cancel")){
			this.dispose();
		}
	}
	
	@Override
	public void keyTyped(KeyEvent ke) {
		try{
			amountTendered = new BigDecimal(tenderedText.getText());
		} catch (NumberFormatException nfe){
			//TODO implement exception
		}
				
	}
	
	/*************************************/
	////////// Unwanted Methods ///////////
	/*************************************/
	@Override
	public void keyPressed(KeyEvent ke) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		// TODO Auto-generated method stub
		
	}
}
