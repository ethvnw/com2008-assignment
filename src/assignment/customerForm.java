package assignment;
import javax.swing.*;
import java.awt.*;

public class customerForm {
    public static void main(String args[]){
        //Creating the Frame
        JFrame frame = new JFrame("Customer Details");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 800);

        JComponent one = new JLabel("Left Split");
        one.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
        JComponent two = new JLabel("Right Split");
        two.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, one, two);
        frame.add(splitPane);

        //Creating the top panel
        JPanel panelTop = new JPanel(); // the panel is not visible in output
        JLabel labelTop = new JLabel("Build - a Bike");
        panelTop.add(labelTop); // Components Added using Flow Layout

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Enter Text");
        JTextField tf = new JTextField(10); // accepts upto 10 characters
        JButton send = new JButton("Send");
        JButton reset = new JButton("Reset");
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(send);
        panel.add(reset);

        // Text Area at the Center
        //JTextArea ta = new JTextArea();

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, panelTop);
        //frame.getContentPane().add(BorderLayout.CENTER, ta);
        frame.setVisible(true);
    }
}
