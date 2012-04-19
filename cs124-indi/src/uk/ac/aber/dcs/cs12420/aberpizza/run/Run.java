package uk.ac.aber.dcs.cs12420.aberpizza.run;

import java.io.IOException;

import uk.ac.aber.dcs.cs12420.aberpizza.gui.AmountTendered;
import uk.ac.aber.dcs.cs12420.aberpizza.gui.ItemPizza;
import uk.ac.aber.dcs.cs12420.aberpizza.gui.MainFrame;
import uk.ac.aber.dcs.cs12420.aberpizza.gui.Manager;


public class Run {

	/**
	 * @param args
	 * @throws IOException
	 */

	static Manager m;
	
	public static void main(String[] args) throws IOException {
		//Manager run = new Manager();
		AmountTendered ip = new AmountTendered(m);
	}
}
