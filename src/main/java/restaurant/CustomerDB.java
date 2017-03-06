package restaurant;

import java.io.Serializable;
import java.util.ArrayList;

public class CustomerDB implements Serializable {

    private ArrayList<Customer> db;

    public CustomerDB(Customer... customers) {
        db = new ArrayList<Customer>();
        for (Customer c : customers) {
            db.add(c);
        }
    }

    public CustomerDB() {
        db = new ArrayList<Customer>();
    }

    public boolean numberExists(String num) {
        for (Customer c : db) {
            if (c.getPhoneNumber().equals(num)) return true;
        }
        return false;
    }

    public Customer get(String num) {
        for (Customer c : db) {
            if (c.getPhoneNumber().equals(num)) return c;
        }
        return null;
    }

    public void addCustomer(Customer c) {
        db.add(c);
    }

    public String toString() {
        String customers = "";
        if (!isEmpty()) {
            customers = "Customer DB\n-----------\n";
            for (Customer c : db) {
                customers += c.toString();
                if (!c.equals(db.get(db.size() - 1))) customers += "\n";
            }
        } else {
            customers += "toString(): Customer DB is empty!";
        }
        return customers;
    }

	/* --------------------------------------------------- */

    public boolean isEmpty() {
        return db.isEmpty();
    }

}
