package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Till implements Serializable {

	/**
	 * Creates SerialUID to enable saving and loading of files
	 */
	private static final long serialVersionUID = 811543431919259128L;
	private final String FILE_EXTENSION = "apiz";
	private final String pathname = "./";

	private ArrayList<Order> orders;

	public Till() {

	}

	public void addOrder(Order order) {
		
	}

	public BigDecimal getTotalForDay() {
		return null;
	}

	public void save() throws IOException {
		XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(
				new FileOutputStream(pathname)));
		encoder.writeObject(this);
		encoder.close();
	}

	public static Till load(String pathname) throws IOException {
		Till loadTill = null;
		XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(
				new FileInputStream(pathname)));
		loadTill = (Till) decoder.readObject();
		return loadTill;
	}
}
