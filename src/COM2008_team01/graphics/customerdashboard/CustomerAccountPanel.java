/** Customer account panel - shows all details of customer, along with all orders they've made
 * @author Ethan Watts
 * @version 1.3
 * @lastUpdated 30/11/22 14:05
 */

package COM2008_team01.graphics.customerdashboard;

import COM2008_team01.models.Bike;
import COM2008_team01.models.Customer;
import COM2008_team01.models.Order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class CustomerAccountPanel extends JPanel {
    private Customer customer;

    private JPanel orderPanel = new JPanel();
    private JPanel orderDeletionPanel = new JPanel();
    private JTable orderDetails;
    private JScrollPane orderScrollPane;
    private JLabel orderSelected = new JLabel("Selected order number:  ");
    private final JButton deleteOrder = new JButton("Delete Order");
    private JLabel deletionErrorMsg = new JLabel("");

    private JPanel customerDetails = new JPanel();
    private JTextField firstName;
    private JTextField surname;
    private JTextField houseNo;
    private JTextField road;
    private JTextField city;
    private JTextField postcode;
    private final JButton updateDetails = new JButton("Submit");
    private JLabel formStatus = new JLabel(" ");


    /**
     * Creates the JPanel showing the details of a customer
     * @param customer the customer to view details of
     */
    protected CustomerAccountPanel(Customer customer) throws SQLException {
        this.customer = customer;

        // Sets up form that displays name and address
        customerDetails.setLayout(new GridLayout(7,2));
        firstName = new JTextField(this.customer.getForename());
        surname = new JTextField(this.customer.getSurname());
        houseNo = new JTextField(this.customer.getAddress().getHouseNum());
        road = new JTextField(this.customer.getAddress().getRoad());
        city = new JTextField(this.customer.getAddress().getCity());
        postcode = new JTextField(this.customer.getAddress().getPostcode());

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

        // Updating details in database
        updateDetails.addActionListener(e -> {
            try {
                this.customer.updateAddress(
                        houseNo.getText(),
                        road.getText(),
                        city.getText(),
                        postcode.getText()
                );
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            this.customer.updateName(
                    firstName.getText(),
                    surname.getText()
            );
            formStatus.setText("Changes saved");
        });

        // Setting up table of customer's orders
        buildOrdersTable();
        orderPanel.setBorder(BorderFactory.createTitledBorder("Your Orders"));

        // Setting up sidebar, allowing deletion of an order
        orderDeletionPanel.setLayout(new BoxLayout(orderDeletionPanel,BoxLayout.Y_AXIS));
        orderDeletionPanel.add(orderSelected);
        orderDeletionPanel.add(deleteOrder);
        deletionErrorMsg.setForeground(Color.red);
        orderDeletionPanel.add(deletionErrorMsg);


        // Displays selected order number in sidebar
        orderDetails.getSelectionModel().addListSelectionListener(e -> {
            int orderID = Integer.parseInt(orderDetails.getValueAt(orderDetails.getSelectedRow(),0).toString());
            orderSelected.setText("Selected order number:  " + orderID);
        });

        // Deletes selected order
        deleteOrder.addActionListener(e -> {
            int orderID = Integer.parseInt(orderDetails.getValueAt(orderDetails.getSelectedRow(),0).toString());
            Order order = Order.getOrder(orderID);
            boolean orderDeleted = false;
            try {
                if (order != null) {
                    orderDeleted = order.deleteOrder();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            if (orderDeleted) {
                deletionErrorMsg.setText("Order deleted");
                orderPanel.remove(orderScrollPane);
                orderPanel.remove(orderDeletionPanel);
                try {
                    buildOrdersTable();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                repaint();
            } else {
                deletionErrorMsg.setText("Order already confirmed");
            }
        });

        this.add(customerDetails);
        this.add(orderPanel);
    }

    /**
     * Creates table of orders
     */
    private void buildOrdersTable() throws SQLException {
        List<Order> orderList = Order.getAllOrderOfACustomer(this.customer.getCustomerID());
        String[] columnNames = {"Order ID", "Date", "Assigned Staff", "Bike Brand", "Bike Name",
                "Handlebar Brand", "Wheel Brand", "Frameset Brand", "Bike Cost", "Order Status"};
        orderDetails = new JTable(new DefaultTableModel(columnNames,0));
        orderScrollPane = new JScrollPane(orderDetails);
        orderScrollPane.setPreferredSize((new Dimension(1000,600)));

        if (!orderList.isEmpty()) {
            orderPanel.add(orderScrollPane);
            orderPanel.add(orderDeletionPanel);

            for (Order order : orderList) {
                String[] details = new String[10];
                Bike bike = Bike.getBike(order.getBikeID());

                details[0] = String.valueOf(order.getOrderID());
                details[1] = order.getDate();
                details[2] = order.getAssignedStaff();
                details[3] = bike.getBrand();
                details[4] = bike.getName();
                details[5] = bike.getHandlebar().getBrand();
                details[6] = bike.getWheels().getBrand();
                details[7] = bike.getFrameSet().getBrand();
                details[8] = "$" + bike.getCost();
                details[9] = order.getStatus();

                DefaultTableModel model = (DefaultTableModel) orderDetails.getModel();
                model.addRow(details);
            }
        }
        else {
            orderPanel.add(new JLabel("No orders have been placed"));
        }
    }
}
