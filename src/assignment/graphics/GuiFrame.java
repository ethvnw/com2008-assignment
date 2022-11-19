/** GUI Frame creation handler.
 * @author Ethan Watts
 * @version 1.1
 * @lastUpdated 14-11-2022 16:39
 */

package assignment.graphics;

import assignment.graphics.customerdashboard.CustomerLoginPanel;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class GuiFrame extends JFrame {
    @Serial
    private static final long serialVersionUID = 1L;

    public GuiFrame() {
        // Set up the JFrame
        super("Build a Bike");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(screenSize.width / 2, screenSize.height / 2);
        this.setLayout(new BorderLayout(0,10));

        // Top navigation panel - always displayed
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBorder(BorderFactory.createMatteBorder(0,0,3,0, Color.gray));
        JLabel title = new JLabel("Build a Bike");
        title.setFont(new Font("Sans-Serif", Font.BOLD, 16));
        buttonPanel.setOpaque(true);
        buttonPanel.add(title);

        JButton customerLoginButton = new JButton("Customer Login");
        JButton staffLoginButton = new JButton("Staff Login");
        buttonPanel.add(customerLoginButton);
        buttonPanel.add(staffLoginButton);

        this.add(buttonPanel);

        // Parent panel that all other panels are a part of
        JPanel mainPanel = new JPanel();
        CardLayout panels = new CardLayout();
        mainPanel.setLayout(panels);
        this.add(mainPanel);

        // Panels for each page
        CustomerFormPanel cForm = new CustomerFormPanel();
        mainPanel.add(cForm);
        BikeCreationPanel bikeCreation = new BikeCreationPanel();
        mainPanel.add(bikeCreation);
        StaffDashboardPanel staffDashboard = new StaffDashboardPanel();
        mainPanel.add(staffDashboard);

        // TESTING /////////////////////////////////////////////////////////////
        JPanel testPanel = new JPanel();
        testPanel.add(new JTextField("Test Test Test Test Test Test Test Test "));
        mainPanel.add(testPanel, "tPanel");

        JButton testButton = new JButton("Test");
        buttonPanel.add(testButton);

        testButton.addActionListener(e -> panels.next(mainPanel));
        ////////////////////////////////////////////////////////////////////////

        // Button event listeners
        customerLoginButton.addActionListener(e -> {
            CustomerLoginPanel customerLogin = new CustomerLoginPanel();
            mainPanel.add(customerLogin,"customerLogin");
            panels.show(mainPanel,"customerLogin");
        });
        staffLoginButton.addActionListener(e -> {
            StaffLoginPanel staffLogin = new StaffLoginPanel();
            mainPanel.add(staffLogin,"staffLogin");
            panels.show(mainPanel,"staffLogin");
        });

        getContentPane().add(mainPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.NORTH);
        ////////////////////////////////////////////////////////////////////////////
    }
}
