/** Panel that shows recent orders of a customer.
 * @author Ethan Watts
 * @version 1.0
 * @lastUpdated 16-11-2022 14:58
 */

package assignment.graphics.customerdashboard;

import assignment.models.Bike;
import assignment.models.Order;

import javax.swing.*;

public class CustomerOrdersPanel extends JPanel {
    private final JLabel orderNum = new JLabel();
    private final JLabel orderDate = new JLabel();
    private final JLabel orderStatus = new JLabel();

    private final JLabel bikeBrand = new JLabel();
    private final JLabel bikeName = new JLabel();
    private final JLabel bikeFrame = new JLabel();
    private final JLabel bikeHandlebar = new JLabel();
    private final JLabel bikeWheels = new JLabel();
    private final JLabel bikeCost = new JLabel();

    private JTextArea orderDetails = new JTextArea();

    protected CustomerOrdersPanel(Order order) {
        this.add(orderDetails);

        orderNum.setText(String.valueOf(order.getOrderID()));
        orderDate.setText(String.valueOf(order.getDate()));
        orderStatus.setText(String.valueOf(order.getStatus()));

        orderDetails.add(orderNum);
        orderDetails.add(orderDate);
        orderDetails.add(orderStatus);

        Bike bike = Bike.getBike(order.getBikeID());
        bikeBrand.setText(bike.getBrand());
        bikeName.setText(bike.getName());
        bikeFrame.setText(bike.getFrameSet().getBrand());
        bikeWheels.setText(bike.getWheels().getBrand());
        bikeHandlebar.setText(bike.getHandlebar().getBrand());
        bikeCost.setText(String.valueOf(bike.getCost()));

        orderDetails.add(bikeBrand);
        orderDetails.add(bikeName);
        orderDetails.add(bikeFrame);
        orderDetails.add(bikeWheels);
        orderDetails.add(bikeHandlebar);
        orderDetails.add(bikeCost);
    }

}
