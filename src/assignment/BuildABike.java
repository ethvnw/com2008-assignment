package assignment;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BuildABike {
    private JButton submitOrderButton;
    private JButton logInButton;
    private JButton viewOrdersButton;
    private JButton nextStepButton;
    private JList list1;
    private JTextPane displayPartInfo;


    public BuildABike() {
        displayPartInfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
    }
}
