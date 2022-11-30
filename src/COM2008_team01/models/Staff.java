/** Represents a Staff.
 * @author Vivek V Choradia
 * @version 1.0
 * @lastUpdated 14-11-2022 10:37
 */

package COM2008_team01.models;

import COM2008_team01.utilities.DBDriver;
import COM2008_team01.utilities.Encryption;

import COM2008_team01.utilities.Cookies;

import java.sql.*;
import java.util.List;
import java.util.Objects;

public class Staff {
    private String username;
    private String password;

    /**
     * Creates a staff
     * @param username username of staff
     * @param password password of staff
     */
    public Staff(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * To insert the staff in the database.
     * @throws Exception handling exception while encrypting the password.
     */
    public void createStaff() throws Exception {
        Encryption encryption = new Encryption();

        String query = "INSERT INTO staff(username, password)" +
                        "VALUES(\""+ this.username + "\", \"" + encryption.encrypt(password) + "\");";

        DBDriver.processQuery(query);
    }

    /**
     * To login as a staff.
     * @return true if login was successful otherwise false.
     * @throws Exception handles exception if any error occurs while comparing the password.
     */
    public String login() throws Exception {

        if (Cookies.loggedInStaff != null) {
            return null;
        }

        Encryption encryption = new Encryption();

        String query = "SELECT * FROM staff WHERE username = \"" + this.username + "\";";

        try (Connection con = DriverManager.getConnection(DBDriver.URL + DBDriver.DBNAME, DBDriver.USER, DBDriver.PASSWORD)) {

            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);

            if (res == null) {
                return null;
            }

            while(res.next()) {
                String password = encryption.decrypt(res.getString("password"));
                if(Objects.equals(password, this.password)) {
                    Cookies.loggedInStaff = this;
                    return this.username;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * To log out the current staff member
     * @return status whether logout was successful or not
     */
    public static boolean logout() {
        if(Cookies.loggedInStaff == null) {
            return false;
        } else {
            Cookies.loggedInStaff = null;
            return true;
        }
    }

    /**
     * Update status of order
     * @param order order to change status of
     * @return whether the status change was successful
     */
    public boolean confirmOrder(Order order) {
        try {
            order.assignStaff(this.username);
            order.changeStatus("Confirmed");
            order.updateOrder();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * To get all the orders in the database.
     * @return A list of Orders.
     */
    public static List<Order> getAllOrders() {
        return Order.getOrders("SELECT orderId FROM order");
    }

    /**
     * To get all the customers in the database.
     * @return A list of all Customers
     */
    public static List<Customer> getAllCustomers() {
        return Customer.getCustomers("SELECT customerId FROM customer");
    }

    /**
     * To get all the pending orders from the database.
     * @return List of pending orders.
     */
    public static List<Order> getAllPendingOrders() {
        return Order.getOrders("SELECT orderId FROM order WHERE status = \"Pending\"");
    }

    /**
     * To get all the in progress orders from the database.
     * @return To get List of In Progress orders.
     */
    public static List<Order> getAllInProgress() {
        return Order.getOrders("SELECT orderId FROM order WHERE status = \"In Progress\"");
    }

    public String getUsername() {
        return username;
    }
}
