package tests;
import static org.junit.Assert.*;

import org.junit.Test;

import resturant.Customer;
import resturant.Item;
import resturant.Order;


public class TestOrder {

	@Test
	public void test_addItem() {
		Order order = new Order(1, new Customer("Han Solo", "Millenium Falcon", "555555555"));
		
		// Test using same object
		Item i1 = new Item("Pizza");
		order.addItem(i1);
		assertTrue(order.getItems().contains(i1));
		
		// Test using equivalent object
		order.addItem(new Item("sub",1,2,3,4));
		assertTrue(order.getItems().contains(new Item("sub",1,2,3,4)));
	}
	
	@Test
	public void test_removeItem() {
		Order order = new Order(2, new Customer("Ash Ketchum", "Pallet Town", "666666666"));
		
		// Try to remove item from empty order
		order.removeItem(new Item("hot dog",1,2,3,4));
		
		// Remove the same object, only 1 Item in order
		Item i1 = new Item("pizza",1,2,3,4);
		order.addItem(new Item(i1));
		assertTrue(order.getItems().contains(i1));
		order.removeItem(i1);
		assertFalse(order.getItems().contains(i1));
		
		// Remove the equivalent object, only 1 Item in order
		order.addItem(new Item("pizza",1,2,3,4));
		assertTrue(order.getItems().contains(new Item("pizza",1,2,3,4)));
		order.removeItem(new Item("pizza",1,2,3,4));
		assertFalse(order.getItems().contains(new Item("pizza",1,2,3,4)));
		
		// Remove from order of more than one Item
		Item i2 = new Item("krabby patty",1,2,3,4);
		Item i3 = new Item("frozen banana",1,2,3,4);
		order.addItem(i2);
		order.addItem(i3);
		order.removeItem(i2);
		assertTrue(order.getItems().contains(i3));
		assertFalse(order.getItems().contains(i2));
	}
	
	@Test
	public void test_toString() {
		Order order = new Order(2, new Customer("Squidward Tentacles", "Bikini Bottom", "777777777"));
		
		// 0 items
		assertEquals("", order.toString());
		
		// 1 item
		order.addItem(new Item("pizza",1,2,3,4));
		assertEquals("pizza 1", order.toString());
		
		// >1 items
		order.addItem(new Item("more pizza",1,2,3,4));
		order.addItem(new Item("EVEN MORE PIZZA",1,2,3,4));
		assertEquals("pizza 1, more pizza 1, EVEN MORE PIZZA 1", order.toString());
		
		// Remove and item
		order.removeItem(new Item("pizza",1,2,3,4));
		assertEquals("more pizza 1, EVEN MORE PIZZA 1", order.toString());
	}

}
