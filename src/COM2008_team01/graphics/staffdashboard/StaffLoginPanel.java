/**
 * Staff dashboard panel
 *
 * @author Weixiang Han
 * @version 1.0
 */

package COM2008_team01.graphics.staffdashboard;

import COM2008_team01.graphics.HintTextField;
import COM2008_team01.models.Staff;

import javax.swing.*;
import java.awt.*;

public class StaffLoginPanel extends JPanel {
    private final JPanel buttonPanel = new JPanel();

    private final JPanel accountForm = new JPanel();
    private HintTextField username = new HintTextField(" Enter username...");
    private JPasswordField password = new JPasswordField();
    private final JButton viewAccount = new JButton("Submit");
    private JLabel staffErrorMsg = new JLabel();

    public StaffLoginPanel() {
        CardLayout card = new CardLayout();
        this.setLayout(card);
        this.add(buttonPanel,"buttonPanel");

        accountForm.setPreferredSize((new Dimension(400,200)));
        accountForm.setLayout(new BoxLayout(accountForm,BoxLayout.Y_AXIS));
        accountForm.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        accountForm.setBorder(BorderFactory.createTitledBorder("View Your Account"));

        accountForm.add(username);
        accountForm.add(password);
        accountForm.add(viewAccount);
        staffErrorMsg.setForeground(Color.red);
        accountForm.add(staffErrorMsg);

        viewAccount.addActionListener(e -> {
            try {
                Staff staff = new Staff(username.getText(), password.getText());

                if (staff.login() != null) {
                    StaffDashboardPanel dashboardPanel = new StaffDashboardPanel(staff);
                    this.add(dashboardPanel,"dashboardPanel");
                    card.show(this,"dashboardPanel");
                }
                else {
                    staffErrorMsg.setText("Details do not match a registered staff");
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        buttonPanel.add(accountForm);

        card.show(this,"buttonPanel");
    }
}
