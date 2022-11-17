/** Main driver class
 * @author Vivek Choradia, Ethan Watts
 * @version 1.2
 * @lastUpdated 14-11-2022 20:11
 */

package assignment;

import assignment.graphics.GuiFrame;
import assignment.models.Order;
import assignment.models.Staff;
import javax.swing.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Run the application
//        JFrame guiFrame = new GuiFrame();
//        SwingUtilities.invokeLater(() -> guiFrame.setVisible(true));


//        Staff s1 = new Staff("vivek3", "choradia");
//        Staff s2 = new Staff("ray", "han");
//        Staff s3 = new Staff("ethan","watts");
//        Staff s4 = new Staff("natalie", "roberts");
//        Staff s5 = new Staff("vivek2", "choradia");
//        s1.createStaff();
//
//        System.out.println(s1.login());
//        System.out.println(s2.login());
//        System.out.println(s3.login());
//        System.out.println(s4.login());

        Order ord = new Order(2, 5);
//        Order ord2 = new Order(4, 7);
//        Order ord3 = new Order(1, 2);
//
        ord.createOrder();
//        ord2.createOrder();
//        ord3.createOrder();


    }
}
