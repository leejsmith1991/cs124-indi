package uk.ac.aber.dcs.cs12420.aberpizza.testing;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import org.junit.Test;

import uk.ac.aber.dcs.cs12420.aberpizza.data.Item;
import uk.ac.aber.dcs.cs12420.aberpizza.data.Pizzas;

public class ItemTest {

	@Test
	public void testMakeNewItem() {
		Item m;
		m = new Pizzas("New Yorker", new BigDecimal("10.99"), "Meaty pizza with Sweetcorn and Mushrooms");
		
		
	}

}
