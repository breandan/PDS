package tests;
import static org.junit.Assert.*;

import org.junit.Test;

import resturant.Item;


public class TestItem {

	@Test
	public void test_equals() {
		Item a = new Item("pizza",10,5,20,4);
		Item b = new Item("pizza",10,5,20,4);
		Item c = new Item("calzone",7,5,10,2);
		
		// two previously created objects, equal
		assertTrue(a.equals(b));
		
		// two previously created objects, NOT equal
		assertFalse(b.equals(c));
		assertFalse(c.equals(a));
		
		// previously created with new, equal
		assertTrue(a.equals(new Item("pizza",0,0,0,0)));
		
		// previously created with new, NOT equal
		assertFalse(b.equals(new Item("calzone",0,0,0,0)));
		
		// both new, equal
		assertTrue( (new Item("fries",0,1,2,3)).equals(new Item("fries",0,1,2,3)) );
		
		// both new, not equal
		assertFalse( (new Item("salad",0,1,2,3)).equals(new Item("fries",0,1,2,3)) );
	}
	
	@Test
	public void test_toString() {
		// normal item
		Item i1 = new Item("pizza", 10, 5, 20, 4);
		assertEquals("pizza 10", i1.toString());
		
		// weird item
		Item i2 = new Item("", 2147483647, 0, 0, 0);
		assertEquals(" 2147483647", i2.toString());
	}

}
