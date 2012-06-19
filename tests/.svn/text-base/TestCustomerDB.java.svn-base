package tests;
import static org.junit.Assert.*;

import org.junit.Test;

import resturant.Customer;
import resturant.CustomerDB;


public class TestCustomerDB {

	@Test
	public void test_numberExists() {
		CustomerDB db = new CustomerDB( new Customer("herp", "herp street", "5851234567"),
										new Customer("derp", "derp road", "5851237654"),
										new Customer("bob", "bob avenue", "5857774545") );
		
		// Test on number that does exist
		assertTrue(db.numberExists("5851234567"));
		
		// Test on number that does not exist
		assertFalse(db.numberExists("444444444"));
	}
	
	@Test
	public void test_addCustomer() {
		CustomerDB db = new CustomerDB();
		
		// Add to empty DB
		db.addCustomer(new Customer("Antonio Tony", "123 1st street", "5856234576"));
		assertTrue(db.numberExists("5856234576"));
		
		// Add to non-empty DB
		db.addCustomer(new Customer("your mom", "123 Fake Street", "18005882300"));
		assertTrue(db.numberExists("18005882300"));
	}
	
	@Test
	public void test_get() {
		CustomerDB db = new CustomerDB();

		// get non-existent from empty
		assertNull(db.get("1231231234"));
		
		// get existent from non-empty DB
		db.addCustomer(new Customer("bob the builder", "2nd street", "0987654321"));
		db.addCustomer(new Customer("dora the explorer", "idk, like mexico i think", "1234567890"));
		assertEquals("bob the builder", db.get("0987654321").getName());
		
		// get non-existent from non-empty DB
		assertNull(db.get("42"));
	}
	
	@Test
	public void test_isEmpty() {
		CustomerDB db = new CustomerDB();
		
		assertTrue(db.isEmpty());
		
		db.addCustomer(new Customer("George Washington", "the white house", "what's a phone?"));
		
		assertFalse(db.isEmpty());
	}
	
	@Test
	public void test_toString() {
		CustomerDB db = new CustomerDB();
		
		// empty DB
		assertEquals("toString(): Customer DB is empty!", db.toString());
		
		// non-empty DB
		db.addCustomer(new Customer("dora the explorer", "rochester", "0987654321"));
		db.addCustomer(new Customer("dora la exploradora", "rochester", "1234567890"));
		
		String val = "Customer DB\n-----------\n";
		val += "dora the explorer - rochester - 0987654321\n";
		val += "dora la exploradora - rochester - 1234567890";
		assertEquals(val, db.toString());
	}

}
