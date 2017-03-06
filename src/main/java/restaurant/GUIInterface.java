package restaurant;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.SwingUtilities;

public class GUIInterface extends JPanel implements ActionListener {

    private JButton btnPlaceOrder, btnViewQueue, btnOrderHistory, btnSearchPhone;
    private JFrame frmLookupNumber, frmCreateCustomer;
    private Restaurant theRestaurant;

    public GUIInterface() {
        super(new BorderLayout());
        theRestaurant = new Restaurant();

        add(MainMenu(), BorderLayout.CENTER);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnPlaceOrder) {
            searchNumber();


        } else if (e.getSource() == btnViewQueue) {

        } else if (e.getSource() == btnOrderHistory) {

        } else if (e.getSource() == btnSearchPhone) {
            Customer theCust = theRestaurant.getCustomer(txtphoneNumber.getText());
            frmLookupNumber.dispose();
            if (theCust.equals(null)) {
                placeOrder(theCust);
            } else {
                createCustomer();
            }

        } else if (e.getSource() == btnCreateCustomer) {
            placeOrder(theCust);
        }

    }

    private void createCustomer() {
        frmCreateCustomer = new JFrame("Create New Customer");
        JPanel pnlCreateCustomer = new JPanel();
        JLabel lblName = new JLabel("Name:");
        JLabel lblPhone = new JLabel("Phone number");
        JComboBox cbxLocation = new JComboBox(theRestaurant.LOCATIONS);

        pnlCreateCustomer.add(lblName);
        pnlCreateCustomer.add(lblPhone);
        pnlCreateCustomer.add(cbxLocation);

        frmCreateCustomer.add(pnlCreateCustomer);
        frmCreateCustomer.pack();
        frmCreateCustomer.setVisible(true);
    }

    private void searchNumber() {
        frmLookupNumber = new JFrame("Find Customer");
        //	frmPlaceOrder.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        JPanel pnlSearchCustomer = new JPanel();
        JLabel lblPhoneNumber = new JLabel("Enter phone number: ");
        JTextArea txtphoneNumber = new JTextArea(1, 10);
        ///need to set txtarea so that it does not resize
        //txtphoneNumber.setMargin(new Insets(5,5,5,5));
        txtphoneNumber.setEditable(true);

        btnSearchPhone = new JButton("Search Phone Number");
        btnSearchPhone.addActionListener(this);
        pnlSearchCustomer.add(lblPhoneNumber);
        pnlSearchCustomer.add(txtphoneNumber);
        pnlSearchCustomer.add(btnSearchPhone);
        frmLookupNumber.add(pnlSearchCustomer);
        //Display the window.
        frmLookupNumber.pack();
        //frmLookupNumber.
        frmLookupNumber.setVisible(true);


    }

    private void placeOrder(Customer myCustomer) {


        JPanel pnlCustomer = new JPanel();
        JLabel lblCustName = new JLabel("Customer Name: " + myCustomer.getName());
        JLabel lblCustPhone = new JLabel("Customer Phone #: " + myCustomer.getPhoneNumber());
        JLabel lblAddress = new JLabel("Address: " + myCustomer.getAddress());
        pnlCustomer.add(lblCustName);
        pnlCustomer.add(lblCustPhone);
        pnlCustomer.add(lblAddress);

        JFrame frmPlaceOrder = new JFrame("Place Order");
        frmPlaceOrder.setPreferredSize(new Dimension(600, 600));
        frmPlaceOrder.setLayout(new BorderLayout());

        frmPlaceOrder.pack();
        frmPlaceOrder.setVisible(true);

        JPanel pnlMakeOrder = new JPanel();
        JLabel lblQuantity = new JLabel("Quantity: ");
        JSpinner spnQuantity = new JSpinner();
        JComboBox cbxMenuItem = new JComboBox();
        JComboBox cbxMenuOptions = new JComboBox();
        JButton btnAddItem = new JButton("Add Item");

        pnlMakeOrder.add(lblQuantity);
        pnlMakeOrder.add(spnQuantity);
        pnlMakeOrder.add(cbxMenuItem);
        pnlMakeOrder.add(cbxMenuOptions);
        pnlMakeOrder.add(btnAddItem);

        JPanel pnlCompleteOrder = new JPanel();
        JTextArea txtOrder = new JTextArea(15, 50);
        txtOrder.setEditable(false);
        pnlCompleteOrder.add(txtOrder);

        frmPlaceOrder.add(pnlCustomer, BorderLayout.NORTH);
        frmPlaceOrder.add(pnlMakeOrder, BorderLayout.CENTER);
        frmPlaceOrder.add(pnlCompleteOrder, BorderLayout.SOUTH);

    }

    private JPanel MainMenu() {
        JPanel mainButtons = new JPanel();
        mainButtons.setSize(500, 500);
        btnPlaceOrder = new JButton("Place Order");
        btnViewQueue = new JButton("View Queue");
        btnOrderHistory = new JButton("Order History");

        btnPlaceOrder.addActionListener(this);
        btnViewQueue.addActionListener(this);
        btnOrderHistory.addActionListener(this);

        mainButtons.add(btnPlaceOrder);
        mainButtons.add(btnViewQueue);
        mainButtons.add(btnOrderHistory);

        return mainButtons;

    }

    /**
     * Create the GUI and show it.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Pizza Order System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600, 400));
        //Add content to the window.
        frame.add(new GUIInterface());


        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();

            }
        });

    }

}
