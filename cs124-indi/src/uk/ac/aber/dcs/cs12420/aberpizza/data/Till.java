package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.beans.*;
import java.io.*;
import java.math.BigDecimal;
import java.text.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;


public class Till implements Serializable {

	/**
	 * Creates SerialUID to enable saving and loading of files
	 */
	private static final long serialVersionUID = 811543431919259128L;
	
	private Date today;
	private SimpleDateFormat dateFormat;
	private String todayDate;
	private static String xmlFileName;
	private String[] date = new String[3];
	private final static String PATHNAME = "./TillSaves/";
	
	private ArrayList<Order> orders;

	public Till() throws IOException{
		setFileDate();
		orders = new ArrayList<Order>();
	}
	
	private void setFileDate(){
		today = new Date();
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		todayDate = dateFormat.format(today);
		date = todayDate.split("/");
		xmlFileName = date[0] + "_" + date[1] + "_" + date[2];
	}
	
	public String getXMLFileName(){
		return xmlFileName;
	}
		
	public void addOrder(Order order) {
		orders.add(order);
		System.out.println(order.getCustomerName());
	}

	public void setOrdersArray(ArrayList orders){
		this.orders = orders;
	}
	
	public ArrayList getOrdersArray(){
		return orders;
	}
	
	public BigDecimal getTotalForDay() {
		BigDecimal total = new BigDecimal("0");
		for (int i = 0; i> orders.size(); i++){
			total.add(orders.get(i).getSubtotal());
		}
		return total;
	}

	public void save() throws IOException {
		XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(
				new FileOutputStream(PATHNAME + xmlFileName)));
		encoder.writeObject(this);
		
		encoder.close();

	}

	public static Till load() throws IOException {
		Till loadTill = null;
				
		XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(
				new FileInputStream(PATHNAME + xmlFileName)));
		loadTill = (Till) decoder.readObject();
		return loadTill;
	}
	
	public static Till loadPrevious(String oldPathName) throws IOException{
		Till loadTill = null;
				
		XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(
				new FileInputStream(PATHNAME + oldPathName)));
		loadTill = (Till) decoder.readObject();
		return loadTill;
	}
	
}
