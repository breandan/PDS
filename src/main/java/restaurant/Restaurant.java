package restaurant;

import java.util.*;

import clock.*;

public class Restaurant {

	/* Constants for default Restaurant settings */

    public static final int DEFAULT_NUM_COOKS = 3;

    public static final int DEFAULT_NUM_DRIVERS = 3;

    public static final Item[] DEFAULT_MENU_ITEMS = {
            new Item("Small Pizza", 8, 8, 13, 1),
            new Item("Medium Pizza", 11, 10, 15, 2),
            new Item("Large Pizza", 16, 15, 20, 4),
            new Item("Pizza Log", 6, 0, 10, 1),
            new Item("Salad", 5, 5, 0, 0)
    };

    public static final String[] LOCATIONS = {
            "RIT", "University of Rochester", "Nazareth College", "St. John Fisher",
            "Roberts Wesleyan College", "Monroe Community College"
    };


    // List of all Cashiers
    private ArrayList<String> cashiers;

    // Clock object for handling orders
    private Clock clock;

    // Menu for storing available food Items
    private Menu menu;

    // Number of cooks & drivers
    int numCooks, numDrivers;

    // Serializer object for object persistence
    private Serializer serializer;

    // DB of Customers
    private CustomerDB customerDB;

    // List of all Orders
    private ArrayList<Order> orders;


    /**
     * Create a new Restaurant
     */
    public Restaurant() {
        this.cashiers = new ArrayList<String>();
        this.clock = new Clock();
        this.menu = new Menu(Restaurant.DEFAULT_MENU_ITEMS);
        this.numCooks = Restaurant.DEFAULT_NUM_COOKS;
        this.numDrivers = Restaurant.DEFAULT_NUM_DRIVERS;
        this.serializer = new Serializer();

        if ((this.customerDB = serializer.readCustomerDB()) == null)
            customerDB = new CustomerDB();

        if ((this.orders = serializer.readOrders()) == null)
            orders = new ArrayList<Order>();

    }


    /**
     * Main: run a loop to get text commands from user
     */
    public static void main(String[] args) {
        Restaurant rest = new Restaurant();

        // Start the command prompt
        Scanner in = new Scanner(System.in);

        System.out.println("********Welcome to the Pizza Delivery System********");
        rest.help();

        boolean input = true;
        do {
            System.out.println("++Enter command number++");
            String cmd = in.nextLine();

            if (cmd.equals("1")) {
                rest.newOrder();
            } else if (cmd.equals("2")) {
                rest.addCashier();
            } else if (cmd.equals("3")) {
                System.out.println("--Current Cashiers:--");
                for (String c : rest.getCashiers())
                    System.out.println(c);

                System.out.println("\nName to be removed:");
                String name = in.next();
                rest.removeCashier(name);
            } else if (cmd.equals("4")) {
                rest.viewOrders();
            } else if (cmd.equals("5")) {
                rest.viewHistory();
            } else if (cmd.equals("6")) {
                rest.viewEmployees();
            } else if (cmd.equals("7")) {
                rest.viewMenu();
            } else if (cmd.equals("8")) {
                rest.managerMenu();
            } else if (cmd.equals("help")) {
                rest.help();
            } else if (cmd.equals("exit")) {
                input = false;
            } else {
                System.out.println("Invalid command.");
            }
        } while (input);

    }


    /**
     * Display list of available functions
     */
    private void help() {
        System.out.println("----------Main Menu:----------");
        System.out.println("1. New Order");
        System.out.println("2. Add Cashier");
        System.out.println("3. Remove Cashier");
        System.out.println("4. View Today's Orders");
        System.out.println("5. View Complete Order History");
        System.out.println("6. View Employees");
        System.out.println("7. View Menu");
        System.out.println("8. Manager Options");
        System.out.println("help - show available comands ");
        System.out.println("exit - terminate program");
    }

    /**
     * Create a new order
     */
    private void newOrder() {
        Customer customer;
        Order order;
        String phone;

        Scanner in = new Scanner(System.in);

        // Ask for customer info
        System.out.print("Phone number (xxxxxxxxxx):");
        phone = in.nextLine();
        if (customerDB.numberExists(phone)) {
            customer = customerDB.get(phone);
            System.out.format("Customer Info: %s\n", customer);
        } else {
            String name;
            String address = "";
            System.out.format("%s not found\n", phone);
            System.out.print("Customer name: ");
            name = in.nextLine();

            // Get the customer's location
            boolean validLoc = true;
            do {
                int c = 0;
                for (String s : Restaurant.LOCATIONS) {
                    System.out.format("%d. %s\n", c, s);
                    c++;
                }
                System.out.print("\nLocation:");
                String l = in.nextLine();

                try {
                    address = Restaurant.LOCATIONS[Integer.parseInt(l)];
                } catch (Exception ex) {
                    System.out.println("Invalid choice");
                    validLoc = false;
                }
            } while (!validLoc);

            customer = new Customer(name, address, phone);
            customerDB.addCustomer(customer);
            serializer.writeCustomerDB(customerDB);
        }

        // now that we have the customer, we can initialize the order
        order = new Order((int) (10000 * Math.random()), customer);

        // Get food items for order
        viewMenu();
        System.out.println("\nChoose one item at a time. Enter -1 to finish");

        boolean done = false;
        do {
            System.out.println("Item to add:");
            String i = in.nextLine();

            if (i.equals("-1")) {
                done = true;
            } else {
                try {
                    order.addItem(menu.getMenuItems().get(Integer.parseInt(i)));
                } catch (Exception e) {
                    System.out.println("Invalid choice");
                }
            }
        } while (!done);

        // update serializer and give order to clock
        orders.add(order);
        serializer.writeOrders(orders);
        clock.drop(order);
    }

