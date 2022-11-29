/** GUI Frame creation handler.
 * @author Ethan Watts
 * @version 1.1
 * @lastUpdated 14-11-2022 16:39
 */

package COM2008_team01.graphics;

import COM2008_team01.graphics.bikecreation.BikeCreationPanel;
import COM2008_team01.graphics.customerdashboard.CustomerLoginPanel;
import COM2008_team01.graphics.staffdashboard.StaffDashboardPanel;
import COM2008_team01.graphics.staffdashboard.StaffLoginPanel;
import COM2008_team01.utilities.Cookies;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class GuiFrame extends JFrame {
    @Serial
    private static final long serialVersionUID = 1L;

    private final JLabel title = new JLabel("Build a Bike");
    private final JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private final JButton homeButton = new JButton("Home");
    private final JButton customerLoginButton = new JButton("Customer Login");
    private final JButton staffLoginButton = new JButton("Staff Login");

    private final JPanel mainPanel = new JPanel();

    public GuiFrame() {
        // Set up the JFrame
        super("Build a Bike");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(screenSize.width, screenSize.height);
        this.getContentPane().setLayout(new GridBagLayout());

        // Top navigation panel - always displayed
        buttonPanel.setBorder(BorderFactory.createMatteBorder(0,0,3,0, Color.gray));
        title.setFont(new Font("Sans-Serif", Font.BOLD, 16));
        buttonPanel.setOpaque(true);
        buttonPanel.setBackground(Color.white);
        buttonPanel.add(title);
        buttonPanel.add(homeButton);
        buttonPanel.add(customerLoginButton);
        buttonPanel.add(staffLoginButton);

        // Parent panel that all other panels are a part of
        CardLayout panels = new CardLayout();
        mainPanel.setLayout(panels);

        // Panels for each page
        BikeCreationPanel bikeCreation = new BikeCreationPanel();
        mainPanel.add(bikeCreation,"bikeCreation");

        // Button event listeners
        homeButton.addActionListener(e-> {
            panels.show(mainPanel,"bikeCreation");
        });
        customerLoginButton.addActionListener(e -> {
            CustomerLoginPanel customerLogin = new CustomerLoginPanel();
            mainPanel.add(customerLogin,"customerLogin");
            panels.show(mainPanel,"customerLogin");
        });
        staffLoginButton.addActionListener(e -> {
            if (Cookies.loggedInStaff == null) {
                StaffLoginPanel staffLogin = new StaffLoginPanel();
                mainPanel.add(staffLogin,"staffLogin");
                panels.show(mainPanel,"staffLogin");
            } else {
                StaffDashboardPanel staffDashboardPanel = new StaffDashboardPanel(Cookies.loggedInStaff);
                mainPanel.add(staffDashboardPanel, "staffDashboardPanel");
                panels.show(mainPanel,"staffDashboardPanel");
            }
        });

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.gridx = 0;
        constraints.gridy = 0;
        getContentPane().add(buttonPanel,constraints);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        getContentPane().add(mainPanel,constraints);
        ////////////////////////////////////////////////////////////////////////////
    }
}
