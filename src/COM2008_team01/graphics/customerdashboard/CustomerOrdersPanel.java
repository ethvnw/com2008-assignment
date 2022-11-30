/** Panel that shows recent orders of a customer
 * @author Ethan Watts
 * @version 1.4
 * @lastUpdated 30/11/22 15:58
 */

package COM2008_team01.graphics.customerdashboard;

import COM2008_team01.models.Bike;
import COM2008_team01.models.Order;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class CustomerOrdersPanel extends JPanel {
    private JTable orderDetails;
    private JTable bikeDetails;
    private JScrollPane orderScrollPane;
    private JScrollPane bikeScrollPane;

    /**
     * Creates the panel showing the details of an order
     * @param order the order to view the details of
     */
    protected CustomerOrdersPanel(Order order) throws SQLException {
        // Creating table to show order
        String[] orderColumnNames = {"Order ID", "Date", "Status"};
        String[] orderInfo = {String.valueOf(order.getOrderID()), String.valueOf(order.getDate()), String.valueOf(order.getStatus())};
        String[][] orderTableData = {orderInfo};

        orderDetails = new JTable(orderTableData, orderColumnNames);
        orderScrollPane = new JScrollPane(orderDetails);
        orderScrollPane.setBorder(BorderFactory.createTitledBorder("Order Details"));

        Bike bike = Bike.getBike(order.getBikeID());
        String[] bikeColumnNames = {"Brand", "Bike Name", "Frameset", "Wheels", "Handlebar", "Cost"};
        String[] bikeInfo = {bike.getBrand(), bike.getName(), bike.getFrameSet().getBrand(),
                bike.getWheels().getBrand(), bike.getHandlebar().getBrand(), "£" + String.valueOf(bike.getCost())};
        String[][] bikeTableData = {bikeInfo};

        bikeDetails = new JTable(bikeTableData, bikeColumnNames);
        bikeScrollPane = new JScrollPane(bikeDetails);
        bikeScrollPane.setBorder(BorderFactory.createTitledBorder("Bike Details"));

        // Deleting order and returning to CustomerLoginPanel
        JButton deleteOrder = new JButton("Delete Order");
        deleteOrder.addActionListener(e -> {
            try {
                order.deleteOrder();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            this.setVisible(false);
        });


        this.add(orderScrollPane,BorderLayout.EAST);
        this.add(bikeScrollPane,BorderLayout.WEST);
        this.add(deleteOrder, BorderLayout.SOUTH);
    }
}
