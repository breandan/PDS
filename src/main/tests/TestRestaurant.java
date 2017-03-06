import static org.junit.Assert.*;

import org.junit.Test;
import restaurant.Restaurant;


import java.io.*;

public class TestRestaurant {

	@Test
	public void test_commands() {
		Restaurant rest = new Restaurant();
		
		// we need to redirect standard output so we can compare it
		ByteArrayOutputStream myOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(myOut));
		
		// for these tests we will be dropping things on the standard in
		// to compare the resulting output
		String cmd = "\r\n";
		String err = "Invalid input.";
		
		// Invalid input
		cmd = "explode\r\n";
		System.setIn(new ByteArrayInputStream(cmd.getBytes()));
		assertEquals(err, myOut.toString());
		
		// help
		cmd = "help\r\n";
		System.setIn(new ByteArrayInputStream(cmd.getBytes()));
		// TODO this one is gonna be not even worth it prolly
		
		
	}

}
