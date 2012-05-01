package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.beans.*;
import java.io.*;
import java.math.BigDecimal;
import java.text.*;
import java.util.*;

public class Till {

	private Date now;
	private SimpleDateFormat dateFormat;
	private String todayDate;
	private String today;
	private static String xmlFileName;
	private String[] date = new String[3];
	private final static String PATHNAME = "./TillSaves/";

	private ArrayList<Order> orders = new ArrayList<Order>();

	public Till() throws IOException {
		orders = new ArrayList<Order>();
	}
	
	public void addOrder(Order order) {
		orders.add(order);
		System.out.println(order.getCustomerName());
	}

	public void setOrdersArray(ArrayList<Order> orders) {
		this.orders = orders;
	}

	public ArrayList<Order> getOrdersArray() {
		return orders;
	}
	
	private void setFileDate() {
		now = new Date();
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		todayDate = dateFormat.format(now);
		date = todayDate.split("/");
		setToday(date[0] + "_" + date[1] + "_" + date[2]);
		xmlFileName = getToday() + ".xml";
	}

	public void setToday(String today){
		this.today = today;
	}
	
	public String getToday(){
		return today;
	}
	
	public String getXMLFileName() {
		return xmlFileName;
	}

	public BigDecimal getTotalForDay() {
		BigDecimal total = new BigDecimal("0.00");
		System.out.println(orders.size());
		for (int i = 0; i < orders.size(); i++) {
			System.out.println(orders.get(i).getOrderTotal().toString());
			total = total.add(orders.get(i).getOrderTotal());
		}
		return total;
	}

	public void save() throws IOException {
		setFileDate();
		try {
			XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(
					new FileOutputStream(PATHNAME + xmlFileName)));
			
			PersistenceDelegate pd=encoder.getPersistenceDelegate(double.class); 
			encoder.setPersistenceDelegate(BigDecimal.class,pd);
			
			encoder.writeObject(this);

			encoder.close();
		} catch (Exception e) {
			
		}
	}

	private static String loadPath() {
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String[] fileName = format.format(now).split("/");
		String pathName = fileName[0] + "_" + fileName[1] + "_" + fileName[2]
				+ ".xml";
		return pathName;
	}

	public static Till load() throws IOException {
		Till loadTill = null;
		File f = new File(PATHNAME + loadPath());
		
		if (!f.exists()) {
			Till till = new Till();
			till.save();
		}
		
		XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(
				new FileInputStream(f)));
		
		loadTill = (Till) decoder.readObject();
		decoder.close();
		return loadTill;
	}

	public static Till loadPrevious(String oldPathName) throws IOException {
		Till loadTill = null;

		XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(
				new FileInputStream(PATHNAME + oldPathName)));
		try {
			loadTill = (Till) decoder.readObject();
		} catch (ArrayIndexOutOfBoundsException e) {

		}
		return loadTill;
	}

}
