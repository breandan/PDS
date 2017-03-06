package restaurant;

import java.io.Serializable;
import java.util.ArrayList;

public class OrderDB implements Serializable {

    private ArrayList<Order> db;

    public OrderDB(Order... orders) {
        db = new ArrayList<Order>();
        for (Order o : orders) {
            db.add(o);
        }
    }

    public OrderDB() {
        db = new ArrayList<Order>();
    }

    public void addOrder(Order o) {
        db.add(o);
    }

    public boolean isEmpty() {
        return db.isEmpty();
    }

}
