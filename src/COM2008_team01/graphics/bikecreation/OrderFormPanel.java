/** Customer Form page where asking for customer details before processing payment.
 * @author Han Weixiang, Ethan Watts, Natalie Roberts
 * @version 1.2
 * @lastUpdated 01-12-2022 23:59
 */

package COM2008_team01.graphics.bikecreation;
import COM2008_team01.models.Address;
import COM2008_team01.models.Bike;
import COM2008_team01.models.Customer;
import COM2008_team01.models.Order;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import javax.swing.border.TitledBorder;

public class OrderFormPanel extends JPanel {
    private final JTextField tfForename;
    private final JTextField tfSurname;
    private final JTextField tfHouseNo;
    private final JTextField tfRoadName;
    private final JTextField tfCityName;
    private final JTextField tfPostcode;

    private JPanel summaryPanel = new JPanel();
    private JPanel orderDetails = new JPanel();

    private JLabel frameSet;

    private JLabel frameSetCost;

    private JLabel handlebar;

    private JLabel handlebarCost;

    private JLabel wheels;

    private JLabel wheelsCost;

    private JLabel totalCost;

    private JButton confirm = new JButton("Confirm");

    private JButton cancel = new JButton("Cancel");

    public OrderFormPanel (Order order, Bike bike, double framesetCost, double handlebarCost, double wheelCost){
        BorderLayout bl = new BorderLayout();
        //this.setLayout(bl);

        JPanel formContainer = new JPanel();
        TitledBorder borderLeft = BorderFactory.createTitledBorder("Customer Details");
        formContainer.setBorder(borderLeft);

        tfForename = new JTextField(10); // accepts upto 10 characters
        tfSurname = new JTextField(10); // accepts upto 10 characters
        tfHouseNo = new JTextField(10); // accepts upto 10 characters
        tfRoadName = new JTextField(10); // accepts upto 10 characters
        tfCityName = new JTextField(10); // accepts upto 10 characters
        tfPostcode = new JTextField(10); // accepts upto 10 characters

        JPanel form = new JPanel();
        GridLayout gb = new GridLayout(6,2);
        form.setLayout(gb);
        formContainer.add(form);

        form.add(new JLabel("Forename: ", SwingConstants.LEFT));
        form.add(tfForename);
        form.add(new JLabel("Surname: ", SwingConstants.LEFT));
        form.add(tfSurname);
        form.add(new JLabel("House No: ", SwingConstants.LEFT));
        form.add(tfHouseNo);
        form.add(new JLabel("Road Name: ", SwingConstants.LEFT));
        form.add(tfRoadName);
        form.add(new JLabel("City Name: ", SwingConstants.LEFT));
        form.add(tfCityName);
        form.add(new JLabel("Postcode: ", SwingConstants.LEFT));
        form.add(tfPostcode);


        orderDetails.setLayout(new BoxLayout(orderDetails, BoxLayout.Y_AXIS));
        TitledBorder border = BorderFactory.createTitledBorder("Order Summary");
        orderDetails.setBorder(border);
        orderDetails.add(new JLabel("Bike Name"));
        orderDetails.add(new JLabel("   " + bike.getName()));
        orderDetails.add(new JLabel("Bike Brand"));
        orderDetails.add(new JLabel("   " + bike.getBrand()));
        orderDetails.add(new JLabel("Frame Set"));
        orderDetails.add(new JLabel("   " + bike.getFrameSet().getBrand() + " " + bike.getFrameSet().getSerialNo()));
        orderDetails.add(new JLabel("   " + framesetCost));
        orderDetails.add(new JLabel("Handlebar"));
        orderDetails.add(new JLabel("   " + bike.getHandlebar().getBrand() + " " + bike.getHandlebar().getSerialNo()));
        orderDetails.add(new JLabel("   " + handlebarCost));
        orderDetails.add(new JLabel("Wheels"));
        orderDetails.add(new JLabel("   " + bike.getWheels().getBrand() + " " + bike.getWheels().getSerialNo()));
        orderDetails.add(new JLabel("   " + wheelCost));

        orderDetails.add(confirm);
        orderDetails.add(cancel);

        this.add(formContainer);
        this.add(orderDetails);

        confirm.addActionListener(e -> {
            int customerID = 0;
            if (tfForename.getText().equals("") || tfSurname.getText().equals("") ||
                    tfHouseNo.getText().equals("") || tfRoadName.getText().equals("") ||
                    tfCityName.getText().equals("")|| tfPostcode.getText().equals("")) {

            } else {

                try {
                    //make customer
                    Address address = new Address(tfHouseNo.getText(), tfRoadName.getText(), tfCityName.getText(), tfPostcode.getText());
                    address.createAddress();
                    Customer customer = new Customer(tfForename.getText(),  tfSurname.getText(), address);
                    customerID = customer.createCustomer();

                } catch (SQLException exception) {
                    exception.printStackTrace();
                } finally {
                    order.setCustomerID(customerID);
                    try {
                        int orderID = order.createOrder();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    this.setVisible(false);
                }
            }
        });
        cancel.addActionListener(e -> {
            this.setVisible(false);
        });
    }
}
