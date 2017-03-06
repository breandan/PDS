package restaurant;
/* Holds a collection of items. */

import java.io.Serializable;
import java.util.ArrayList;

public class Menu implements Serializable {

    private ArrayList<Item> menu;

    public Menu(Item... items) {
        menu = new ArrayList<Item>();
        for (Item i : items) {
            menu.add(i);
        }
    }

    public Menu() {
        menu = new ArrayList<Item>();
    }

    public void add(Item item) {
        menu.add(item);
    }

    public boolean isEmpty() {
        return menu.isEmpty();
    }

    public void remove(String name) {
        for (Item i : menu) {
            if (i.getName().equals(name)) {
                menu.remove(i);
                return;
            }
        }
        System.out.format("Menu.remove(): Cannot find '%s'!\n", name);
    }

    /* Searches the menu for the given item name and if found, returns
     * a deep copy of the Item object.
     * TODO: Error out if menu is empty or if name cannot be found */
    public Item get(String name) {
        for (Item i : menu) {
            if (i.getName().equals(name)) return new Item(i);
        }
        System.out.format("Menu.get(): Cannot find '%s'!\n", name);
        return null;
    }

    /* Lists all items on the menu.
     * TODO: Add other Item parameters (prep time, etc.) and format
     *       everything neatly */
    public String toString() {
        String items = "";
        for (Item i : menu) {
            items += i.getName();
            if (!i.equals(menu.get(menu.size() - 1))) items += ", ";
        }
        return items;
    }

    public ArrayList<Item> getMenuItems() {
        return menu;
    }

}
