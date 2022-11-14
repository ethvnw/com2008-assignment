/** Main driver class
 * @author Vivek Choradia, Ethan Watts
 * @version 1.2
 * @lastUpdated 14-11-2022 20:11
 */

package assignment;

import assignment.graphics.GuiFrame;
import assignment.models.Staff;
import javax.swing.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Run the application
        JFrame guiFrame = new GuiFrame();
        SwingUtilities.invokeLater(() -> guiFrame.setVisible(true));


        Staff s1 = new Staff("vivek", "choradia");
        Staff s2 = new Staff("ray", "han");
        Staff s3 = new Staff("ethan","watts");
        Staff s4 = new Staff("natalie", "roberts");
        Staff s5 = new Staff("vivek2", "choradia");
//        s5.createStaff();

        System.out.println(s1.login());
        System.out.println(s2.login());
        System.out.println(s3.login());
        System.out.println(s4.login());
    }
}
