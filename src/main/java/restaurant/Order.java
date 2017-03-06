package restaurant;

import java.io.Serializable;
import java.util.ArrayList;
/* TODO: Add delivery location, estimated delivery time, actual
 *       delivery time, time order taken, status,
 *       start time/end time estimated/end time actual for order stages */

public class Order implements Serializable {

    private int ID, total;
    private Customer customer;
    private ArrayList<Item> items;

    public Order(int ID, Customer customer) {
        this.ID = ID;
        this.customer = customer;
        total = 0;
        items = new ArrayList<Item>();
    }

    public int getLongestPrepTime() {
        int maxPrepTime = 0;
        for (Item i : items) {
            if (i.getPrepTime() > maxPrepTime) maxPrepTime = i.getPrepTime();
        }
        return maxPrepTime;
    }

    public int getLongestCookTime() {
        int maxCookTime = 0;
        for (Item i : items) {
            if (i.getCookTime() > maxCookTime) maxCookTime = i.getCookTime();
        }
        return maxCookTime;
    }

    public void addItem(Item item) {
        items.add(item);
        total += item.getPrice();
    }

    public void removeItem(Item item) {
        items.remove(item);
        total -= item.getPrice();
    }

    public int getID() {
        return ID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getTotal() {
        return total;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public String toString() {
        String s = String.format("%s, %d : ", getCustomer(), getID());

        if (!items.isEmpty()) {
            s += items.get(0).toString();
            for (int i = 1; i < items.size(); i++)
                s += ", " + items.get(i);
        }

        return s;
    }
}
