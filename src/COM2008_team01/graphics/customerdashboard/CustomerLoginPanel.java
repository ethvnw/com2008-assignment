/** Customer login panel - shows forms for getting specific order and viewing all details
 * @author Ethan Watts
 * @version 1.5
 * @lastUpdated 30/11/22 14:56
 */

package COM2008_team01.graphics.customerdashboard;

import COM2008_team01.graphics.HintTextField;
import COM2008_team01.models.Customer;
import COM2008_team01.models.Order;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class CustomerLoginPanel extends JPanel {
    private final JPanel formPanel = new JPanel();

    private final JPanel orderForm = new JPanel();
    private final HintTextField orderNo = new HintTextField("Enter order number...");
    private final JButton modifyOrder = new JButton("Submit");
    private JLabel orderErrorMsg = new JLabel(" ");

    private final JPanel accountForm = new JPanel();
    private final HintTextField firstName = new HintTextField("Enter first name...");
    private final HintTextField surname = new HintTextField("Enter surname...");
    private final HintTextField houseNo = new HintTextField("Enter house number...");
    private final HintTextField postcode = new HintTextField("Enter postcode...");
    private final JButton viewAccount = new JButton("Submit");
    private JLabel customerErrorMsg = new JLabel(" ");

    /**
     * Creates the JPanel showing the forms to view details
     */
    public CustomerLoginPanel() {
        // Setting up cards for other panels called
        CardLayout card = new CardLayout();
        this.setLayout(card);
        this.add(formPanel,"formPanel");

        // Form to view order by order number
        orderForm.setPreferredSize(new Dimension(400,100));
        orderForm.setLayout(new BoxLayout(orderForm,BoxLayout.Y_AXIS));
        orderForm.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        orderForm.setBorder(BorderFactory.createTitledBorder("View an Order"));

        orderForm.add(orderNo);
        orderForm.add(modifyOrder);
        orderErrorMsg.setForeground(Color.red);
        orderForm.add(orderErrorMsg);

        // Calling CustomerOrdersPanel
        modifyOrder.addActionListener(e -> {
            Order order = null;
            try {
                order = Order.getOrder(Integer.parseInt(orderNo.getText()));
                if (order != null) {
                    if (order.getStatus().equals("Pending")) {
                        CustomerOrdersPanel ordersPanel = new CustomerOrdersPanel(order);
                        this.add(ordersPanel,"ordersPanel");
                        card.show(this,"ordersPanel");
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
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        formPanel.add(orderForm);

        // Form to view account details
        accountForm.setPreferredSize(new Dimension(400,200));
        accountForm.setLayout(new BoxLayout(accountForm,BoxLayout.Y_AXIS));
        accountForm.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        accountForm.setBorder(BorderFactory.createTitledBorder("View Your Account"));

        accountForm.add(firstName);
        accountForm.add(surname);
        accountForm.add(houseNo);
        accountForm.add(postcode);
        accountForm.add(viewAccount);
        customerErrorMsg.setForeground(Color.red);
        accountForm.add(customerErrorMsg);

        // Calling CustomerAccountPanel
        viewAccount.addActionListener(e -> {
            Customer customer = null;
            try {
                customer = Customer.getCustomer(firstName.getText(),surname.getText(), houseNo.getText(), postcode.getText());
                if (customer != null) {
                    CustomerAccountPanel accountPanel = new CustomerAccountPanel(customer);
                    this.add(accountPanel,"accountPanel");
                    card.show(this,"accountPanel");
                }
                else {
                    customerErrorMsg.setText("Details do not match a registered customer");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        formPanel.add(accountForm);

        card.show(this,"formPanel");
    }
}
