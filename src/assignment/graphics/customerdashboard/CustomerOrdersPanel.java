/** Panel that shows recent orders of a customer.
 * @author Ethan Watts
 * @version 1.0
 * @lastUpdated 16-11-2022 14:58
 */

package assignment.graphics.customerdashboard;

import assignment.models.Bike;
import assignment.models.Order;

import javax.swing.*;
import java.awt.*;

public class CustomerOrdersPanel extends JPanel {
    private JTextField orderNum = new JTextField();
    private JTextField orderDate = new JTextField();
    private JTextField orderStatus = new JTextField();
    private JPanel orderDetails = new JPanel();

    private JTextField bikeBrand = new JTextField();
    private JTextField bikeName = new JTextField();
    private JTextField bikeFrame = new JTextField();
    private JTextField bikeHandlebar = new JTextField();
    private JTextField bikeWheels = new JTextField();
    private JTextField bikeCost = new JTextField();
    private JPanel bikeDetails = new JPanel();


    protected CustomerOrdersPanel(Order order) {
        orderNum.setText(String.valueOf(order.getOrderID()));
        orderDate.setText(String.valueOf(order.getDate()));
        orderStatus.setText(String.valueOf(order.getStatus()));

        orderDetails.setLayout(new GridLayout(3,2));
        orderDetails.add(new JLabel("Order Number"));
        orderDetails.add(orderNum);
        orderDetails.add(new JLabel("Order Date"));
        orderDetails.add(orderDate);
        orderDetails.add(new JLabel("Order Status"));
        orderDetails.add(orderStatus);

        Bike bike = Bike.getBike(order.getBikeID());
        bikeBrand.setText(bike.getBrand());
        bikeName.setText(bike.getName());
        bikeFrame.setText(bike.getFrameSet().getBrand());
        bikeWheels.setText(bike.getWheels().getBrand());
        bikeHandlebar.setText(bike.getHandlebar().getBrand());
        bikeCost.setText("£ " + String.valueOf(bike.getCost()));

        bikeDetails.setLayout(new GridLayout(6,2));
        bikeDetails.add(new JLabel("Bike Brand"));
        bikeDetails.add(bikeBrand);
        bikeDetails.add(new JLabel("Bike Name"));
        bikeDetails.add(bikeName);
        bikeDetails.add(new JLabel("Frame brand"));
        bikeDetails.add(bikeFrame);
        bikeDetails.add(new JLabel("Wheel brand"));
        bikeDetails.add(bikeWheels);
        bikeDetails.add(new JLabel("Handlebar brand"));
        bikeDetails.add(bikeHandlebar);
        bikeDetails.add(new JLabel("Cost of bike"));
        bikeDetails.add(bikeCost);

        this.add(orderDetails);
        this.add(bikeDetails);
    }
}
