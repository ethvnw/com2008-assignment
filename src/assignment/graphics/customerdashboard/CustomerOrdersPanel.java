/** Panel that shows recent orders of a customer.
 * @author Ethan Watts
 * @version 1.2
 * @lastUpdated 18-11-2022 15:58
 */

package assignment.graphics.customerdashboard;

import assignment.models.Bike;
import assignment.models.Order;

import javax.swing.*;
import java.awt.*;

public class CustomerOrdersPanel extends JPanel {
    private JTable orderDetails;
    private JTable bikeDetails;
    private JScrollPane orderScrollPane;
    private JScrollPane bikeScrollPane;

    protected CustomerOrdersPanel(Order order) {
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

        JButton deleteOrder = new JButton("Delete Order");
        deleteOrder.addActionListener(e -> {
//            order.delete();
            this.setVisible(false);
        });


        this.add(orderScrollPane,BorderLayout.EAST);
        this.add(bikeScrollPane,BorderLayout.WEST);
        this.add(deleteOrder, BorderLayout.SOUTH);
    }
}
