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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Produces a window where the user can enter the amount that the customer has
 * given, will calculate change to be given to the customer
 * 
 * @author Lee Smith
 * 
 */

public class AmountTendered extends JFrame implements ActionListener,
		KeyListener {
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

	/**
	 * Constructs a new JFrame and lays out the components
	 * 
	 * @param manager
	 * @param subTotal
	 */

	public AmountTendered(Manager manager, BigDecimal subTotal) {
		this.manager = manager;
		this.subTotal = subTotal;

		JPanel pane = new JPanel(new BorderLayout());

		label = new JLabel("Enter amount tendered");
		label.setPreferredSize(new Dimension(200, 25));
		pane.add(label, BorderLayout.NORTH);

		JPanel tendered = new JPanel();
		tenderedText = new JTextField();
		tenderedText.setPreferredSize(new Dimension(150, 25));
		tenderedText.addKeyListener(this);
		tendered.add(tenderedText);
		tendered.setSize(new Dimension(150, 25));
		pane.add(tendered, BorderLayout.CENTER);

		JPanel buttonPane = new JPanel(new GridLayout(1, 2));
		payOrder = new JButton("Pay Order");
		payOrder.setPreferredSize(new Dimension(100, 25));
		payOrder.addActionListener(this.manager);
		buttonPane.add(payOrder);
		cancel = new JButton("Cancel");
		cancel.setPreferredSize(new Dimension(100, 25));
		cancel.addActionListener(this);
		buttonPane.add(cancel);
		pane.add(buttonPane, BorderLayout.SOUTH);
		pane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.add(pane);
		this.setSize(new Dimension(250, 135));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}

	/**
	 * Calculates the amount of change to be given the the customer and stores
	 * it in a variable
	 */

	public void changeToGive() {
		change = new BigDecimal("0");
		change = amountTendered.subtract(subTotal);
	}

	/**
	 * Gets the amount of change that the user needs to give the customer
	 * 
	 * @return change
	 */

	public BigDecimal getChange() {
		return change;
	}

	/**
	 * Gets the value that the user has entered, this is the amount of money that the customer has given the user
	 * @return amountTendered
	 */
	
	public BigDecimal getTenderedAmount() {
		return amountTendered;
	}

	/**
	 * Get this window 
	 * @return jf - <code>Window</code>
	 */
	public Window getChangeFrame() {
		return jf;
	}

	/**
	 * Displays a JFrame with components showing the amount of change to be given to the customer
	 * @param change
	 */
	
	public void showChangeAmount(BigDecimal change) {
		this.setEnabled(false);
		jf = new JFrame();
		jf.setLayout(null);

		JLabel label = new JLabel("Customer Change:");
		label.setBounds(5, 5, 150, 25);
		jf.add(label);

		JLabel changeLabel = new JLabel("£" + change.toString(),
				SwingConstants.CENTER);
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
		jf.setLocationRelativeTo(null);
	}

	/**
	 * Overrides the <code>actionPerformed()</code> methods in the ActionListener interface
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Cancel")) {
			this.dispose();
		}
	}

	/**
	 * Changes the amount tendered value when a key is released
	 */
	
	@Override
	public void keyReleased(KeyEvent ke) {
		try {
			amountTendered = new BigDecimal(tenderedText.getText());
			changeToGive();
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(this, "Invalid format amount entered, possible invalid Character, Use the format (££.pp)");
		}
	}

	/*************************************/
	// //////// Unwanted Methods ///////////
	/*************************************/
	@Override
	public void keyPressed(KeyEvent ke) {

	}

	@Override
	public void keyTyped(KeyEvent ke) {

	}

}
