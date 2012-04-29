package uk.ac.aber.dcs.cs12420.aberpizza.testing;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import uk.ac.aber.dcs.cs12420.aberpizza.data.*;

public class ItemTest {
	
	Item p, d, s;
	
	@Before
	public void setUpTest(){
		p = new Pizza("New Yorker", new BigDecimal("10.99"), "large", "Meaty Pizza");
		
	}
	
	@Test
	public void testGetSetName() {
		p.setName("New New Yorker");
		assertEquals("Expected to match", p.getName(), "New New Yorker");		
	}
	
	@Test
	public void testGetSetPrice(){
		p.setPrice(new BigDecimal("12.99"));
		assertEquals("Expected to match", p.getPrice(), new BigDecimal("12.99"));
	}
	
	@Test
	public void testGetSetDescription(){
		p.setDescription("Changed Description");
		assertEquals("Expected to match", p.getDescription(), "Changed Description");
	}
}