    /**
     * Add a cashier
     */
    private void addCashier() {
        System.out.println("New Cashier Name:");

        Scanner in = new Scanner(System.in);
        String name = in.next();
        cashiers.add(name);
    }

    /**
     * Remove a cashier
     *
     * @param name name of the cashier to be removed
     */
    private void removeCashier(String name) {
        cashiers.remove(name);
    }

    /**
     * Display all active orders
     */
    private void viewOrders() {
        if (orders.size() > 0) {
            for (Order o : orders)
                System.out.println(o);
        } else {
            System.out.println("No active orders");
        }
    }

    /**
     * View complete history of processed orders
     */
    private void viewHistory() {
        for (Order o : orders)
            System.out.println(o);
    }

    /**
     * Show number of each type of employee currently working
     */
    private void viewEmployees() {
        System.out.format("Cashiers: %d\nCooks: %d\nDrivers: %d\n",
                cashiers.size(), numCooks, numDrivers);
    }

    /**
     * Display the food Menu
     */
    private void viewMenu() {
        System.out.println("--Food Menu--");

        int c = 0;
        for (Item i : menu.getMenuItems()) {
            System.out.println(c + ". " + i);
            c++;
        }
    }

    /**
     * Change to manager menu
     */
    private void managerMenu() {
        // Start the command prompt
        Scanner in = new Scanner(System.in);

        managerHelp();

        boolean input = true;
        do {
            System.out.println("++Enter command number++");
            String cmd = in.next();
            in.nextLine();

            if (cmd.equals("1")) {
                addFoodItem();
            } else if (cmd.equals("2")) {
                removeFoodItem();
            } else if (cmd.equals("3")) {
                clearMenu();
            } else if (cmd.equals("4")) {
                input = false;
            } else {
                System.out.println("Invalid choice");
            }
        } while (input);
    }


    /**
     * Display the manager's menu
     */
    public static void managerHelp() {
        System.out.println("----------Manager Menu:----------");
        System.out.println("1. Add item to food menu");
        System.out.println("2. Remove item from food menu");
        System.out.println("3. Clear all items from menu");
        System.out.println("4. Return main menu");
    }

    /**
     * Add item to food menu
     */
    private void addFoodItem() {
        Scanner in = new Scanner(System.in);
        try {
            System.out.println("Item name:");
            String name = in.nextLine();
            System.out.println("Price:");
            int price = Integer.parseInt(in.next());
            System.out.println("Prep time in mins:");
            int preptime = Integer.parseInt(in.next());
            System.out.println("Cook time? (Minutes)");
            int cooktime = Integer.parseInt(in.next());
            System.out.println("Number of oven units:");
            int ovenunits = Integer.parseInt(in.next());

            Item item = new Item(name, price, preptime, cooktime, ovenunits);
            menu.add(item);
        } catch (Exception ex) {
            System.out.println("Invalid choice");
        }
    }

    /**
     * Remove item to food menu
     */
    private void removeFoodItem() {
        viewMenu();
        System.out.println("Choose one item at a time. Enter -1 when finished");

        Scanner in = new Scanner(System.in);

        boolean done = false;
        do {
            System.out.println("Item to remove:");
            String i = in.next();
            in.nextLine();

            if (i.equals("-1")) {
                done = true;
            } else {
                try {
                    menu.remove(menu.getMenuItems().get(Integer.parseInt(i)).getName());
                } catch (Exception ex) {
                    System.out.println("Invalid choice");
                }
            }
        } while (!done);
    }

    /**
     * Clear all items from food menu
     */
    private void clearMenu() {
        System.out.println("Are you sure you want to clear all items from menu? (yes/no)");

        Scanner in = new Scanner(System.in);
        String answer = in.nextLine();
        if (answer.toLowerCase().equals("yes"))
            menu = new Menu();
    }


    /**
     * Return ArrayList of current cashiers
     *
     * @return current cashiers
     */
    private ArrayList<String> getCashiers() {
        return cashiers;
    }

}
