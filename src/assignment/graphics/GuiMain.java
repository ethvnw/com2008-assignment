/** GUI main handler.
 * @author Ethan Watts
 * @version 1.0
 * @lastUpdated 13-11-2022 23:19
 */

package assignment.graphics;

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
        setSize(screenSize.width/2, screenSize.height/2);

        // Parent panel that all other panels are a part of
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(layout);

        // Panels for each page
        CustomerForm cForm = new CustomerForm();
        mainPanel.add(cForm, "cForm");

        // TESTING /////////////////////////////////////////////////////////////
        JPanel testPanel = new JPanel();
        testPanel.add(new JTextField("Test Test Test Test Test Test Test Test "));
        mainPanel.add(testPanel, "tPanel");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(new JLabel("Buttons"));

        JButton testButton = new JButton("Test");
        buttonPanel.add(testButton);

        testButton.addActionListener(e -> {
            layout.next(mainPanel);
        });

//        layout.set
        getContentPane().add(mainPanel, BorderLayout.NORTH);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        ////////////////////////////////////////////////////////////////////////////
    }

    public static void main(String[] args) {
        new GuiMain();
    }
}
