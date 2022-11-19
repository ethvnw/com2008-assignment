package assignment.models;

/** Represents a Staff.
 * @author Vivek V Choradia
 * @version 1.0
 * @lastUpdated 14-11-2022 10:37
 */

import assignment.dbconnection.DBDriver;

// TODO create assembleBike()

import java.sql.*;
import java.util.List;
import java.util.Objects;

public class Staff {
    private String username;
    private String password;


    //Constructor
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
    public boolean login() throws Exception {

        Encryption encryption = new Encryption();

        String query = "SELECT * FROM staff WHERE username = \"" + this.username + "\";";

        try (Connection con = DriverManager.getConnection(DBDriver.URL + DBDriver.DBNAME, DBDriver.USER, DBDriver.PASSWORD)) {

            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while(res.next()) {
                String password = encryption.decrypt(res.getString("password"));
                return Objects.equals(password, this.password);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * To get a staff
     * @param username username
     * @param password password
     * @return Staff object
     * @throws SQLException handles exception from database queries
     */
    public static Staff getStaff(String username, String password) throws SQLException {
        String query = "SELECT * FROM team001.staff WHERE this.username = \"" + username +"\" AND "+
                "this.password = \"" + password +"\" ";

        try (Connection con = DriverManager.getConnection(DBDriver.URL + DBDriver.DBNAME, DBDriver.USER, DBDriver.PASSWORD)) {

            Statement stmt = con.createStatement();

            ResultSet res = stmt.executeQuery(query);

            while (res.next()) {
                return new Staff(res.getInt("staffID"),
                        res.getString("username"),
                        res.getString("password"),
                        add);
            }

            res.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
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


}
