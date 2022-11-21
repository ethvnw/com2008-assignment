/** Customer login panel
 * @author Ethan Watts
 * @version 1.3
 * @lastUpdated 21/11/22 15:24
 */

package assignment.graphics.customerdashboard;

import assignment.models.Bike;
import assignment.models.Customer;
import assignment.models.Order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class CustomerAccountPanel extends JPanel {
    private JPanel orderPanel = new JPanel();
    private JPanel orderDeletionPanel = new JPanel();
    private JLabel orderSelected = new JLabel("Selected order number:  ");
    private JTable orderDetails;
    private JScrollPane orderScrollPane;
    private final JButton deleteOrder = new JButton("Delete Order");

    private JPanel customerDetails = new JPanel();
    private JTextField firstName;
    private JTextField surname;
    private JTextField houseNo;
    private JTextField road;
    private JTextField city;
    private JTextField postcode;
    private final JButton updateDetails = new JButton("Submit");
    private JLabel formStatus = new JLabel(" ");

    protected CustomerAccountPanel(Customer customer) {
        customerDetails.setLayout(new GridLayout(7,2));
        firstName = new JTextField(customer.getForename());
        surname = new JTextField(customer.getSurname());
        houseNo = new JTextField(customer.getAddress().getHouseNum());
        road = new JTextField(customer.getAddress().getRoad());
        city = new JTextField(customer.getAddress().getCity());
        postcode = new JTextField(customer.getAddress().getPostcode());

        customerDetails.add(new JLabel("First Name", SwingConstants.LEFT));
        customerDetails.add(firstName);
        customerDetails.add(new JLabel("Surname", SwingConstants.LEFT));
        customerDetails.add(surname);
        customerDetails.add(new JLabel("House Number", SwingConstants.LEFT));
        customerDetails.add(houseNo);
        customerDetails.add(new JLabel("Road Name", SwingConstants.LEFT));
        customerDetails.add(road);
        customerDetails.add(new JLabel("City", SwingConstants.LEFT));
        customerDetails.add(city);
        customerDetails.add(new JLabel("Postcode", SwingConstants.LEFT));
        customerDetails.add(postcode);
        customerDetails.add(updateDetails);
        customerDetails.add(formStatus);

        updateDetails.addActionListener(e -> {
            try {
                customer.updateAddress(
                        houseNo.getText(),
                        road.getText(),
                        city.getText(),
                        postcode.getText()
                );
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            customer.updateName(
                    firstName.getText(),
                    surname.getText()
            );
            formStatus.setText("Changes saved");
        });


        List<Order> orderList = Order.getAllOrderOfACustomer(customer.getCustomerID());
        String[] columnNames = {"Order ID", "Date", "Assigned Staff", "Bike Brand", "Bike Name",
                "Handlebar Brand", "Wheel Brand", "Frameset Brand", "Bike Cost", "Order Status"};
        orderDetails = new JTable(new DefaultTableModel(columnNames,0));
        orderScrollPane = new JScrollPane(orderDetails);
        orderScrollPane.setPreferredSize((new Dimension(1000,600)));
        orderPanel.setBorder(BorderFactory.createTitledBorder("Your Orders"));

        orderDeletionPanel.setLayout(new BoxLayout(orderDeletionPanel,BoxLayout.Y_AXIS));
        orderDeletionPanel.add(orderSelected);
        orderDeletionPanel.add(deleteOrder);

        if (!orderList.isEmpty()) {
            orderPanel.add(orderScrollPane);
            orderPanel.add(orderDeletionPanel);

            for (Order order : orderList) {
                String[] details = new String[10];
                Bike bike = Bike.getBike(order.getBikeID());

                details[0] = String.valueOf(order.getOrderID());
                details[1] = order.getDate();
                details[2] = order.getAssigned_Staff();
                details[3] = bike.getBrand();
                details[4] = bike.getName();
                details[5] = bike.getHandlebar().getBrand();
                details[6] = bike.getWheels().getBrand();
                details[7] = bike.getFrameSet().getBrand();
                details[8] = "£" + bike.getCost();
                details[9] = order.getStatus();

                DefaultTableModel model = (DefaultTableModel) orderDetails.getModel();
                model.addRow(details);
            }
        }
        else {
            orderPanel.add(new JLabel("No orders have been placed"));
        }

        orderDetails.getSelectionModel().addListSelectionListener(e -> {
            int orderID = Integer.parseInt(orderDetails.getValueAt(orderDetails.getSelectedRow(),0).toString());
            orderSelected.setText("Selected order number:  " + orderID);
        });
        deleteOrder.addActionListener(e -> {
            int orderID = Integer.parseInt(orderDetails.getValueAt(orderDetails.getSelectedRow(),0).toString());
            Order order = Order.getOrder(orderID);
            order.deleteOrder();
        });

        this.add(customerDetails);
        this.add(orderPanel);
    }
}
