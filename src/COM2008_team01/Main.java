package COM2008_team01;

import COM2008_team01.graphics.GuiFrame;
import COM2008_team01.utilities.DBDriver;

import javax.swing.*;

/** COM2008_team01.Main driver class
 * @author Vivek Choradia, Ethan Watts
 * @version 1.2
 * @lastUpdated 30-11-2022 15:02
 */
public class Main {

    public static void main(String[] args) {

        //Running the static function to establish connection with database
        DBDriver.getConnection();

        // Run the application
        JFrame guiFrame = new GuiFrame();
        SwingUtilities.invokeLater(() -> guiFrame.setVisible(true));
    }
}
