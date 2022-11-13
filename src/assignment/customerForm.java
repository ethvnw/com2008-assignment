package assignment;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class customerForm extends JFrame implements ActionListener {
    private JTextField tfForename, tfSurname, tfHouseNo, tfRoadName, tfCityName, tfPostcode;

    public customerForm (){
        //Creating the Frame
        JFrame frame = new JFrame("Customer Details");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 800);

        //Creating the top panel
        JPanel panelTop = new JPanel(); // the panel is not visible in output
        JLabel labelTop = new JLabel("Build - a Bike");
        panelTop.add(labelTop); // Components Added using Flow Layout

        //Creating the middle panel (Left)
        JPanel panelLeft = new JPanel(); // the panel is not visible in output
        JLabel labelLeft = new JLabel("Customer Details");
        //panelLeft.add(labelLeft); // Components Added using Flow Layout
        panelLeft.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JPanel labels = new JPanel(new GridLayout(0,1));
        JPanel controls = new JPanel(new GridLayout(0,1));
        panelLeft.add(labels, BorderLayout.WEST);
        panelLeft.add(controls, BorderLayout.CENTER);

        tfForename = new JTextField(10); // accepts upto 10 characters
        tfSurname = new JTextField(10); // accepts upto 10 characters
        tfHouseNo = new JTextField(10); // accepts upto 10 characters
        tfRoadName = new JTextField(10); // accepts upto 10 characters
        tfCityName = new JTextField(10); // accepts upto 10 characters
        tfPostcode = new JTextField(10); // accepts upto 10 characters


        labels.add(new JLabel("Forename: ", SwingConstants.LEFT));
        controls.add(tfForename);
        labels.add(new JLabel("Surname: ", SwingConstants.LEFT));
        controls.add(tfSurname);
        labels.add(new JLabel("House No: ", SwingConstants.LEFT));
        controls.add(tfHouseNo);
        labels.add(new JLabel("Road Name: ", SwingConstants.LEFT));
        controls.add(tfRoadName);
        labels.add(new JLabel("City Name: ", SwingConstants.LEFT));
        controls.add(tfCityName);
        labels.add(new JLabel("Postcode: ", SwingConstants.LEFT));
        controls.add(tfPostcode);

        //Creating the middle panel (Right)
        JPanel panelRight = new JPanel(); // the panel is not visible in output
        JLabel labelTwo = new JLabel("Order");
        panelRight.add(labelTwo); // Components Added using Flow Layout
        panelRight.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //Split middle panel into two
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelLeft, panelRight);
        splitPane.setDividerLocation(710);

        //Creating the panel at bottom and adding components
        JPanel panelBottom = new JPanel(); // the panel is not visible in output
        JButton proceedToPayment = new JButton("Proceed to Payment");
        panelBottom.add(proceedToPayment);
        proceedToPayment.addActionListener(this);

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panelBottom);
        frame.getContentPane().add(BorderLayout.NORTH, panelTop);
        frame.getContentPane().add(BorderLayout.CENTER, splitPane);

    }

    public static void main(String[] args) {
        new customerForm().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
