/** Staff dashboard panel
 * @author Weixiang Han
 * @version 1.0
 * @lastUpdated 23/11/22 07:00
 */


package COM2008_team01.graphics.staffdashboard;

import COM2008_team01.graphics.HintTextField;
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

    private JPanel addFrameSetPanel = new JPanel();
    private JPanel addHandleBarPanel = new JPanel();
    private JPanel addWheelPanel = new JPanel();

    private JTable frameSetDetails;
    private JTable handleBarDetails;
    private JTable wheelDetails;

    private JScrollPane frameSetScrollPane;
    private JScrollPane handleBarScrollPane;
    private JScrollPane wheelScrollPane;

    private JLabel addFrameSet = new JLabel("Add Frame Set:");
    private JLabel addHandleBar = new JLabel("Add Handle Bar:");
    private JLabel addWheel = new JLabel("Add Wheel:");

    private JLabel frameErrorMsg = new JLabel();
    private JLabel handleBarErrorMsg = new JLabel();
    private JLabel wheelErrorMsg = new JLabel();

    private HintTextField frameSetQuantity = new HintTextField(" Quantity");
    private HintTextField frameSetSerial = new HintTextField(" Serial No");
    private HintTextField frameSetBrand = new HintTextField(" Brand");
    private HintTextField frameSetSize = new HintTextField(" Size");
    private HintTextField frameSetShockAb = new HintTextField(" Shock Ab");
    private HintTextField frameSetGear = new HintTextField(" Gear");
    private HintTextField frameSetCost = new HintTextField(" Cost");

    private HintTextField handleBarQuantity = new HintTextField(" Quantity");
    private HintTextField handleBarSerial = new HintTextField(" Serial No");
    private HintTextField handleBarBrand = new HintTextField(" Brand");
    private HintTextField handleBarType = new HintTextField(" Type");
    private HintTextField handleBarCost = new HintTextField(" Cost");

    private HintTextField wheelQuantity = new HintTextField(" Quantity");
    private HintTextField wheelSerial = new HintTextField(" Serial No");
    private HintTextField wheelBrand = new HintTextField(" Brand");
    private HintTextField wheelTyreType = new HintTextField(" Tyre Type");
    private HintTextField wheelBrakesType = new HintTextField(" Brakes Type");
    private HintTextField wheelCost = new HintTextField(" Cost");

    private JButton backButton = new JButton("Back");
    private JButton saveFrameSetButton = new JButton("Save");
    private JButton saveHandleBarButton = new JButton("Save");
    private JButton saveWheelButton = new JButton("Save");

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

        // Panel for staff to add frame set product
        addFrameSetPanel.add(addFrameSet);

        // Enter frame set quantity
        frameSetQuantity.setPreferredSize(new Dimension(100, 25));
        addFrameSetPanel.add(frameSetQuantity);

        // Enter frame set serial number
        frameSetSerial.setPreferredSize(new Dimension(100, 25));
        addFrameSetPanel.add(frameSetSerial);

        // Enter frame set brand
        frameSetBrand.setPreferredSize(new Dimension(100, 25));
        addFrameSetPanel.add(frameSetBrand);

        // Enter frame set size
        frameSetSize.setPreferredSize(new Dimension(100, 25));
        addFrameSetPanel.add(frameSetSize);

        // Enter frame set shock absorber
        frameSetShockAb.setPreferredSize(new Dimension(100, 25));
        addFrameSetPanel.add(frameSetShockAb);

        // Enter frame set gear
        frameSetGear.setPreferredSize(new Dimension(100, 25));
        addFrameSetPanel.add(frameSetGear);

        // Enter frame set cost
        frameSetCost.setPreferredSize(new Dimension(100, 25));
        addFrameSetPanel.add(frameSetCost);

        // Save the details entered by staff
        frameErrorMsg.setForeground(Color.red);
        addFrameSetPanel.add(frameErrorMsg);
        saveFrameSetButton.addActionListener(e->{
            try{
                FrameSet fm = new FrameSet(Integer.parseInt(frameSetSerial.getText()),
                        frameSetBrand.getText(),
                        Double.parseDouble(frameSetSize.getText()),
                        Integer.parseInt(frameSetShockAb.getText()),
                        Integer.parseInt(frameSetGear.getText()),
                        Integer.parseInt(frameSetQuantity.getText()),
                        Double.parseDouble(frameSetCost.getText()));

                if (fm.createFrameSet()){
                    StaffBikeComponentPanel bikePanel = new StaffBikeComponentPanel(staff);
                    this.add(bikePanel,"bikePanel");
                    card.show(this,"bikePanel");
                }
                else{
                    frameErrorMsg.setText("Frame Set exist, Please check your input");
                }
            }
            catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        addFrameSetPanel.add(saveFrameSetButton);

        frameSetPanel.add(addFrameSetPanel, BorderLayout.SOUTH);

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

        // Panel for staff to add handle bar product
        addHandleBarPanel.add(addHandleBar);

        // Enter handle bar quantity
        handleBarQuantity.setPreferredSize(new Dimension(100, 25));
        addHandleBarPanel.add(handleBarQuantity);

        // Enter handle bar serial number
        handleBarSerial.setPreferredSize(new Dimension(100, 25));
        addHandleBarPanel.add(handleBarSerial);

        // Enter handle bar brand
        handleBarBrand.setPreferredSize(new Dimension(100, 25));
        addHandleBarPanel.add(handleBarBrand);

        // Enter handle bar type
        handleBarType.setPreferredSize(new Dimension(100, 25));
        addHandleBarPanel.add(handleBarType);

        // Enter handle bar cost
        handleBarCost.setPreferredSize(new Dimension(100, 25));
        addHandleBarPanel.add(handleBarCost);

        // Save the details entered by staff
        handleBarErrorMsg.setForeground(Color.red);
        addHandleBarPanel.add(handleBarErrorMsg);
        saveHandleBarButton.addActionListener(e->{
            try{
                Handlebar hb = new Handlebar(Integer.parseInt(handleBarSerial.getText()),
                        handleBarBrand.getText(),
                        handleBarType.getText(),
                        Integer.parseInt(handleBarQuantity.getText()),
                        Double.parseDouble(handleBarCost.getText()));

                if (hb.createHandleBar()){
                    StaffBikeComponentPanel bikePanel = new StaffBikeComponentPanel(staff);
                    this.add(bikePanel,"bikePanel");
                    card.show(this,"bikePanel");
                }
                else{
                    handleBarErrorMsg.setText("Handlebar exist, Please check your input");
                }
            }
            catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        addHandleBarPanel.add(saveHandleBarButton);

        handleBarPanel.add(addHandleBarPanel, BorderLayout.SOUTH);

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

        // Panel for staff to add wheel product
        addWheelPanel.add(addWheel);

        // Enter wheel quantity
        wheelQuantity.setPreferredSize(new Dimension(100, 25));
        addWheelPanel.add(wheelQuantity);

        // Enter wheel serial number
        wheelSerial.setPreferredSize(new Dimension(100, 25));
        addWheelPanel.add(wheelSerial);

        // Enter wheel brand
        wheelBrand.setPreferredSize(new Dimension(100, 25));
        addWheelPanel.add(wheelBrand);

        // Enter wheel tyre type
        wheelTyreType.setPreferredSize(new Dimension(100, 25));
        addWheelPanel.add(wheelTyreType);

        // Enter wheel brakes type
        wheelBrakesType.setPreferredSize(new Dimension(100, 25));
        addWheelPanel.add(wheelBrakesType);

        // Enter wheel cost
        wheelCost.setPreferredSize(new Dimension(100, 25));
        addWheelPanel.add(wheelCost);

        // Save the details entered by staff
        wheelErrorMsg.setForeground(Color.red);
        addWheelPanel.add(wheelErrorMsg);
        saveWheelButton.addActionListener(e->{
            try{
                Wheel wh = new Wheel(Integer.parseInt(wheelSerial.getText()),
                        wheelBrand.getText(),
                        Double.parseDouble(wheelCost.getText()),
                        wheelTyreType.getText(),
                        wheelBrakesType.getText(),
                        Integer.parseInt(wheelQuantity.getText()));

                if (wh.createWheel()){
                    StaffBikeComponentPanel bikePanel = new StaffBikeComponentPanel(staff);
                    this.add(bikePanel,"bikePanel");
                    card.show(this,"bikePanel");
                }
                else{
                    wheelErrorMsg.setText("Wheel exist, Please check your input");
                }
            }
            catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        addWheelPanel.add(saveWheelButton);

        wheelPanel.add(addWheelPanel, BorderLayout.SOUTH);

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
