package restaurant;

import java.io.Serializable;

public class Item implements Serializable {

    private String name;
    private int prepTime, cookTime, ovenUnits;
    private int price;

    public Item(String name, int price2, int prepTime,
                int cookTime, int ovenUnits) {
        this.name = name;
        this.price = price2;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.ovenUnits = ovenUnits;
    }

    public Item(String name) {
        this.name = name;
        this.price = 0;
        this.prepTime = 0;
        this.cookTime = 0;
        this.ovenUnits = 0;
    }

    /* Copy constructor */
    public Item(Item item) {
        this(item.getName(), item.getPrice(), item.getPrepTime(),
                item.getCookTime(), item.getOvenUnits());
    }

    public Item() {
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public int getCookTime() {
        return cookTime;
    }

    public int getOvenUnits() {
        return ovenUnits;
    }

    public boolean equals(Object other) {
        if (other instanceof Item)
            return this.getName().equals(((Item) other).getName());

        return false;
    }

    public String toString() {
        return name + " " + price;
    }
}
