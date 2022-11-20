/** Staff dashboard panel
 * @author Weixiang Han
 * @version 1.0
 * @lastUpdated 19/11/22 16:00
 */

package assignment.graphics.staffdashboard;

import assignment.models.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class StaffDashboardPanel extends JPanel {
    private JTable orderDetails;
    private JTable customerDetails;
    private JScrollPane orderScrollPane;
    private JScrollPane customerScrollPane;

    protected StaffDashboardPanel(Staff staff) {
        // Customer Table
        String customerQuery = "SELECT * FROM team001.customer;";
        List<Customer> customerList = Customer.getCustomers(customerQuery);
        String[] customerColumnNames = {"Customer ID", "Forename", "Surname", "House No", "Road Name",
                "City Name", "Postcode"};
        customerDetails = new JTable(new DefaultTableModel(customerColumnNames,0));
        customerScrollPane = new JScrollPane(orderDetails);
        customerScrollPane.setPreferredSize((new Dimension(1000,500)));
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

            DefaultTableModel model = (DefaultTableModel) customerDetails.getModel();
            model.addRow(customerColumns);
        }

        // Order Table
        String orderQuery = "SELECT * FROM team001.order;";
        List<Order> orderList = Order.getOrders(orderQuery);
        String[] orderColumnNames = {"Order ID", "Date", "Assigned Staff", "Bike Brand", "Bike Name",
                "Handlebar Brand", "Wheel Brand", "Frameset Brand", "Bike Cost", "Order Status"};
        orderDetails = new JTable(new DefaultTableModel(orderColumnNames,0));
        orderScrollPane = new JScrollPane(orderDetails);
        orderScrollPane.setPreferredSize((new Dimension(1000,500)));
        orderScrollPane.setBorder(BorderFactory.createTitledBorder("All Orders"));

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

        this.add(customerScrollPane, BorderLayout.WEST);
        this.add(orderScrollPane, BorderLayout.EAST);

    }


    public StaffDashboardPanel() {

    }
}
