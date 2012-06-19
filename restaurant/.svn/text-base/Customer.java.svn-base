package restaurant;
import java.io.Serializable;

/* TODO: Do we assume each customer has a fixed delivery location? */

public class Customer implements Serializable {
	
	private String name, address, phoneNumber;
	
	public Customer(String name, String address, String phoneNumber) {
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
	
	public Customer() {
		this.name = "default";
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public void setAddress(String address) 
	{
		this.address = address;
	}
	
	public String getName() {
		return name;
	}
	
	public int getDeliveryTime()
	{
		
		if(address != null) {
			if (address.equals("RIT"))
				return 18;
			else if (address.equals("University of Rochester"))
				return 12;
			else if (address.equals("Nazareth College"))
				return 25;
			else if (address.equals("St. John Fisher"))
				return 21;
			else if (address.equals("Roberts Wesleyan College")) 
				return 25;
			else if (address.equals("Monroe Community College")) 
				return 18;
			else
				return -1;
		}
		else {
			return 5;	
		}
	}
	
	public String getAddress() {
			return address;
	}
	
	public String getPhoneNumber() 
	{
		return phoneNumber;
	}
	
	public String toString() 
	{
		return name + " - " + address + " - " + phoneNumber;
	}

}
