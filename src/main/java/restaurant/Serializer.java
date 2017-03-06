package restaurant;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Serializer {

    private FileInputStream fis = null;
    private FileOutputStream fos = null;
    private ObjectInputStream in = null;
    private ObjectOutputStream out = null;

    protected Menu readMenu() {
        Menu menu = null;
        try {
            fis = new FileInputStream("Menu");
            in = new ObjectInputStream(fis);
            menu = (Menu) in.readObject();
            in.close();
            //	System.out.println("Menu read");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return menu;
    }

    @SuppressWarnings("unchecked")
    protected ArrayList<Order> readOrders() {
        ArrayList<Order> orders = null;
        try {
            fis = new FileInputStream("Orders");
            in = new ObjectInputStream(fis);
            orders = (ArrayList<Order>) in.readObject();
            in.close();
            //		System.out.println("Orders read");
        } catch (FileNotFoundException e) {
            //do nothing, just let it return null
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return orders;
    }

    protected CustomerDB readCustomerDB() {
        CustomerDB db = null;
        try {
            fis = new FileInputStream("CustomerDB");
            in = new ObjectInputStream(fis);
            db = (CustomerDB) in.readObject();
            in.close();
            //System.out.println("CustomerDB read");
        } catch (FileNotFoundException e) {
            //do nothing, just let it return null
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return db;
    }

    protected void writeCustomerDB(CustomerDB db) {
        try {
            fos = new FileOutputStream("CustomerDB");
            out = new ObjectOutputStream(fos);
            out.writeObject(db);
            out.flush();
            out.close();
            //	System.out.println("Customer DB written to: \"CustomerDB\"");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void writeOrders(ArrayList<Order> orders) {
        try {
            fos = new FileOutputStream("Orders");
            out = new ObjectOutputStream(fos);
            out.writeObject(orders);
            out.flush();
            out.close();
            //		System.out.println("Orders written to: \"Orders\"");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void writeMenu(Menu menu) {
        try {
            fos = new FileOutputStream("Menu");
            out = new ObjectOutputStream(fos);
            out.writeObject(menu);
            out.flush();
            out.close();
            //		System.out.println("Menu written to: \"Menu\"");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
