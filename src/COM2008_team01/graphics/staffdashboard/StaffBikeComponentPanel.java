/** Staff dashboard panel
 * @author Weixiang Han
 * @version 1.0
 * @lastUpdated 23/11/22 07:00
 */


package COM2008_team01.graphics.staffdashboard;

import COM2008_team01.models.*;

import javax.swing.*;

public class StaffBikeComponentPanel extends JPanel {
    private final JPanel buttonPanel = new JPanel();

    private JPanel frameSetPanel = new JPanel();
    private JPanel handleBarPanel = new JPanel();
    private JPanel wheelPanel = new JPanel();

    private JTable frameSetDetails;
    private JTable handleBarDetails;
    private JTable wheelDetails;

    private JScrollPane frameSetScrollPane;
    private JScrollPane handleBarScrollPane;
    private JScrollPane wheelScrollPane;

    protected StaffBikeComponentPanel(Staff staff) {
//        // Frame Set Table
//        String frameSetQuery = "SELECT * FROM team001.frameSet;";
//        List<Order> frameSetList = Order.getOrders(frameSetQuery);
//        String[] bikeColumnNames = {"Bike Serial No", "Bike Brand", "Bike Name",
//                "Handlebar Serial No", "Handlebar Brand",
//                "Wheel Serial No", "Wheel Brand",
//                "Frameset Serial No",
//                "Frameset Brand", "Bike Cost"};
//        bikeDetails = new JTable(new DefaultTableModel(bikeColumnNames,0));
//        bikeScrollPane = new JScrollPane(bikeDetails);
//        bikePanel.setBorder(BorderFactory.createTitledBorder("All Bike Information"));
//
//        for (Order order : bikeList) {
//            String[] bikeColumns = new String[10];
//            Bike bike = Bike.getBike(order.getBikeID());
//
//            bikeColumns[0] = String.valueOf(order.getOrderID());
//            bikeColumns[1] = order.getDate();
//            bikeColumns[2] = order.getAssigned_Staff();
//            bikeColumns[3] = bike.getBrand();
//            bikeColumns[4] = bike.getName();
//            bikeColumns[5] = bike.getHandlebar().getBrand();
//            bikeColumns[6] = bike.getWheels().getBrand();
//            bikeColumns[7] = bike.getFrameSet().getBrand();
//            bikeColumns[8] = "£" + bike.getCost();
//            bikeColumns[9] = order.getStatus();
//
//            DefaultTableModel bikeModel = (DefaultTableModel) bikeDetails.getModel();
//            bikeModel.addRow(bikeColumns);
//        }
//
//        buttonPanel.add(bikePanel, BorderLayout.CENTER);



//        // Bike Table
//        String bikeQuery = "SELECT * FROM team001.bike;";
//        List<Order> bikeList = Order.getOrders(bikeQuery);
//        String[] bikeColumnNames = {"Bike Serial No", "Bike Brand", "Bike Name",
//                "Handlebar Serial No", "Handlebar Brand",
//                "Wheel Serial No", "Wheel Brand",
//                "Frameset Serial No",
//                "Frameset Brand", "Bike Cost"};
//        bikeDetails = new JTable(new DefaultTableModel(bikeColumnNames,0));
//        bikeScrollPane = new JScrollPane(bikeDetails);
//        bikePanel.setBorder(BorderFactory.createTitledBorder("All Bike Information"));
//
//        for (Order order : bikeList) {
//            String[] bikeColumns = new String[10];
//            Bike bike = Bike.getBike(order.getBikeID());
//
//            bikeColumns[0] = String.valueOf(order.getOrderID());
//            bikeColumns[1] = order.getDate();
//            bikeColumns[2] = order.getAssigned_Staff();
//            bikeColumns[3] = bike.getBrand();
//            bikeColumns[4] = bike.getName();
//            bikeColumns[5] = bike.getHandlebar().getBrand();
//            bikeColumns[6] = bike.getWheels().getBrand();
//            bikeColumns[7] = bike.getFrameSet().getBrand();
//            bikeColumns[8] = "£" + bike.getCost();
//            bikeColumns[9] = order.getStatus();
//
//            DefaultTableModel bikeModel = (DefaultTableModel) bikeDetails.getModel();
//            bikeModel.addRow(bikeColumns);
//        }
//
//        buttonPanel.add(bikePanel, BorderLayout.CENTER);
    }


}
