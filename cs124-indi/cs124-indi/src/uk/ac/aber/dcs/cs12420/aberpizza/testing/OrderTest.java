package uk.ac.aber.dcs.cs12420.aberpizza.testing;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import uk.ac.aber.dcs.cs12420.aberpizza.data.*;

public class OrderTest {
	private Order o;
	private Item p, d;
		
	@Before
	public void setUp(){
		o = new Order();
		p = new Pizza("New Yorker", new BigDecimal("10.99"), "Large", "Meaty Pizza");
		d = new Drink("Coke", new BigDecimal("3.99"), null, "Coke");
		o.addItem(p, 3);
	}
	
	@Test //1
	public void testGetSetCustomerName(){
		o.setCustomerName("Gertrude");
		assertEquals("Expected to Match", o.getCustomerName(), "Gertrude");
	}
	
	@Test //2
	public void GetSetOrderDate(){
		String date = "15/09/2011";
		o.setOrderDate(date);
		assertEquals("Expected to match", o.getOrderDate(),date);
	}
	
	@Test //3
	public void GetSetOrderTime(){
		String time = "22:22:22";
		o.setOrderTime(time);
		assertEquals("Expected to match", o.getOrderTime(), time);
	}
	
	@Test //4
	public void testGetSetOrderTotal(){
		BigDecimal total = new BigDecimal("100.00");
		o.setOrderTotal(total);
		assertEquals("Expected to match", o.getOrderTotal(), total);
	}
	
	@Test //5
	public void testAddItemGetItemAt(){
		o.addItem(d, 4);
		assertEquals("Expected to match", o.getItemAt(o.getItems().size()-1), d);
	}
	
	@Test //6
	public void testGetSetItemsArray(){
		ArrayList<OrderItem> newOrdersList = new ArrayList<OrderItem>();
		o.setItems(newOrdersList);
		assertEquals("Expected to match", o.getItems(), newOrdersList);
	}
	
	@Test //7
	public void testUpdateQuantityAndOrderItemGetQuantity(){
		int newQuantity = 10;
		o.updateItemQuantity(p, newQuantity);
		OrderItem orderItem = o.getItems().get(0);
		assertEquals("Expected to match", orderItem.getQuantity(), newQuantity);
	}
	
	@Test //8
	public void testRemoveItemFromArray(){
		o.removeItem(0);
		assertEquals("Expected ArrayList to be empty", o.getItems().size(), 0);
	}
	
	@Test //9
	public void testSetGetSubTotal(){
		BigDecimal newSubTotal = new BigDecimal("20.00");
		o.setSubtotal(newSubTotal);
		assertEquals("Expected to match", o.getSubtotal(), newSubTotal);
	}
	
	@Test //10
	public void testUpdateSubTotalgetSubTotal(){
		o.updateSubTotal();
		BigDecimal subTotal = new BigDecimal("0");
		
		for (OrderItem orderItem : o.getItems()){
			subTotal = subTotal.add(orderItem.getItem().getPrice().multiply(new BigDecimal(orderItem.getQuantity())));
		}
		assertEquals("Expected to match", o.getSubtotal(), subTotal);
		
	}
	
	@Test //11
	public void testGetDiscount3KindPizza(){
		o.getItems().clear();
		
		Item newLarge = new Pizza("New Pizza", new BigDecimal("9.99"), "Large", "My New Pizza");
		
		o.addItem(newLarge, 3);
		
		BigDecimal myCalculatedDiscount = newLarge.getPrice().multiply(new BigDecimal(3)).multiply(new BigDecimal("0.33334"));
		myCalculatedDiscount = myCalculatedDiscount.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		assertEquals("Expected to match", o.getDiscount(), myCalculatedDiscount);
	}
	@Test //12
	public void testGetDiscountMealDeal(){
		o.getItems().clear();
		
		Item newLarge = new Pizza("New Pizza", new BigDecimal("9.99"), "Large", "My New Pizza");
		Item side = new Side("Coleslaw", new BigDecimal("1.99"), "", "Coleslaw side");
		Item drink = new Drink("Orange Juice", new BigDecimal("0.99"), "", "Freshly Squeezed Orange Juice");
		
		o.addItem(newLarge, 1);
		o.addItem(side, 1);
		o.addItem(drink, 1);
		
		BigDecimal myCalculatedDiscount = newLarge.getPrice();
		myCalculatedDiscount = myCalculatedDiscount.add(side.getPrice());
		myCalculatedDiscount = myCalculatedDiscount.add(drink.getPrice());
		myCalculatedDiscount = myCalculatedDiscount.multiply(new BigDecimal("0.15"));
		myCalculatedDiscount = myCalculatedDiscount.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		assertEquals("Expected to match", o.getDiscount(), myCalculatedDiscount);
	}
}
