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
		d = new Drink("Coke", new BigDecimal("3.99"), null, "Coke");
		s = new Side("Coleslaw", new BigDecimal("1.99"), null, "Coleslaw");
	}
	
	@Test
	public void testPizzaGetSetName() {
		p.setName("New New Yorker");
		assertEquals("Expected to match", p.getName(), "New New Yorker");		
	}
	
	@Test
	public void testPizzaGetSetPrice(){
		p.setPrice(new BigDecimal("12.99"));
		assertEquals("Expected to match", p.getPrice(), new BigDecimal("12.99"));
	}
	
	@Test
	public void testPizzaGetSetSize(){
		p.setSize("Medium");
		assertEquals("Expected to match", p.getSize(), "Medium");
	}
	
	@Test
	public void testPizzaGetSetDescription(){
		p.setDescription("Changed Description");
		assertEquals("Expected to match", p.getDescription(), "Changed Description");
	}
	@Test
	public void testPizzaGetType(){
		assertEquals("Expected to match", p.getItemType(), ItemType.PIZZA);
	}
	
	@Test
	public void testDrinkGetSetName() {
		d.setName("Orange Juice");
		assertEquals("Expected to match", d.getName(), "Orange Juice");		
	}
	
	@Test
	public void testDrinkGetSetPrice(){
		d.setPrice(new BigDecimal("3.50"));
		assertEquals("Expected to match", d.getPrice(), new BigDecimal("3.50"));
	}
	
	@Test
	public void testDrinkGetSetDescription(){
		d.setDescription("New Orange Juice");
		assertEquals("Expected to match", d.getDescription(), "New Orange Juice");
	}
	
	@Test
	public void testDrinkGetType(){
		assertEquals("Expected to match", d.getItemType(), ItemType.DRINK);
	}
	
	@Test
	public void testSideGetSetName() {
		s.setName("Coleslaw");
		assertEquals("Expected to match", s.getName(), "Coleslaw");		
	}
	
	@Test
	public void testSideGetSetPrice(){
		s.setPrice(new BigDecimal("3.50"));
		assertEquals("Expected to match", s.getPrice(), new BigDecimal("3.50"));
	}
	
	@Test
	public void testSideGetSetDescription(){
		s.setDescription("Large Coleslaw");
		assertEquals("Expected to match", s.getDescription(), "Large Coleslaw");
	}
	
	@Test
	public void testSideGetType(){
		assertEquals("Expected to match", s.getItemType(), ItemType.SIDE);
	}
	
}
