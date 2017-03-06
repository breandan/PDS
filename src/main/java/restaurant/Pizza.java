package restaurant;

public class Pizza extends Item {

    private Topping[] toppings;

    /* Pizza with no toppings has pepperoni */
    public Pizza(String name, int price, int prepTime, int cookTime,
                 int ovenUnits) {
        super(name, price, prepTime, cookTime, ovenUnits);
        this.toppings = new Topping[]{Topping.Pepperoni};
    }

    /* Pizza with toppings. Topping price is added to the initial price */
    public Pizza(String name, int price, int prepTime, int cookTime,
                 int ovenUnits, int toppingPrice, Topping[] toppings) {
        super(name, price + toppingPrice, prepTime, cookTime, ovenUnits);
        this.toppings = toppings;
    }

    public Pizza() {
    }

    public Topping[] getToppings() {
        return toppings;
    }

}
