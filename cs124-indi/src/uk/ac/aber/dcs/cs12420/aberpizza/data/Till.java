package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.beans.*;
import java.io.*;
import java.math.BigDecimal;
import java.text.*;
import java.util.*;

import javax.swing.JOptionPane;

public class Till {

	private Date now;
	private SimpleDateFormat dateFormat;
	private String todayDate;
	private String today;
	private static String xmlFileName;
	private String[] date = new String[3];
	private final static String PATHNAME = "./TillSaves/";

	private ArrayList<Order> orders = new ArrayList<Order>();

	/**
	 * Constructs a Till object, and initialises the orders
	 * <code>ArrayList</code><<code>Order</code>>
	 * 
	 * @throws IOException
	 */
	public Till() throws IOException {
		orders = new ArrayList<Order>();
	}

	/**
	 * Add Order to the till Order ArrayList
	 * 
	 * @param order
	 *            - <code>Order</code>
	 */
	public void addOrder(Order order) {
		orders.add(order);
	}

	/**
	 * Sets the <code>ArrayList</code><<code>Order</code>>
	 * 
	 * @param orders
	 */
	public void setOrdersArray(ArrayList<Order> orders) {
		this.orders = orders;
	}

	/**
	 * Returns the <code>ArrayList</code><<code>Order</code>>
	 * 
	 * @return
	 */

	public ArrayList<Order> getOrdersArray() {
		return orders;
	}

	/**
	 * Sets the date and transforms it into a string ready to be used to name
	 * the file
	 */
	private void setFileDate() {
		now = new Date();
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		todayDate = dateFormat.format(now);
		date = todayDate.split("/");
		setToday(date[0] + "_" + date[1] + "_" + date[2]);
		xmlFileName = getToday() + ".xml";
	}

	/**
	 * Sets the today String
	 * 
	 * @param today
	 */
	public void setToday(String today) {
		this.today = today;
	}

	/**
	 * Returns the String that holds the today value
	 * 
	 * @return today
	 */
	public String getToday() {
		return today;
	}

	/**
	 * Calculates the total for the day, by cycling through the
	 * <code>ArrayList</code>, and extracting the Order totals.
	 * 
	 * @return total
	 */
	public BigDecimal getTotalForDay() {
		BigDecimal total = new BigDecimal("0.00");
		for (int i = 0; i < orders.size(); i++) {
			total = total.add(orders.get(i).getOrderTotal());
		}
		return total;
	}

	/**
	 * Saves an XML file using <code>XMLEncoder</code>.
	 * <code>PersistenceDelegate</code> was also used as BigDecimal would not
	 * serialise properly without this
	 * 
	 * @see XMLEncoder
	 * @see PersistenceDelegate
	 * @throws IOException
	 */
	public void save() throws IOException {
		setFileDate();
		try {
			XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(
					new FileOutputStream(PATHNAME + xmlFileName)));

			PersistenceDelegate pd = encoder
					.getPersistenceDelegate(Integer.class);
			encoder.setPersistenceDelegate(BigDecimal.class, pd);

			encoder.writeObject(this);

			encoder.close();
		} catch (Exception e) {

		}
	}

	/**
	 * Sets the loading path of the load files using the date
	 * 
	 * @return
	 */
	private static String loadPath() {
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String[] fileName = format.format(now).split("/");
		String pathName = fileName[0] + "_" + fileName[1] + "_" + fileName[2]
				+ ".xml";
		return pathName;
	}

	/**
	 * As an folder external to the Runnable Jar file is required to run the
	 * program the a file check takes place and if no folder exists it will
	 * create one. Also for the xml file the same thing happens but runs the
	 * save method to save a instance of a new Till as one has not been created
	 * for the day.
	 * 
	 * @return Till
	 * @throws IOException
	 */
	public static Till load() throws IOException {
		Till loadTill = null;

		File folder = new File(PATHNAME);
		if (!folder.exists()) {
			folder.mkdir();
		}
		
		File f = new File(PATHNAME + loadPath());

		if (!f.exists()) {
			Till till = new Till();
			till.save();
		}
		try {
			XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(
					new FileInputStream(f)));
			loadTill = (Till) decoder.readObject();
			decoder.close();
		} catch (FileNotFoundException nf) {
			JOptionPane.showMessageDialog(null, "File/Folder does not exist");
		}

		return loadTill;
	}

	/**
	 * Allows user to load in an existing XML file that is
	 * 
	 * @param oldPathName
	 * @return
	 * @throws IOException
	 */
	public static Till loadPrevious(String oldPathName) throws IOException {
		Till loadTill = null;

		try {
			XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(
					new FileInputStream(PATHNAME + oldPathName)));

			loadTill = (Till) decoder.readObject();
		} catch (Exception e) {

		}
		return loadTill;
	}

}
