package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AmountTendered extends JFrame implements ActionListener, KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1622407942915387382L;

	private Manager manager;
	
	private BigDecimal amountTendered;
	private BigDecimal subTotal;
	private BigDecimal change;
	
	private JLabel label;
	private JTextField tenderedText;
	private JButton payOrder, cancel;
	private JFrame jf;
	
	public AmountTendered (Manager manager, BigDecimal subTotal){
		this.manager = manager;
		this.subTotal = subTotal;
		
		JPanel pane = new JPanel(new BorderLayout());
		
		label = new JLabel("Enter amount tendered");
		label.setPreferredSize(new Dimension(200, 25));
		pane.add(label, BorderLayout.NORTH);
		
		JPanel tendered = new JPanel();
		tenderedText = new JTextField();
		tenderedText.setPreferredSize(new Dimension(150,25));
		tenderedText.addKeyListener(this);
		tendered.add(tenderedText);
		tendered.setSize(new Dimension(150,25));
		pane.add(tendered, BorderLayout.CENTER);
		
		JPanel buttonPane = new JPanel(new GridLayout(1,2));
		payOrder = new JButton("Pay Order");
		payOrder.setPreferredSize(new Dimension(100, 25));
		payOrder.addActionListener(this.manager);
		buttonPane.add(payOrder);
		cancel = new JButton("Cancel");
		cancel.setPreferredSize(new Dimension(100, 25));
		cancel.addActionListener(this);
		buttonPane.add(cancel);
		pane.add(buttonPane, BorderLayout.SOUTH);
		pane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		this.add(pane);
		this.setSize(new Dimension(250, 135));
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public void changeToGive(){
		change = new BigDecimal("0");
		change = amountTendered.subtract(subTotal);
	}
	
	public BigDecimal getChange(){
		return change;
	}
	
	public BigDecimal getTenderedAmount(){
		return amountTendered;
	}
	
	public Window getChangeFrame(){
		return jf;
	}
	
	public void showChangeAmount(BigDecimal change){
		jf = new JFrame();
		jf.setLayout(null);
		
		JLabel label = new JLabel("Customer Change:");
		label.setBounds(5,5,150,25);
		jf.add(label);
		
		JLabel changeLabel = new JLabel("�" + change.toString(), SwingConstants.CENTER);
		Font f = new Font("Arial", Font.PLAIN, 25);
		changeLabel.setFont(f);
		
		changeLabel.setBounds(30, 35, 200, 50);
		jf.add(changeLabel);
		
		JButton ok = new JButton("Complete Order");
		ok.setBounds(55, 90, 150, 40);
		ok.addActionListener(manager);
		jf.add(ok);
		jf.setResizable(false);
		jf.setVisible(true);
		
		jf.setSize(260, 170);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Cancel")){
			this.dispose();
		} 
	}
	
	@Override
	public void keyReleased(KeyEvent ke) {
		try{
			amountTendered = new BigDecimal(tenderedText.getText());
			changeToGive();
		} catch (NumberFormatException nfe){
			//TODO implement exception
		}
	}
	
	
	/*************************************/
	////////// Unwanted Methods ///////////
	/*************************************/
	@Override
	public void keyPressed(KeyEvent ke) {
				
	}

	@Override
	public void keyTyped(KeyEvent ke) {
				
	}
	
	
}
