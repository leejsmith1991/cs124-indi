package uk.ac.aber.dcs.cs12420.aberpizza.data;

import javax.swing.JOptionPane;

public class BigDecimalException extends NumberFormatException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1205839184424282890L;

	public BigDecimalException(){
		JOptionPane.showMessageDialog(null, "Error in Price Field, Check Characters", "Error", JOptionPane.ERROR_MESSAGE);
	}
}
