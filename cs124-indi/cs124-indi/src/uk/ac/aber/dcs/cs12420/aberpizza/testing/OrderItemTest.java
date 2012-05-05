package uk.ac.aber.dcs.cs12420.aberpizza.testing;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import uk.ac.aber.dcs.cs12420.aberpizza.data.*;

public class OrderItemTest {

	OrderItem orderItem;
	Item p, d;

	@Before
	public void setUpTest() {
		p = new Pizza("New Yorker", new BigDecimal("10.99"), "Large",
				"Meaty Pizza");
		d = new Drink("Coke", new BigDecimal("0.99"), "", "Coke-Cola");
		orderItem = new OrderItem(p, 3);
	}

	@Test
	public void testGetItem() {
		assertEquals("Expected to match", orderItem.getItem(), p);
	}

	@Test
	public void testSetGetItem(){
		orderItem.setItem(d);
		assertEquals("Expected to match", orderItem.getItem(), d);
	}
	
	@Test
	public void testGetItemType(){
		assertEquals("Expected to match", orderItem.getItemType(), ItemType.PIZZA);
	}
	
	@Test
	public void testGetSetQuantity(){
		orderItem.setQuantity(6);
		assertEquals("Expected to match", orderItem.getQuantity(), 6);
	}
	
	@Test
	public void testGetSetUpdateOrderItemTotalGetItemPrice(){
		orderItem.setQuantity(6);
		BigDecimal itemTotal = orderItem.getItem().getPrice().multiply(new BigDecimal(orderItem.getQuantity()));
		assertEquals("Expected to Match", orderItem.getOrderItemTotal(), itemTotal);
	}
	
	@Test
	public void testGetSetOrderItemTotal(){
		BigDecimal itemTotal = orderItem.getItem().getPrice().multiply(new BigDecimal(6));
		orderItem.setOrderItemTotal(itemTotal);
		assertEquals("Expected to match", orderItem.getOrderItemTotal(), itemTotal);
	}
}
