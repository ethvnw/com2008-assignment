/** Bike creation panel
 * @author Ethan Watts, Natalie Roberts
 * @version 1.0
 * @lastUpdated 30-11-2022 17:45
 */

package COM2008_team01.graphics.bikecreation;

import COM2008_team01.models.Bike;
import COM2008_team01.models.Order;
import COM2008_team01.models.bikeComponents.BikeComponent;
import COM2008_team01.models.bikeComponents.FrameSet;
import COM2008_team01.models.bikeComponents.Handlebar;
import COM2008_team01.models.bikeComponents.Wheel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BikeCreationPanel extends JPanel {
    private JPanel container = new JPanel();
    private JTabbedPane partSelectionPanes = new JTabbedPane();
    private JPanel frameSelectionPanel = new JPanel();
    private JTable frameParts;

    private JPanel handlebarSelectionPanel = new JPanel();
    private JTable handlebarParts;

    private JPanel wheelSelectionPanel = new JPanel();
    private JTable wheelParts;

    private JPanel summaryPanel = new JPanel();
    private JLabel chosenFrame = new JLabel();
    private JLabel chosenFrameCost = new JLabel();
    private JLabel chosenHandlebar = new JLabel();
    private JLabel chosenHandlebarCost = new JLabel();
    private JLabel chosenWheel = new JLabel();
    private JLabel chosenWheelCost = new JLabel();

    private JLabel totalCost = new JLabel();
    private JButton confirmOrder = new JButton("Confirm");

    public BikeCreationPanel() {
        CardLayout card = new CardLayout();
        this.setLayout(card);
        container.setLayout(new BorderLayout());

        String[] frameColumnNames = {"Brand", "Size (cm)", "Has Shock-absorbers?", "No. Gears", "Cost"};
        List<FrameSet> framePartList = FrameSet.getAllFrameSets();
        Object[] frameObjects = buildPartsTable("frame", frameColumnNames, framePartList);
        frameParts = (JTable) frameObjects[0];
        frameSelectionPanel = (JPanel) frameObjects[1];

        String[] handlebarColumnNames = {"Brand", "Type", "Cost"};
        List<Handlebar> handlebarPartList = Handlebar.getAllHandlebars();
        Object[] handlebarObjects = buildPartsTable("handlebar", handlebarColumnNames, handlebarPartList);
        handlebarParts = (JTable) handlebarObjects[0];
        handlebarSelectionPanel = (JPanel) handlebarObjects[1];

        String[] wheelColumnNames = {"Brand", "Tyre Type", "Brake Type", "Cost"};
        List<Wheel> wheelPartList = Wheel.getAllWheels();
        Object[] wheelObjects = buildPartsTable("wheel", wheelColumnNames, wheelPartList);
        wheelParts = (JTable) wheelObjects[0];
        wheelSelectionPanel = (JPanel) wheelObjects[1];

        partSelectionPanes.addTab("Frames", frameSelectionPanel);
        partSelectionPanes.addTab("Handlebars", handlebarSelectionPanel);
        partSelectionPanes.addTab("Wheels", wheelSelectionPanel);

        container.add(partSelectionPanes, BorderLayout.NORTH);

        frameParts.getSelectionModel().addListSelectionListener(e -> {
            chosenFrame.setText(frameParts.getValueAt(frameParts.getSelectedRow(),0).toString());
            chosenFrameCost.setText(frameParts.getValueAt(frameParts.getSelectedRow(), 4).toString());
            totalCost.setText(String.valueOf(calculateTotal()));
        });

        handlebarParts.getSelectionModel().addListSelectionListener(e -> {
            chosenHandlebar.setText(handlebarParts.getValueAt(handlebarParts.getSelectedRow(),0).toString());
            chosenHandlebarCost.setText(handlebarParts.getValueAt(handlebarParts.getSelectedRow(),2).toString());
            totalCost.setText(String.valueOf(calculateTotal()));
        });

        wheelParts.getSelectionModel().addListSelectionListener(e -> {
            chosenWheel.setText(wheelParts.getValueAt(wheelParts.getSelectedRow(),0).toString());
            chosenWheelCost.setText(wheelParts.getValueAt(wheelParts.getSelectedRow(),3).toString());
            totalCost.setText(String.valueOf(calculateTotal()));
        });

        // TODO pass in the chosen parts to a new order, and display it in OrderFormPanel
        confirmOrder.addActionListener(e -> {
            Order order = null;
            OrderFormPanel orderFormPanel = new OrderFormPanel(order);
            this.add(orderFormPanel,"orderFormPanel");
            card.show(this,"orderFormPanel");
        });

        summaryPanel.setLayout(new GridLayout(0, 2));
        summaryPanel.add(chosenFrame);
        summaryPanel.add(chosenFrameCost);
        summaryPanel.add(chosenHandlebar);
        summaryPanel.add(chosenHandlebarCost);
        summaryPanel.add(chosenWheel);
        summaryPanel.add(chosenWheelCost);
        summaryPanel.add(new JLabel("Total Cost"));
        summaryPanel.add(totalCost);
        summaryPanel.add(confirmOrder);

        container.add(summaryPanel, BorderLayout.SOUTH);
        this.add(container,"container");
        card.show(this,"container");
    }

    private double calculateTotal() {
        double frameCost = 0, handlebarCost = 0, wheelCost = 0;
        double total = 0.0;
        try {
            frameCost = Double.parseDouble(frameParts.getValueAt(frameParts.getSelectedRow(), 4).toString());
        } finally {total += frameCost;}
        try {
            handlebarCost = Double.parseDouble(handlebarParts.getValueAt(handlebarParts.getSelectedRow(),2).toString());
        } finally {total += handlebarCost;}
        try {
            wheelCost = Double.parseDouble(wheelParts.getValueAt(wheelParts.getSelectedRow(),3).toString());
        } finally {total += wheelCost;}
        return total;
    }
    private Object[] buildPartsTable(String partType, String[] columnNames, List<? extends BikeComponent> partList) {
        JPanel selectionPanel = new JPanel();
        JScrollPane scrollpane;
        JTable parts;


        parts = new JTable(new DefaultTableModel(columnNames,0));
        scrollpane = new JScrollPane(parts);
        selectionPanel.setLayout(new BorderLayout());
        selectionPanel.add(scrollpane, BorderLayout.CENTER);
        parts.setDefaultEditor(Object.class, null);

        if (!partList.isEmpty()) {
            for (BikeComponent part : partList) {
                String[] details = new String[columnNames.length];
                details[0] = part.getBrand();

                if (partType == "frame") {
                    FrameSet framePart = (FrameSet) part;
                    details[1] = String.valueOf(framePart.getSize());
                    details[2] = String.valueOf(framePart.getShockAbsorbers());
                    if (framePart.getShockAbsorbers() == 0) { details[2] = "No"; }
                    else { details[2] = "Yes"; }
                    details[3] = String.valueOf(framePart.getGears());
                    details[4] = String.valueOf(framePart.getCost());
                } else if (partType == "handlebar") {
                    Handlebar handlebarPart = (Handlebar) part;
                    details[1] = handlebarPart.getType();
                    details[2] = String.valueOf(handlebarPart.getCost());
                } else if (partType == "wheel") {
                    Wheel wheelPart = (Wheel) part;
                    details[1] = wheelPart.getTyre();
                    details[2] = wheelPart.getBrakes();
                    details[3] = String.valueOf(wheelPart.getCost());
                }

                DefaultTableModel model = (DefaultTableModel) parts.getModel();
                //checking there are parts available to add to order
                if (part.getQuantity() != 0) {
                    model.addRow(details);
                }
            }
        } else {
            //all the parts have 0 quantity
            selectionPanel.add(new JLabel("No parts available"));
        }

        Object[] returns = {parts, selectionPanel};
        return returns;
    }
}
