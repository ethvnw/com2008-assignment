/** Customer dashboard panel
 * @author
 * @version 1.0
 * @lastUpdated
 */

package assignment.graphics.customerdashboard;

import javax.swing.*;
import java.awt.*;

public class CustomerDashboardPanel extends JPanel {
    private final JPanel buttonPanel = new JPanel();
    private final JPanel orderForm = new JPanel();
    private JTextField orderNo = new JTextField("Enter order number...",20);
    private final JButton modifyOrder = new JButton("Submit");

    private final JPanel accountForm = new JPanel();
    private JTextField firstName = new JTextField("Enter first name...",20);
    private JTextField surname = new JTextField("Enter surname...");
    private JTextField houseNo = new JTextField("Enter house number...");
    private JTextField postcode = new JTextField("Enter postcode...");
    private final JButton viewAccount = new JButton("Submit");


    public CustomerDashboardPanel() {
        CardLayout panels = new CardLayout();
        this.setLayout(panels);

        CustomerOrdersPanel ordersPanel = new CustomerOrdersPanel();
        this.add(ordersPanel,"ordersPanel");
        CustomerAccountPanel accountPanel = new CustomerAccountPanel();
        this.add(accountPanel,"accountPanel");
        this.add(buttonPanel,"buttonPanel");

        // Form to view order by order number
        orderForm.setLayout(new GridLayout(2,1));
        orderForm.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        orderForm.setBorder(BorderFactory.createTitledBorder("View an Order"));

        orderForm.add(orderNo);
        orderForm.add(modifyOrder);
        modifyOrder.addActionListener(e -> {
            panels.show(this,"ordersPanel");
        });

        buttonPanel.add(orderForm);

        // Form to view account details
        accountForm.setLayout(new GridLayout(5,1));
        accountForm.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        accountForm.setBorder(BorderFactory.createTitledBorder("View Your Account"));

        accountForm.add(firstName);
        accountForm.add(surname);
        accountForm.add(houseNo);
        accountForm.add(postcode);
        accountForm.add(viewAccount);
        viewAccount.addActionListener(e -> {
            panels.show(this,"accountPanel");
        });

        buttonPanel.add(accountForm);


        panels.show(this,"buttonPanel");
    }
}
