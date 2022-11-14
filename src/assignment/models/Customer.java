package assignment.models;

/** Represents a Customer/Shopper.
 * @author Vivek V Choradia
 * @version 1.0
 * @lastUpdated 13-11-2022 23:18
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

    public boolean createCustomer(){

        String query = "INSERT INTO customer(forename, surname, houseNum, postcode)" +
                        "VALUES (\""+ this.forename +"\", \""+
                                this.surname +"\", \"" + this.address.houseNum + "\", \"" +
                                this.address.postcode + "\");";

        DBDriver.processQuery(query);
        return false;
    }

    public Customer getCustomer(int customerID) throws SQLException {
        String query = "SELECT * FROM customer where customerID = " + customerID + ";";
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
     * To get a customer (only when we call
     * @param forename
     * @param surname
     * @param houseNum
     * @param postcode
     * @return
     * @throws SQLException
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
     * To get a list of all customers
     * @return List of all Customers
     */
    public List<Customer> getAllCustomers() {
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




}
