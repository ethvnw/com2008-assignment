/** Customer login panel
 * @author Ethan Watts
 * @version 1.3
 * @lastUpdated 17/11/22 14:54
 */

package assignment.graphics.customerdashboard;

import assignment.models.Customer;
import assignment.models.Order;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class CustomerLoginPanel extends JPanel {
    private final JPanel buttonPanel = new JPanel();
    private final JPanel orderForm = new JPanel();
    private JTextField orderNo = new JTextField("Enter order number...",20);
    private final JButton modifyOrder = new JButton("Submit");
    private JLabel orderErrorMsg = new JLabel();

    private final JPanel accountForm = new JPanel();
    private JTextField firstName = new JTextField("Enter first name...",20);
    private JTextField surname = new JTextField("Enter surname...");
    private JTextField houseNo = new JTextField("Enter house number...");
    private JTextField postcode = new JTextField("Enter postcode...");
    private final JButton viewAccount = new JButton("Submit");
    private JLabel customerErrorMsg = new JLabel();

    public CustomerLoginPanel() {
        CardLayout panels = new CardLayout();
        this.setLayout(panels);
        this.add(buttonPanel,"buttonPanel");

        // Form to view order by order number
        orderForm.setLayout(new GridLayout(3,1));
        orderForm.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        orderForm.setBorder(BorderFactory.createTitledBorder("View an Order"));

        orderForm.add(orderNo);
        orderForm.add(modifyOrder);
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
