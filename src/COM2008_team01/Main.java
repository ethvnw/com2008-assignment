/** COM2008_team01.Main driver class
 * @author Vivek Choradia, Ethan Watts
 * @version 1.2
 * @lastUpdated 30-11-2022 15:02
 */

package COM2008_team01;

import COM2008_team01.graphics.GuiFrame;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        // Run the application
        JFrame guiFrame = new GuiFrame();
        SwingUtilities.invokeLater(() -> guiFrame.setVisible(true));

    }
}
