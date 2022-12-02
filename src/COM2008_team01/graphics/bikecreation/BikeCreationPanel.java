/** Bike creation panel
 * @author Ethan Watts, Natalie Roberts
 * @version 1.5
 * @lastUpdated 01-12-2022 23:59
 */

package COM2008_team01.graphics.bikecreation;

import COM2008_team01.graphics.HintTextField;
import COM2008_team01.models.Bike;
import COM2008_team01.models.Order;
import COM2008_team01.models.bikeComponents.BikeComponent;
import COM2008_team01.models.bikeComponents.FrameSet;
import COM2008_team01.models.bikeComponents.Handlebar;
import COM2008_team01.models.bikeComponents.Wheel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
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

    private JPanel box = new JPanel();
    private JLabel chosenFrame = new JLabel("", 0);
    private JLabel chosenFrameCost = new JLabel("", 0);
    private JLabel chosenHandlebar = new JLabel("", 0);
    private JLabel chosenHandlebarCost = new JLabel("", 0);
    private JLabel chosenWheel = new JLabel("", 0);
    private JLabel chosenWheelCost = new JLabel("", 0);

    private HintTextField bikeName = new HintTextField(" Bike Name");
    private JLabel totalCost = new JLabel("", 0);
    private JButton confirmOrder = new JButton("Confirm");
    private JLabel message = new JLabel("");

    public BikeCreationPanel() {
        CardLayout card = new CardLayout();
        this.setLayout(card);
        container.setLayout(new BorderLayout());

        String[] frameColumnNames = {"Brand", "Serial No.", "Size (cm)", "Has Shock-absorbers?", "No. Gears", "Cost"};
        try {
            List<FrameSet> framePartList = FrameSet.getAllFrameSets();
            Object[] frameObjects = buildPartsTable("frame", frameColumnNames, framePartList);
            frameParts = (JTable) frameObjects[0];
            frameSelectionPanel = (JPanel) frameObjects[1];
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String[] handlebarColumnNames = {"Brand", "Serial No.", "Type", "Cost"};
        try {
            List<Handlebar> handlebarPartList = Handlebar.getAllHandlebars();
            Object[] handlebarObjects = buildPartsTable("handlebar", handlebarColumnNames, handlebarPartList);
            handlebarParts = (JTable) handlebarObjects[0];
            handlebarSelectionPanel = (JPanel) handlebarObjects[1];
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String[] wheelColumnNames = {"Brand", "Serial No.", "Tyre Type", "Brake Type", "Cost"};
        try {
            List<Wheel> wheelPartList = Wheel.getAllWheels();
            Object[] wheelObjects = buildPartsTable("wheel", wheelColumnNames, wheelPartList);
            wheelParts = (JTable) wheelObjects[0];
            wheelSelectionPanel = (JPanel) wheelObjects[1];
        } catch (SQLException e) {
            e.printStackTrace();
        }

        partSelectionPanes.addTab("Frames", frameSelectionPanel);
        partSelectionPanes.addTab("Handlebars", handlebarSelectionPanel);
        partSelectionPanes.addTab("Wheels", wheelSelectionPanel);

        container.add(partSelectionPanes, BorderLayout.NORTH);

        frameParts.getSelectionModel().addListSelectionListener(e -> {
            chosenFrame.setText(getCellValue(frameParts, 0));
            chosenFrameCost.setText(getCellValue(frameParts, 5));
            totalCost.setText(String.valueOf(calculateTotal()));
        });

        handlebarParts.getSelectionModel().addListSelectionListener(e -> {
            chosenHandlebar.setText(getCellValue(handlebarParts, 0));
            chosenHandlebarCost.setText(getCellValue(handlebarParts, 3));
            totalCost.setText(String.valueOf(calculateTotal()));
        });

        wheelParts.getSelectionModel().addListSelectionListener(e -> {
            chosenWheel.setText(getCellValue(wheelParts, 0));
            chosenWheelCost.setText(getCellValue(wheelParts, 4));
            totalCost.setText(String.valueOf(calculateTotal()));
        });

        confirmOrder.addActionListener(e -> {
            if (chosenFrame.getText().equals("") || chosenHandlebar.getText().equals("") || chosenWheel.getText().equals("")) {
                message.setText("This is not a valid order");
            } else {
                if (bikeName.getText().equals("")  || bikeName.getText().equals("Bike Name")) {
                    message.setText("Please enter a bike name");
                } else {
                    Bike bike = new Bike(
                            bikeName.getText(),
                            Integer.parseInt(getCellValue(frameParts, 1)),
                            getCellValue(frameParts, 0),
                            Integer.parseInt(getCellValue(handlebarParts, 1)),
                            getCellValue(handlebarParts, 0),
                            Integer.parseInt(getCellValue(wheelParts, 1)),
                            getCellValue(wheelParts, 0),
                            calculateTotal()
                    );
                    try {
                        int bikeID = bike.createBike();
                        Order order = new Order(bikeID);
                        OrderFormPanel orderFormPanel = new OrderFormPanel(
                                order,
                                bike,
                                Double.parseDouble(getCellValue(frameParts, 5)),
                                Double.parseDouble(getCellValue(handlebarParts, 3)),
                                Double.parseDouble(getCellValue(wheelParts, 4))
                                );
                        this.add(orderFormPanel,"orderFormPanel");
                        card.show(this,"orderFormPanel");
                    } catch (SQLException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        });


        summaryPanel.setLayout(new GridLayout(0, 2));
        summaryPanel.add(chosenFrame);
        summaryPanel.add(chosenFrameCost);
        summaryPanel.add(chosenHandlebar);
        summaryPanel.add(chosenHandlebarCost);
        summaryPanel.add(chosenWheel);
        summaryPanel.add(chosenWheelCost);
        summaryPanel.add(new JLabel("Assembly Fee", 0));
        summaryPanel.add(new JLabel("10.0", 0));
        summaryPanel.add(new JLabel("Total Cost", 0));
        summaryPanel.add(totalCost);
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        summaryPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        box.add(summaryPanel);
        bikeName.setAlignmentX(Component.CENTER_ALIGNMENT);
        box.add(bikeName);
        confirmOrder.setAlignmentX(Component.CENTER_ALIGNMENT);
        box.add(confirmOrder);
        message.setAlignmentX(Component.CENTER_ALIGNMENT);
        box.add(message);

        container.add(box, BorderLayout.SOUTH);
        box.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(container,"container");
        card.show(this,"container");
    }

    private String getCellValue(JTable table, int column) {
        return table.getValueAt(table.getSelectedRow(), column).toString();
    }

    /**
     * Calculates the total of all the parts
     */
    private double calculateTotal() {
        double frameCost = 0, handlebarCost = 0, wheelCost = 0;
        double total = 0.0;
        try {
            frameCost = Double.parseDouble(getCellValue(frameParts, 5));
        } catch (Exception e) { } finally {total += frameCost;}
        try {
            handlebarCost = Double.parseDouble(getCellValue(handlebarParts, 3));
        } catch (Exception e) { } finally {total += handlebarCost;}
        try {
            wheelCost = Double.parseDouble(getCellValue(wheelParts, 4));
        } catch (Exception e) { } finally {total += wheelCost;}
        return total + 10;
    }

    /**
     * creates the JTable with all the parts and their configurations listed
     * @param partType type of part
     * @param columnNames names of the columns in the table
     * @param partList list of all the parts of that type
     * @return returns an array of objects with the part JTable and selection JPanel
     */
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
                details[1] = String.valueOf(part.getSerialNo());

                if (partType == "frame") {
                    FrameSet framePart = (FrameSet) part;
                    details[2] = String.valueOf(framePart.getSize());
                    if (framePart.getShockAbsorbers() == 0) { details[3] = "No"; }
                    else { details[3] = "Yes"; }
                    details[4] = String.valueOf(framePart.getGears());
                    details[5] = String.valueOf(framePart.getCost());
                } else if (partType == "handlebar") {
                    Handlebar handlebarPart = (Handlebar) part;
                    details[2] = handlebarPart.getType();
                    details[3] = String.valueOf(handlebarPart.getCost());
                } else if (partType == "wheel") {
                    Wheel wheelPart = (Wheel) part;
                    details[2] = wheelPart.getTyre();
                    details[3] = wheelPart.getBrakes();
                    details[4] = String.valueOf(wheelPart.getCost());
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
