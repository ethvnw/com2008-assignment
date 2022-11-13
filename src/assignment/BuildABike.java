package assignment;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;

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

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout(0, 0));
        panel1.setBackground(new Color(-5985349));
        panel1.setEnabled(true);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout(0, 0));
        panel1.add(panel2, BorderLayout.EAST);
        final JLabel label1 = new JLabel();
        label1.setBackground(new Color(-5985349));
        Font label1Font = this.$$$getFont$$$("Dubai", Font.BOLD, 28, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-10777357));
        label1.setHorizontalAlignment(0);
        label1.setHorizontalTextPosition(0);
        label1.setText("             Order            ");
        panel2.add(label1, BorderLayout.NORTH);
        submitOrderButton = new JButton();
        Font submitOrderButtonFont = this.$$$getFont$$$("Bahnschrift", Font.BOLD, 16, submitOrderButton.getFont());
        if (submitOrderButtonFont != null) submitOrderButton.setFont(submitOrderButtonFont);
        submitOrderButton.setText("Submit Order");
        panel2.add(submitOrderButton, BorderLayout.SOUTH);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new BorderLayout(0, 0));
        panel1.add(panel3, BorderLayout.CENTER);
        final JLabel label2 = new JLabel();
        label2.setBackground(new Color(-5985349));
        Font label2Font = this.$$$getFont$$$("Dubai", Font.BOLD, 28, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setForeground(new Color(-10777357));
        label2.setHorizontalAlignment(0);
        label2.setHorizontalTextPosition(0);
        label2.setText("Step 1");
        panel3.add(label2, BorderLayout.NORTH);
        nextStepButton = new JButton();
        Font nextStepButtonFont = this.$$$getFont$$$("Bahnschrift", Font.BOLD, 16, nextStepButton.getFont());
        if (nextStepButtonFont != null) nextStepButton.setFont(nextStepButtonFont);
        nextStepButton.setText("Next Step");
        panel3.add(nextStepButton, BorderLayout.SOUTH);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new BorderLayout(0, 0));
        panel3.add(panel4, BorderLayout.CENTER);
        final JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setHorizontalScrollBarPolicy(31);
        panel4.add(scrollPane1, BorderLayout.WEST);
        list1 = new JList();
        Font list1Font = this.$$$getFont$$$(null, -1, 20, list1.getFont());
        if (list1Font != null) list1.setFont(list1Font);
        final DefaultListModel defaultListModel1 = new DefaultListModel();
        defaultListModel1.addElement("frameset 1");
        defaultListModel1.addElement("frameset 2");
        defaultListModel1.addElement("frameset 3");
        defaultListModel1.addElement("frameset 4");
        defaultListModel1.addElement("frameset 5");
        defaultListModel1.addElement("frameset 6");
        defaultListModel1.addElement("frameset 7");
        defaultListModel1.addElement("frameset 8");
        defaultListModel1.addElement("frameset 9");
        defaultListModel1.addElement("frameset 10");
        defaultListModel1.addElement("frameset 11");
        defaultListModel1.addElement("frameset 12");
        defaultListModel1.addElement("frameset 13");
        defaultListModel1.addElement("frameset 14");
        defaultListModel1.addElement("frameset 15");
        defaultListModel1.addElement("frameset 16");
        list1.setModel(defaultListModel1);
        list1.setSelectionMode(0);
        scrollPane1.setViewportView(list1);
        final JScrollPane scrollPane2 = new JScrollPane();
        panel4.add(scrollPane2, BorderLayout.CENTER);
        displayPartInfo = new JTextPane();
        displayPartInfo.setEditable(false);
        displayPartInfo.setText("");
        scrollPane2.setViewportView(displayPartInfo);
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new BorderLayout(0, 0));
        panel1.add(panel5, BorderLayout.NORTH);
        final JLabel label3 = new JLabel();
        label3.setBackground(new Color(-5985349));
        Font label3Font = this.$$$getFont$$$("Dubai", Font.BOLD, 30, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setForeground(new Color(-10777357));
        label3.setHorizontalAlignment(0);
        label3.setHorizontalTextPosition(0);
        label3.setText("Build-a-Bike");
        panel5.add(label3, BorderLayout.CENTER);
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel5.add(panel6, BorderLayout.EAST);
        logInButton = new JButton();
        logInButton.setText("Log In");
        panel6.add(logInButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        viewOrdersButton = new JButton();
        viewOrdersButton.setText("View Orders");
        panel6.add(viewOrdersButton, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

}
