package uk.ac.aber.dcs.cs12420.aberpizza.data;

import uk.ac.aber.dcs.cs12420.aberpizza.data.*;
import uk.ac.aber.dcs.cs12420.aberpizza.gui.*;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Till implements Serializable {

	private Manager manager;

	/**
	 * Creates SerialUID to enable saving and loading of files
	 */
	private static final long serialVersionUID = 811543431919259128L;
	private ArrayList<Order> orders;
	
	public Till(Manager manager){
		this.manager = manager;
		
		
	}
	
	public void addOrder(Order order){
		
	}
	
	public BigDecimal getTotalForDay(){
		return null;
	}
	
	public void save(){
		
	}
	
	public static Till load() throws IOException{
		return null;
	}
}