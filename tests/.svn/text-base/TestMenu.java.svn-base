package tests;
import static org.junit.Assert.*;

import org.junit.Test;

import resturant.Item;
import resturant.Menu;

import java.io.*;

public class TestMenu {
	
	@Test
	public void test_get() {
		Item pizza = new Item("pizza",13,3,4,5);
		Item calzone = new Item("calzone", 12,2,3,4);
		Item sub = new Item("sub",11,1,2,3);
		Menu menu = new Menu(pizza, calzone, sub);
		
		// Test for Items that are in menu, should return names of Items
		assertEquals(pizza, menu.get("pizza"));
		assertEquals(calzone, menu.get("calzone"));
		assertEquals(sub, menu.get("sub"));
		
		// Test for Items that are not in menu, should return null
		assertNull(menu.get("Salad"));
	}
	

	@Test
	public void test_add() {
		Menu menu = new Menu();
		
		// Add to empty menu
		menu.add(new Item("Pizza",10,40,30,20));
		assertEquals("Pizza", menu.get("Pizza").getName());
		
		// Add to non-empty menu
		menu.add(new Item("Sub"));
		assertEquals("Sub", menu.get("Sub").getName());
	}
	
	@Test
	public void test_remove() {
		Menu menu = new Menu();
		
		// we will redirect standard output to a byte stream for comparisons
		ByteArrayOutputStream myOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(myOut));
		
		// remove non-existent item from empty menu
		menu.remove("Pizza");
		assertNotNull(myOut.toString());

		// remove from menu with only 1 item
		menu.add(new Item("Pizza"));
		assertEquals("Pizza", menu.get("Pizza").getName());
		menu.remove("Pizza");
		assertNull(menu.get("Pizza"));
		
		// remove from menu with >1 item
		menu.add(new Item("Sub"));
		menu.add(new Item("Salad"));
		assertEquals("Sub", menu.get("Sub").getName());
		assertEquals("Salad", menu.get("Salad").getName());
		menu.remove("Sub");
		assertNull(menu.get("Sub"));
		
		// remove a non-existent item from menu with items
		menu.remove("Candy");
		assertNotNull(myOut.toString());
		
		// restore normal output
		System.setOut(System.out);
	}
	
	@Test
	public void test_isEmpty() {
		Menu menu = new Menu();
		
		assertTrue(menu.isEmpty());
		
		menu.add(new Item("Pizza"));
		
		assertFalse(menu.isEmpty());
	}
	
	@Test
	public void test_toString() {
		Item pizza = new Item("pizza",13,3,4,5);
		Item calzone = new Item("calzone", 12,2,3,4);
		Item sub = new Item("sub",11,1,2,3);
		Menu menu = new Menu(pizza, calzone, sub);
		
		String exp = pizza.getName() + ", " + 
					 calzone.getName() + ", " +
					 sub.getName();
		assertEquals(exp, menu.toString());
	}

}
