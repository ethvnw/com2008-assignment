/** Bike creation panel
 * @author
 * @version 1.0
 * @lastUpdated
 */

package assignment.graphics.bikecreation;

import assignment.models.Order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BikeCreationPanel extends JPanel {
    private JPanel container = new JPanel();
    private JTabbedPane partSelectionPanes = new JTabbedPane();
    private JPanel frameSelectionPanel = new JPanel();
    private JScrollPane frameScrollpane;
    private JTable frameParts;

    private JPanel handlebarSelectionPanel = new JPanel();
    private JScrollPane handlebarScrollpane;
    private JTable handlebarParts;

    private JPanel wheelSelectionPanel = new JPanel();
    private JScrollPane wheelScrollpane;
    private JTable wheelParts;

    private JPanel summaryPanel = new JPanel();
    private JLabel chosenFrame = new JLabel();
    private JLabel chosenHandlebar = new JLabel();;
    private JLabel chosenWheel = new JLabel();;
    private JButton confirmOrder = new JButton("Confirm");

    public BikeCreationPanel() {
        CardLayout card = new CardLayout();
        this.setLayout(card);
        container.setLayout(new BorderLayout());

        partSelectionPanes.addTab("Frames", frameSelectionPanel);
        partSelectionPanes.addTab("Handlebars", handlebarSelectionPanel);
        partSelectionPanes.addTab("Wheels", wheelSelectionPanel);
        container.add(partSelectionPanes, BorderLayout.NORTH);

        String[] frameColumnNames = {"Brand", "Size (cm)", "Has Shock-absorbers?", "No. Gears", "Cost"};
        frameParts = new JTable(new DefaultTableModel(frameColumnNames,0));
        frameScrollpane = new JScrollPane(frameParts);
        frameSelectionPanel.setLayout(new BorderLayout());
        frameSelectionPanel.add(frameScrollpane, BorderLayout.CENTER);

        // TODO populate table with frames, copy code in CustomerAccountPanel


        String[] handlebarColumnNames = {"Brand", "Type", "Cost"};
        handlebarParts = new JTable(new DefaultTableModel(handlebarColumnNames,0));
        handlebarScrollpane = new JScrollPane(handlebarParts);
        handlebarSelectionPanel.setLayout(new BorderLayout());
        handlebarSelectionPanel.add(handlebarScrollpane, BorderLayout.CENTER);

        // TODO populate table with handlebars, copy code in CustomerAccountPanel


        String[] wheelColumnNames = {"Brand", "Tyre Type", "Brake Type", "Cost"};
        wheelParts = new JTable(new DefaultTableModel(wheelColumnNames,0));
        wheelScrollpane = new JScrollPane(wheelParts);
        wheelSelectionPanel.setLayout(new BorderLayout());
        wheelSelectionPanel.add(wheelScrollpane, BorderLayout.CENTER);

        // TODO populate table with wheels, copy code in CustomerAccountPanel



        frameParts.getSelectionModel().addListSelectionListener(e -> {
            chosenFrame.setText(
                frameParts.getValueAt(frameParts.getSelectedRow(),0).toString()
                + " " + frameParts.getValueAt(frameParts.getSelectedRow(),1).toString()
                + " " + frameParts.getValueAt(frameParts.getSelectedRow(),2).toString()
                + " " + frameParts.getValueAt(frameParts.getSelectedRow(),3).toString()
                + " " + frameParts.getValueAt(frameParts.getSelectedRow(),4).toString()
            );
        });

        handlebarParts.getSelectionModel().addListSelectionListener(e -> {
            chosenHandlebar.setText(
                handlebarParts.getValueAt(handlebarParts.getSelectedRow(),0).toString()
                + " " + handlebarParts.getValueAt(handlebarParts.getSelectedRow(),1).toString()
                + " " + handlebarParts.getValueAt(handlebarParts.getSelectedRow(),2).toString()
            );
        });

        wheelParts.getSelectionModel().addListSelectionListener(e -> {
            chosenWheel.setText(
                wheelParts.getValueAt(wheelParts.getSelectedRow(),0).toString()
                + " " + wheelParts.getValueAt(wheelParts.getSelectedRow(),1).toString()
                + " " + wheelParts.getValueAt(wheelParts.getSelectedRow(),2).toString()
                + " " + wheelParts.getValueAt(wheelParts.getSelectedRow(),3).toString()
            );
        });


        // TODO pass in the chosen parts to a new order, and display it in OrderFormPanel
        confirmOrder.addActionListener(e -> {
            Order order = null;
            OrderFormPanel orderFormPanel = new OrderFormPanel(order);
            this.add(orderFormPanel,"orderFormPanel");
            card.show(this,"orderFormPanel");
        });


        summaryPanel.add(chosenFrame);
        summaryPanel.add(chosenHandlebar);
        summaryPanel.add(chosenWheel);
        summaryPanel.add(confirmOrder);

        container.add(summaryPanel, BorderLayout.SOUTH);
        this.add(container,"container");
        card.show(this,"container");
    }
}
