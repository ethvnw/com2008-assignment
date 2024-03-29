/** Represents a Customer/Shopper.
 * @author Vivek V Choradia, Natalie Roberts
 * @version 1.1
 */

package COM2008_team01.models;

import COM2008_team01.utilities.DBDriver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int customerID;
    private String forename;
    private String surname;
    Address address;

    /**
     * Creates a customer
     * @param forename forename of customer
     * @param surname surname of customer
     * @param address address of customer
     */
    public Customer(String forename, String surname, Address address) {
        forename = forename.substring(0,1).toUpperCase() + forename.substring(1);
        surname = surname.substring(0,1).toUpperCase() + surname.substring(1);

        this.forename = forename;
        this.surname = surname;
        this.address = address;
    }

    /**
     * Creates a customer
     * @param customerID ID of customer
     * @param forename forename of customer
     * @param surname surname of customer
     * @param address address of customer
     */
    public Customer(int customerID, String forename, String surname, Address address) {
        forename = forename.substring(0,1).toUpperCase() + forename.substring(1);
        surname = surname.substring(0,1).toUpperCase() + surname.substring(1);

        this.customerID = customerID;
        this.forename = forename;
        this.surname = surname;
        this.address = address;
    }

    /**
     * To insert a customer in the database.
     * @return customer ID
     */
    public int createCustomer() throws SQLException {

        //To check whether such a customer already exists
        String query = "SELECT customerID FROM customer " +
                "WHERE forename = \"" + forename + "\" AND " +
                "surname = \"" + surname + "\" AND " +
                "houseNum = \"" + address.houseNum + "\" AND " +
                "postcode = \"" + address.postcode + "\" ;";

        Statement stmt = DBDriver.getConnection().createStatement();
        ResultSet res = stmt.executeQuery(query);

        if(res.next()) {
            return res.getInt(1);
        }
        else {

            //Inserting a new customer in the customer table and returning the ID of that customer

            query = "INSERT INTO customer(forename, surname, houseNum, postcode)" +
                    "VALUES (\"" + this.forename + "\", \"" +
                    this.surname + "\", \"" + this.address.houseNum + "\", \"" +
                    this.address.postcode + "\");";


            stmt.execute(query);
            query = "SELECT @@identity as current;";
            res = stmt.executeQuery(query);

            if (res.next()) {
                return (res.getInt(1));
            }

            return 0;
        }
    }

    /**
     * To get a customer object from a customer ID
     * @param customerID customerID
     * @return A Customer object
     * @throws SQLException handles exception from database queries
     */
    public static Customer getCustomer(int customerID) throws SQLException {
        String query = "SELECT * FROM team001.customer where customerID = " + customerID + ";";

            Statement stmt = DBDriver.getConnection().createStatement();

            ResultSet res = stmt.executeQuery(query);

            if (res.next()) {
                Address add = Address.findAddress(res.getString("houseNum"), res.getString("postcode"));
                return new Customer(res.getInt("customerID"),
                        res.getString("forename"),
                        res.getString("surname"),
                        add);
            }

            res.close();
            return null;
    }

    /**
     * To get a customer
     * @param forename forename
     * @param surname surname
     * @param houseNum house number
     * @param postcode postcode
     * @return Customer object
     * @throws SQLException handles exception from database queries
     */
    public static Customer getCustomer(String forename, String surname, String houseNum, String postcode) throws SQLException {
        forename = forename.substring(0,1).toUpperCase() + forename.substring(1);
        surname = surname.substring(0,1).toUpperCase() + surname.substring(1);
        houseNum = houseNum.toUpperCase();
        postcode = postcode.replaceAll("\\s+","").toUpperCase();

        Address add = Address.findAddress(houseNum, postcode);
        if (add == null)
            return null;

        String query = "SELECT * FROM team001.customer WHERE forename = \"" + forename +"\" AND "+
                        "surname = \"" + surname +"\" AND houseNum = \"" + add.houseNum + "\" AND " +
                        "postcode = \"" + add.postcode + "\";";

        try (Connection con = DriverManager.getConnection(DBDriver.URL + DBDriver.DBNAME, DBDriver.USER, DBDriver.PASSWORD)) {

            Statement stmt = con.createStatement();

            ResultSet res = stmt.executeQuery(query);

            while (res.next()) {
                return new Customer(res.getInt("customerID"),
                                    res.getString("forename"),
                                    res.getString("surname"),
                                    add);
            }

            res.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * To get a list of customers depending on the query
     * @param query Query to execute
     * @return List of Customers based on the query
     */
    public static List<Customer> getCustomers(String query) throws SQLException {
        List<Customer> customers = new ArrayList<Customer>();

        Statement stmt = DBDriver.getConnection().createStatement();
        ResultSet res = stmt.executeQuery(query);

        while(res.next()) {
            Customer cus = Customer.getCustomer(res.getInt("customerID"));
            customers.add(cus);
        }

        return customers;

    }

    /**
     * To get a list of all customers
     * @return List of all Customers
     */
    public static List<Customer> getAllCustomers() throws SQLException{
        List<Customer> customers = new ArrayList<Customer>();

        String query = "SELECT * FROM team001.customer;";

        Statement stmt = DBDriver.getConnection().createStatement();

        ResultSet res = stmt.executeQuery(query);

        while (res.next()) {
            Address add = Address.findAddress(
                    res.getString("houseNum"),
                    res.getString("postcode")
            );
            Customer c = new Customer(
                    res.getInt("customerID"),
                    res.getString("forename"),
                    res.getString("surname"),
                    add
            );
            customers.add(c);
        }
        res.close();
        return customers;

    }

    /**
     * To get a customer from an order ID
     * @param orderID OrderID of an order of the wanted customer
     * @return Customer of that orderID
     */
    public static Customer getCustomerFromOrderID (int orderID) throws SQLException {
        String query = "SELECT customerID FROM team001.order WHERE orderID = " + orderID + ";";

        Statement stmt = DBDriver.getConnection().createStatement();

        ResultSet res = stmt.executeQuery(query);

        while (res.next()) {

            int customerID = res.getInt("customerID");

            return getCustomer(customerID);
        }

        res.close();

        return null;
    }

    /**
     * To update the name of the customer
     */
    public void updateName(String fName, String sName) {
        fName = fName.substring(0,1).toUpperCase() + fName.substring(1);
        sName = sName.substring(0,1).toUpperCase() + sName.substring(1);
        String query = "UPDATE customer " +
                        "SET forename = \"" + fName + "\" , " +
                        "surname = \"" + sName + "\" " +
                        " WHERE customerID = " + this.customerID +";";

        DBDriver.processQuery(query);
    }

    /**
     * To update the address of the customer.
     * @param houseNum house number
     * @param road road
     * @param city city
     * @param postcode postcode
     */
    public void updateAddress(String houseNum, String road, String city, String postcode) throws SQLException {
        houseNum = houseNum.toUpperCase();
        road = road.substring(0,1).toUpperCase() + road.substring(1);
        city = city.substring(0,1).toUpperCase() + city.substring(1);
        postcode = postcode.replaceAll("\\s+","").toUpperCase();

        String query = "UPDATE customer " +
                "SET houseNum = \"" + houseNum + "\" ," +
                "postcode = \"" + postcode + "\" " +
                " WHERE customerID = " + this.customerID +";";

        DBDriver.processQuery(query);

        this.address.updateAddress(houseNum, road, city, postcode);
    }

    public int getCustomerID() {
        return customerID;
    }

    public Address getAddress() {
        return address;
    }

    public String getForename() {
        return forename;
    }

    public String getSurname() {
        return surname;
    }

}
