/** Customer Form page where asking for customer details before processing payment.
 * @author Han Weixiang
 * @version 1.1
 * @lastUpdated 14-11-2022 16:39
 */
package assignment.graphics;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.TitledBorder;

public class CustomerForm extends JPanel{
    private final JTextField tfForename;
    private final JTextField tfSurname;
    private final JTextField tfHouseNo;
    private final JTextField tfRoadName;
    private final JTextField tfCityName;
    private final JTextField tfPostcode;
    private final JTextField tfOrderDetails;

    public CustomerForm (){
        BorderLayout bl = new BorderLayout();
        this.setLayout(bl);

        JPanel formContainer = new JPanel();
        formContainer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        TitledBorder borderLeft = BorderFactory.createTitledBorder("Customer Details");
        formContainer.setBorder(borderLeft);

        tfForename = new JTextField(10); // accepts upto 10 characters
        tfSurname = new JTextField(10); // accepts upto 10 characters
        tfHouseNo = new JTextField(10); // accepts upto 10 characters
        tfRoadName = new JTextField(10); // accepts upto 10 characters
        tfCityName = new JTextField(10); // accepts upto 10 characters
        tfPostcode = new JTextField(10); // accepts upto 10 characters

        JPanel form = new JPanel();
        GridLayout gb = new GridLayout(6,2);
        form.setLayout(gb);
        formContainer.add(form);

        form.add(new JLabel("Forename: ", SwingConstants.LEFT));
        form.add(tfForename);
        form.add(new JLabel("Surname: ", SwingConstants.LEFT));
        form.add(tfSurname);
        form.add(new JLabel("House No: ", SwingConstants.LEFT));
        form.add(tfHouseNo);
        form.add(new JLabel("Road Name: ", SwingConstants.LEFT));
        form.add(tfRoadName);
        form.add(new JLabel("City Name: ", SwingConstants.LEFT));
        form.add(tfCityName);
        form.add(new JLabel("Postcode: ", SwingConstants.LEFT));
        form.add(tfPostcode);


        tfOrderDetails = new JTextField(50);

        JPanel orderDetails = new JPanel();
        orderDetails.add(tfOrderDetails);

        this.add(formContainer,BorderLayout.WEST);
        this.add(orderDetails,BorderLayout.EAST);
    }
}
