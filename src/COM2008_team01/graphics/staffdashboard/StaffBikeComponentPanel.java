/** Staff dashboard panel
 * @author Weixiang Han
 * @version 1.0
 * @lastUpdated 23/11/22 07:00
 */


package COM2008_team01.graphics.staffdashboard;

import COM2008_team01.models.*;
import COM2008_team01.models.bikeComponents.FrameSet;
import COM2008_team01.models.bikeComponents.Handlebar;
import COM2008_team01.models.bikeComponents.Wheel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class StaffBikeComponentPanel extends JPanel {
    private final JPanel container = new JPanel();

    private JTabbedPane bikeComponentPanes = new JTabbedPane();

    private JPanel frameSetPanel = new JPanel();
    private JPanel handleBarPanel = new JPanel();
    private JPanel wheelPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();

    private JTable frameSetDetails;
    private JTable handleBarDetails;
    private JTable wheelDetails;

    private JScrollPane frameSetScrollPane;
    private JScrollPane handleBarScrollPane;
    private JScrollPane wheelScrollPane;

    private JButton backButton = new JButton("Back");

    protected StaffBikeComponentPanel(Staff staff) throws SQLException {
        CardLayout card = new CardLayout();
        this.setLayout(card);
        container.setLayout(new BorderLayout());

        bikeComponentPanes.addTab("Frames", frameSetPanel);
        bikeComponentPanes.addTab("Handlebars", handleBarPanel);
        bikeComponentPanes.addTab("Wheels", wheelPanel);
        container.add(bikeComponentPanes, BorderLayout.NORTH);

        // Frame Set Table
        List<FrameSet> frameSetList = FrameSet.getAllFrameSets();
        String[] frameColumnNames = {"Quantity", "Serial No", "Brand", "Size (cm)", "Has Shock-absorbers?", "No. Gears", "Cost"};
        frameSetDetails = new JTable(new DefaultTableModel(frameColumnNames,0));
        frameSetScrollPane = new JScrollPane(frameSetDetails);
        frameSetPanel.setLayout(new BorderLayout());

        if (!frameSetList.isEmpty()) {
            frameSetPanel.add(frameSetScrollPane);

            for (FrameSet frameSet : frameSetList) {
                String[] frameColumns = new String[7];

                frameColumns[0] = String.valueOf(frameSet.getQuantity());
                frameColumns[1] = String.valueOf(frameSet.getSerialNo());
                frameColumns[2] = frameSet.getBrand();
                frameColumns[3] = String.valueOf(frameSet.getSize());
                frameColumns[4] = String.valueOf(frameSet.getShockAbsorbers());
                frameColumns[5] = String.valueOf(frameSet.getGears());
                frameColumns[6] = String.valueOf(frameSet.getCost());

                DefaultTableModel frameSetModel = (DefaultTableModel) frameSetDetails.getModel();
                frameSetModel.addRow(frameColumns);
            }
        }

        else {
            frameSetPanel.add(new JLabel("No frameset in the system"));
        }

        frameSetPanel.add(frameSetScrollPane, BorderLayout.CENTER);

        // Handle Bar Table
        List<Handlebar> handleBarList = Handlebar.getAllHandlebars();
        String[] handlebarColumnNames = {"Quantity", "Serial No", "Brand", "Type", "Cost"};
        handleBarDetails = new JTable(new DefaultTableModel(handlebarColumnNames,0));
        handleBarScrollPane = new JScrollPane(handleBarDetails);
        handleBarPanel.setLayout(new BorderLayout());

        if (!handleBarList.isEmpty()) {
            handleBarPanel.add(handleBarScrollPane);

            for (Handlebar handleBar : handleBarList) {
                String[] handleBarColumns = new String[5];

                handleBarColumns[0] = String.valueOf(handleBar.getQuantity());
                handleBarColumns[1] = String.valueOf(handleBar.getSerialNo());
                handleBarColumns[2] = handleBar.getBrand();
                handleBarColumns[3] = handleBar.getType();
                handleBarColumns[4] = String.valueOf(handleBar.getCost());

                DefaultTableModel handleBarModel = (DefaultTableModel) handleBarDetails.getModel();
                handleBarModel.addRow(handleBarColumns);
            }
        }

        else {
            handleBarPanel.add(new JLabel("No handle bars in the system"));
        }

        handleBarPanel.add(handleBarScrollPane, BorderLayout.CENTER);

        // Wheel Table
        List<Wheel> wheelList = Wheel.getAllWheels();
        String[] wheelColumnNames = {"Quantity", "Serial No", "Brand", "Tyre Type", "Brakes Type", "Cost"};
        wheelDetails = new JTable(new DefaultTableModel(wheelColumnNames,0));
        wheelScrollPane = new JScrollPane(wheelDetails);
        wheelPanel.setLayout(new BorderLayout());

        if (!wheelList.isEmpty()) {
            wheelPanel.add(wheelScrollPane);

            for (Wheel wheel : wheelList) {
                String[] wheelColumns = new String[6];

                wheelColumns[0] = String.valueOf(wheel.getQuantity());
                wheelColumns[1] = String.valueOf(wheel.getSerialNo());
                wheelColumns[2] = wheel.getBrand();
                wheelColumns[3] = wheel.getTyre();
                wheelColumns[4] = wheel.getBrakes();
                wheelColumns[5] = String.valueOf(wheel.getCost());

                DefaultTableModel wheelModel = (DefaultTableModel) wheelDetails.getModel();
                wheelModel.addRow(wheelColumns);
            }
        }

        else {
            wheelPanel.add(new JLabel("No wheels in the system"));
        }

        wheelPanel.add(wheelScrollPane, BorderLayout.CENTER);

        backButton.addActionListener(e -> {
            try{
                if (staff != null) {
                    StaffDashboardPanel dashboardPanel = new StaffDashboardPanel(staff);
                    this.add(dashboardPanel,"dashboardPanel");
                    card.show(this,"dashboardPanel");
                }
            }
            catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        buttonPanel.add(backButton);
        container.add(buttonPanel, BorderLayout.SOUTH);

        this.add(container,"container");
        card.show(this,"container");
    }
}
