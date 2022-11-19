/** Customer login panel
 * @author Ethan Watts
 * @version 1.3
 * @lastUpdated 17/11/22 14:54
 */

package assignment.graphics.customerdashboard;

import assignment.graphics.HintTextField;
import assignment.models.Customer;
import assignment.models.Order;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class CustomerLoginPanel extends JPanel {
    private final JPanel buttonPanel = new JPanel();
    private final JPanel orderForm = new JPanel();
    private HintTextField orderNo = new HintTextField("Enter order number...");
    private final JButton modifyOrder = new JButton("Submit");
    private JLabel orderErrorMsg = new JLabel();

    private final JPanel accountForm = new JPanel();
    private HintTextField firstName = new HintTextField("Enter first name...");
    private HintTextField surname = new HintTextField("Enter surname...");
    private HintTextField houseNo = new HintTextField("Enter house number...");
    private HintTextField postcode = new HintTextField("Enter postcode...");
    private final JButton viewAccount = new JButton("Submit");
    private JLabel customerErrorMsg = new JLabel();

    public CustomerLoginPanel() {
        CardLayout panels = new CardLayout();
        this.setLayout(panels);
        this.add(buttonPanel,"buttonPanel");

        // Form to view order by order number
        orderForm.setLayout(new GridLayout(3,1));
        orderForm.setPreferredSize((new Dimension(250,250)));
        orderForm.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        orderForm.setBorder(BorderFactory.createTitledBorder("View an Order"));

        orderNo.setSize(new Dimension(100, 30));
        orderForm.add(orderNo);
        orderForm.add(modifyOrder);
        modifyOrder.setSize(new Dimension(100, 30));
        orderErrorMsg.setForeground(Color.red);
        orderForm.add(orderErrorMsg);

        modifyOrder.addActionListener(e -> {
            Order order = null;
            try {
                order = Order.getOrder(Integer.parseInt(orderNo.getText()));
                if (order != null) {
                    if (order.getStatus().equals("Pending")) {
                        CustomerOrdersPanel ordersPanel = new CustomerOrdersPanel(order);
                        this.add(ordersPanel,"ordersPanel");
                        panels.show(this,"ordersPanel");
                    }
                    else {
                        orderErrorMsg.setText("Order is already confirmed");
                    }
                }
                else {
                    orderErrorMsg.setText("Incorrect order number");
                }
            } catch (NumberFormatException exception) {
                orderErrorMsg.setText("Enter only numbers");
                exception.printStackTrace();
            }
        });

        buttonPanel.add(orderForm);

        // Form to view account details
        accountForm.setLayout(new GridLayout(6,1));
        accountForm.setPreferredSize((new Dimension(250,250)));
        accountForm.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        accountForm.setBorder(BorderFactory.createTitledBorder("View Your Account"));

        accountForm.add(firstName);
        accountForm.add(surname);
        accountForm.add(houseNo);
        accountForm.add(postcode);
        accountForm.add(viewAccount);
        customerErrorMsg.setForeground(Color.red);
        accountForm.add(customerErrorMsg);

        viewAccount.addActionListener(e -> {
            Customer customer = null;
            try {
                customer = Customer.getCustomer(firstName.getText(),surname.getText(), houseNo.getText(), postcode.getText());
                if (customer != null) {
                    CustomerAccountPanel accountPanel = new CustomerAccountPanel(customer);
                    this.add(accountPanel,"accountPanel");
                    panels.show(this,"accountPanel");
                }
                else {
                    customerErrorMsg.setText("Details do not match a registered customer");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        buttonPanel.add(accountForm);

        panels.show(this,"buttonPanel");
    }
}
