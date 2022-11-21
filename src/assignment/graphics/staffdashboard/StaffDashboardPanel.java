/** Staff dashboard panel
 * @author Weixiang Han
 * @version 1.0
 * @lastUpdated 19/11/22 16:00
 */

package assignment.graphics.staffdashboard;

import assignment.graphics.customerdashboard.CustomerAccountPanel;
import assignment.models.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class StaffDashboardPanel extends JPanel {
    private final JPanel buttonPanel = new JPanel();
    private JTable orderDetails;
    private JTable customerDetails;
    private JScrollPane orderScrollPane;
    private JScrollPane customerScrollPane;

    protected StaffDashboardPanel(Staff staff) {
        CardLayout panels = new CardLayout();
        this.setLayout(panels);
        this.add(buttonPanel,"buttonPanel");

        // Customer Table
        String customerQuery = "SELECT * FROM team001.customer;";
        List<Customer> customerList = Customer.getCustomers(customerQuery);
        String[] customerColumnNames = {"Customer ID", "Forename", "Surname", "House No", "Road Name",
                "City Name", "Postcode"};
        customerDetails = new JTable(new DefaultTableModel(customerColumnNames,0));
        customerScrollPane = new JScrollPane(customerDetails);
        customerScrollPane.setPreferredSize((new Dimension(1000,300)));
        customerScrollPane.setBorder(BorderFactory.createTitledBorder("All Customers"));

        for (Customer customer : customerList) {
            String[] customerColumns = new String[7];

            customerColumns[0] = String.valueOf(customer.getCustomerID());
            customerColumns[1] = customer.getForename();
            customerColumns[2] = customer.getSurname();
            customerColumns[3] = customer.getAddress().getHouseNum();
            customerColumns[4] = customer.getAddress().getRoad();
            customerColumns[5] = customer.getAddress().getCity();
            customerColumns[6] = customer.getAddress().getPostcode();

            DefaultTableModel customerModel = (DefaultTableModel) customerDetails.getModel();
            customerModel.addRow(customerColumns);
        }

        // Order Table
        String orderQuery = "SELECT * FROM team001.order;";
        List<Order> orderList = Order.getOrders(orderQuery);
        String[] orderColumnNames = {"Order ID", "Date", "Assigned Staff", "Bike Brand", "Bike Name",
                "Handlebar Brand", "Wheel Brand", "Frameset Brand", "Bike Cost", "Order Status"};
        orderDetails = new JTable(new DefaultTableModel(orderColumnNames,0));
        orderScrollPane = new JScrollPane(orderDetails);
        orderScrollPane.setPreferredSize((new Dimension(1000,300)));
        orderScrollPane.setBorder(BorderFactory.createTitledBorder("All Orders"));

        for (Order order : orderList) {
            String[] orderColumns = new String[10];
            Bike bike = Bike.getBike(order.getBikeID());

            orderColumns[0] = String.valueOf(order.getOrderID());
            orderColumns[1] = order.getDate();
            orderColumns[2] = order.getAssigned_Staff();
            orderColumns[3] = bike.getBrand();
            orderColumns[4] = bike.getName();
            orderColumns[5] = bike.getHandlebar().getBrand();
            orderColumns[6] = bike.getWheels().getBrand();
            orderColumns[7] = bike.getFrameSet().getBrand();
            orderColumns[8] = "£" + bike.getCost();
            orderColumns[9] = order.getStatus();

            DefaultTableModel orderModel = (DefaultTableModel) orderDetails.getModel();
            orderModel.addRow(orderColumns);
        }

        //JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel title = new JLabel("Welcome " + String.valueOf(staff.getUsername()));
        title.setFont(new Font("Sans-Serif", Font.PLAIN, 16));
        buttonPanel.add(title);

        //buttonPanel.setBorder(BorderFactory.createMatteBorder(0,0,3,0, Color.gray));
        JButton staffLogOutButton = new JButton("Log Out");
        buttonPanel.add(staffLogOutButton);

        staffLogOutButton.addActionListener(e -> {
            try {

                if (Staff.logout()) {
                    StaffLoginPanel staffLogin = new StaffLoginPanel();
                    this.add(staffLogin,"staffLogin");
                    panels.show(this,"staffLogin");
                }

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

//        this.add(buttonPanel, BorderLayout.PAGE_START);
//        this.add(customerScrollPane, BorderLayout.WEST);
//        this.add(orderScrollPane, BorderLayout.EAST);

        buttonPanel.add(customerScrollPane, BorderLayout.WEST);
        buttonPanel.add(orderScrollPane, BorderLayout.EAST);

        panels.show(this,"buttonPanel");

    }


    public StaffDashboardPanel() {

    }
}
