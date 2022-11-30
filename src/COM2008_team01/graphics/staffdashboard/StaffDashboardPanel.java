/** Staff dashboard panel
 * @author Weixiang Han
 * @version 1.0
 * @lastUpdated 19/11/22 16:00
 */

package COM2008_team01.graphics.staffdashboard;

import COM2008_team01.models.*;
import COM2008_team01.utilities.Cookies;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StaffDashboardPanel extends JPanel {
    private final JPanel homePanel = new JPanel();
    private final JPanel buttonPanel = new JPanel();
    private JTabbedPane tabbedPane = new JTabbedPane();

    private JPanel orderPanel = new JPanel();
    private JPanel customerPanel = new JPanel();

    private JTable orderDetails;
    private JTable customerDetails;

    private JScrollPane orderScrollPane;
    private JScrollPane customerScrollPane;

    private final JButton viewBikeComponents = new JButton("View Bike Components");

    public StaffDashboardPanel(Staff staff) {
        CardLayout card = new CardLayout();
        this.setLayout(card);
        homePanel.setLayout(new BorderLayout());

        // Welcome text for user
        JLabel title = new JLabel("Welcome " + String.valueOf(staff.getUsername()));
        title.setFont(new Font("Sans-Serif", Font.PLAIN, 16));

        // Log out button
        JButton staffLogOutButton = new JButton("Log Out");
        staffLogOutButton.addActionListener(e -> {
            try {
                if (Staff.logout()) {
                    StaffLoginPanel staffLogin = new StaffLoginPanel();
                    this.add(staffLogin,"staffLogin");
                    card.show(this,"staffLogin");
                    Cookies.loggedInStaff = null;
                }
            }
            catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        // View Bike Components
        viewBikeComponents.addActionListener(e -> {
            try{
                if (staff != null){
                    StaffBikeComponentPanel bikePanel = new StaffBikeComponentPanel(staff);
                    this.add(bikePanel,"bikePanel");
                    card.show(this,"bikePanel");
                }
            }
            catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        buttonPanel.add(title);
        buttonPanel.add(staffLogOutButton);
        buttonPanel.add(viewBikeComponents);
        homePanel.add(buttonPanel, BorderLayout.NORTH);

        // Adding customer and order tables in a tabbed pane
        tabbedPane.addTab("All Customers", customerPanel);
        tabbedPane.addTab("All Orders", orderPanel);
        homePanel.add(tabbedPane, BorderLayout.SOUTH);

        // Customer Table
        List<Customer> customerList = Customer.getAllCustomers();
        String[] customerColumnNames = {"Customer ID", "Forename", "Surname", "House No", "Road Name",
                "City Name", "Postcode"};
        customerDetails = new JTable(new DefaultTableModel(customerColumnNames,0));
        customerScrollPane = new JScrollPane(customerDetails);
        customerPanel.setLayout(new BorderLayout());

        if (!customerList.isEmpty()) {
            customerPanel.add(customerScrollPane);

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
        }

        else {
            customerPanel.add(new JLabel("No customers in the system"));
        }

        customerPanel.add(customerScrollPane, BorderLayout.CENTER);

        // Order Table
        String orderQuery = "SELECT * FROM team001.order;";
        List<Order> orderList = Order.getOrders(orderQuery);
        String[] orderColumnNames = {"Order ID", "Date", "Assigned Staff", "Bike Brand", "Bike Name",
                "Handlebar Brand", "Wheel Brand", "Frameset Brand", "Bike Cost", "Order Status"};
        orderDetails = new JTable(new DefaultTableModel(orderColumnNames,0));
        orderScrollPane = new JScrollPane(orderDetails);
        orderPanel.setLayout(new BorderLayout());

        if (!orderList.isEmpty()) {
            orderPanel.add(orderScrollPane);

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
                orderColumns[8] = "$" + bike.getCost();
                orderColumns[9] = order.getStatus();

                DefaultTableModel orderModel = (DefaultTableModel) orderDetails.getModel();
                orderModel.addRow(orderColumns);
            }
        }

        else {
            orderPanel.add(new JLabel("No orders have been placed"));
        }

        orderPanel.add(orderScrollPane);

        this.add(homePanel,"homePanel");
        card.show(this,"homePanel");
    }
}
