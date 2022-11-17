package assignment.models;

/** Represents a Customer/Shopper.
 * @author Vivek V Choradia
 * @version 1.1
 * @lastUpdated 16-11-2022 19:39
 */

import assignment.dbconnection.DBDriver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int customerID;
    private String forename;
    private String surname;
    Address address;


    public Customer(String forename, String surname, Address address) throws SQLException {
        this.forename = forename;
        this.surname = surname;
        this.address = address;
    }

    public Customer(int customerID, String forename, String surname, Address address) throws SQLException {
        this.customerID = customerID;
        this.forename = forename;
        this.surname = surname;
        this.address = address;
    }

    /**
     * To insert a customer in the database.
     * @return if adding the customer was successful then true otherwise  false
     */
    public boolean createCustomer(){

        String query = "INSERT INTO customer(forename, surname, houseNum, postcode)" +
                        "VALUES (\""+ this.forename +"\", \""+
                                this.surname +"\", \"" + this.address.houseNum + "\", \"" +
                                this.address.postcode + "\");";

        DBDriver.processQuery(query);
        return false;
    }

    /**
     * To get a customer object from a customer ID
     * @param customerID customerID
     * @return A Customer object
     * @throws SQLException handles exception from database queries
     */
    public static Customer getCustomer(int customerID) throws SQLException {
        String query = "SELECT * FROM team001.customer where customerID = " + customerID + ";";
        ResultSet res = DBDriver.processGetOutput(query);
        Customer cus;
        if(res != null) {
            String forename = res.getString("forename");
            String lastname = res.getString("surname");
            Address add = Address.findAddress(res.getString("houseNum"), res.getString("postcode"));
            cus = new Customer(customerID, forename, lastname, add);
            return cus;
        }
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
    public Customer getCustomer(String forename, String surname, String houseNum, String postcode) throws SQLException {
        Address add = Address.findAddress(houseNum, postcode);
        String query = "SELECT * FROM customer WHERE forename = \"" + forename +"\", "+
                        "surname = \"" + surname +"\" ,houseNum = \"" + add.houseNum + "\" ," +
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
    public static List<Customer> getCustomers(String query) {
        List<Customer> customers = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(DBDriver.URL + DBDriver.DBNAME, DBDriver.USER, DBDriver.PASSWORD)) {

            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while(res.next()) {
                Customer cus = Customer.getCustomer(res.getInt("customerId"));
                customers.add(cus);
            }

            return customers;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * To get a list of all customers
     * @return List of all Customers
     */
    public static List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<Customer>();

        String query = "SELECT * FROM customer;";

        try (Connection con = DriverManager.getConnection(DBDriver.URL + DBDriver.DBNAME, DBDriver.USER, DBDriver.PASSWORD)) {

            Statement stmt = con.createStatement();

            ResultSet res = stmt.executeQuery(query);

            while (res.next()) {
                Address add = Address.findAddress(
                        res.getString("houseNum"),
                        res.getString("postcode")
                );
                Customer c = new Customer(
                        res.getInt(1),
                        res.getString(2),
                        res.getString(3),
                        add
                );
                customers.add(c);
            }
            res.close();
            return customers;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * To get a customer from an order ID
     * @param orderID OrderID of an order of the wanted customer
     * @return Customer of that orderID
     */
    public static Customer getCustomerFromOrderID (int orderID) {
        String query = "SELECT customerId from order where orderID = \"" + orderID + "\";";

        try (Connection con = DriverManager.getConnection(DBDriver.URL + DBDriver.DBNAME, DBDriver.USER, DBDriver.PASSWORD)) {

            Statement stmt = con.createStatement();

            ResultSet res = stmt.executeQuery(query);

            while (res.next()) {

                int customerID = res.getInt("customerId");

                return getCustomer(customerID);
            }

            res.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * To update the name of the customer
     */
    public void updateName() {
        String query = "UPDATE customer " +
                        "SET forename = \"" + this.forename + "\" ," +
                        "surname = \"" + this.surname + "\" " +
                        " WHERE customerId = " + this.customerID +";";

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
        String query = "UPDATE customer " +
                "SET houseNum = \"" + houseNum + "\" ," +
                "postcode = \"" + postcode + "\" " +
                " WHERE customerId = " + this.customerID +";";

        DBDriver.processQuery(query);

        this.address.updateAddress(houseNum, road, city, postcode);
    }

}
