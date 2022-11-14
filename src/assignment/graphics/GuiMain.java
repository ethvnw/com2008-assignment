package assignment.graphics;

/** GUI main handler.
 * @author Ethan Watts
 * @version 1.0
 * @lastUpdated 13-11-2022 23:19
 */

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class GuiMain extends JFrame {
    @Serial
    private static final long serialVersionUID = 1L;
    public GuiMain() {
        // Set up the JFrame
        super("Build a Bike");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        CardLayout layout = new CardLayout();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(screenSize.width/4, screenSize.height/4);

        // Parent panel that all other panels are a part of
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(layout);

        // TESTING /////////////////////////////////////////////////////////////
        JPanel testPanel1 = new JPanel();
        testPanel1.add(new JLabel("Test 1"));
        JPanel testPanel2 = new JPanel();
        testPanel2.add(new JLabel("Test 2"));
        JPanel testPanel3 = new JPanel();
        testPanel3.add(new JLabel("Test 3"));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(new JLabel("Buttons"));

        mainPanel.add(testPanel1, "1");
        mainPanel.add(testPanel2, "2");
        mainPanel.add(testPanel3, "3");

        JButton testButton = new JButton("Test");
        buttonPanel.add(testButton);

        testButton.addActionListener(e -> {
            layout.show(mainPanel,"3");
            // layout.next(mainPanel);
        });

        getContentPane().add(mainPanel, BorderLayout.NORTH);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        ////////////////////////////////////////////////////////////////////////////
    }

    public static void main(String[] args) {
        new GuiMain();
    }
}
