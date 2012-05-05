package uk.ac.aber.dcs.cs12420.aberpizza.testing;

import static org.junit.Assert.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import uk.ac.aber.dcs.cs12420.aberpizza.data.*;

public class TillTest {
	Till t; 
	Order o;
	Pizza p;
	
	@Before
	public void setUp() throws IOException{
		t = new Till();
		o = new Order();
		p = new Pizza("New Pizza", new BigDecimal("3.99"), "Large", "My New Pizza");
		o.addItem(p, 3);
		t.addOrder(o);
	}
	
	@Test //1
	public void testAddGetOrder() {
		assertEquals("Expected to match", t.getOrdersArray().get(0), o);
	}
	
	@Test //2
	public void testSetGetOrdersArray(){
		ArrayList<Order> orders = new ArrayList<Order>();
		t.setOrdersArray(orders);
		assertEquals("Expected to match", t.getOrdersArray(), orders);	
	}

	@Test //3
	public void testSetGetToday(){
		String today = "today";
		t.setToday(today);
		assertEquals("Expected to match", t.getToday(), today);
	}
	
	@Test //4
	public void testGetTotalForToday(){
		BigDecimal myCalculatedTotal = new BigDecimal("0.00");
		for (int i = 0; i <t.getOrdersArray().size(); i++){
			myCalculatedTotal = myCalculatedTotal.add(t.getOrdersArray().get(i).getOrderTotal());
		}
		assertEquals("Expected to match", t.getTotalForDay(), myCalculatedTotal);
		
	}
}
