/**
 * Staff dashboard panel
 *
 * @author Weixiang Han
 * @version 1.0
 * @lastUpdated 19/11/22 16:00
 */

package assignment.graphics.staffdashboard;

import assignment.graphics.HintTextField;
import assignment.models.Staff;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class StaffLoginPanel extends JPanel {
    private final JPanel buttonPanel = new JPanel();

    private final JPanel accountForm = new JPanel();
    private HintTextField username = new HintTextField("Enter username...");
    private HintTextField password = new HintTextField("Enter password...");
    private final JButton viewAccount = new JButton("Submit");
    private JLabel staffErrorMsg = new JLabel();

    public StaffLoginPanel() {
        CardLayout panels = new CardLayout();
        this.setLayout(panels);
        this.add(buttonPanel,"buttonPanel");

        accountForm.setLayout(new GridLayout(4,1));
        accountForm.setPreferredSize((new Dimension(250,250)));
        accountForm.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        accountForm.setBorder(BorderFactory.createTitledBorder("View Your Account"));

        accountForm.add(username);
        accountForm.add(password);
        accountForm.add(viewAccount);
        staffErrorMsg.setForeground(Color.red);
        accountForm.add(staffErrorMsg);

        viewAccount.addActionListener(e -> {
            Staff staff = null;
            try {
                staff = Staff.getStaff(username.getText(), password.getText());
                if (staff != null) {
                    StaffDashboardPanel dashboardPanel = new StaffDashboardPanel(staff);
                    this.add(dashboardPanel,"dashboardPanel");
                    panels.show(this,"dashboardPanel");
                }
                else {
                    staffErrorMsg.setText("Details do not match a registered staff");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }
}
