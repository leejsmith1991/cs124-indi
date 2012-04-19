package uk.ac.aber.dcs.cs12420.aberpizza.testing;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import uk.ac.aber.dcs.cs12420.aberpizza.data.*;

public class ItemTest {
	
	Item m;
	
	@Before
	public void setUpTest(){
		m = new Pizza("New Yorker", new BigDecimal("10.99"), "Large", "Meaty Pizza");
	}
	
	@Test
	public void testGetSetName() {
		m.setName("New New Yorker");
		assertEquals("Expected to match", m.getName(), "New New Yorker");		
	}
	
	@Test
	public void testGetSetPrice(){
		m.setPrice(new BigDecimal("12.99"));
		assertEquals("Expected to match", m.getPrice(), new BigDecimal("12.99"));
	}

}
