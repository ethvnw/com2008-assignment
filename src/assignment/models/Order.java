package assignment.models;

/** Represents an order.
 * @author Vivek V Choradia
 * @version 1.0
 * @lastUpdated 14-11-2022 10:40
 */

import assignment.dbconnection.DBDriver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private int orderID;
    private String date;
    private String status;
    private int staffID;
    private int customerID;
    private int bikeID;


    /**
     * To create an order object
     * @param date date of order
     * @param customerID Customer ID
     * @param bikeID Bike ID
     */
    public Order(String date, int customerID, int bikeID) {
        this.date = date;
        this.customerID = customerID;
        this.bikeID = bikeID;
        this.status = "Pending";
    }

    /**
     * To store details of the order (called when getting information from the database).
     * @param orderID orderId
     * @param status current status
     * @param date date of order
     * @param bikeID Bike ID
     * @param staffID Assigned staff's ID
     * @param customerID Customer ID
     */
    public Order(int orderID, String status, String date, int bikeID, int staffID, int customerID) {
        this.orderID = orderID;
        this.date = date;
        this.customerID = customerID;
        this.bikeID = bikeID;
        this.status = status;
        this.staffID = staffID;
    }

    /**
     * Creates an order and pushed that order to the database
     */
    public void createOrder() {
        String query = "INSERT INTO order(date, customerID, bikeID, status)" +
                "VALUES(\""+this.date+"\", \"" + this.customerID +"\", \"" + this.bikeID + "\", \"" + this.status +"\")";

        DBDriver.processQuery(query);
    }

    /**
     * Assigns a staff to complete the order
     * @param staffID Staff's ID who is to be assigned.
     */
    public void assignStaff(int staffID) {
        this.staffID = staffID;
        updateOrder();
    }

    /**
     * To change the current status of the Order
     * @param status new status to be assigned.
     */
    public void changeStatus(String status) {
        this.status = status;
        updateOrder();
    }

    /**
     * Returns the current status of the given order (using orderID)
     * @param orderID Order's ID
     * @return current status of the order
     */
    public String getStatus(int orderID) {

        String query = "SELECT status FROM order where orderId = " + orderID + ";";

        try(Connection con = DriverManager.getConnection(DBDriver.URL + DBDriver.DBNAME, DBDriver.USER, DBDriver.PASSWORD)) {
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while(res.next()) {
                return res.getString("status");
            }

            return null;

        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     *  To return the current status of the order.
     * @return current status of the order.
     */
    public String getStatus() {
        return this.status;
    }


    /**
     * Returns the Order object.
     * @param orderID Order's ID
     * @return An order object of the given orderID
     */
    public Order getOrder(int orderID) {

        String query = "SELECT * FROM order where orderId = " + orderID + ";";

        try(Connection con = DriverManager.getConnection(DBDriver.URL + DBDriver.DBNAME, DBDriver.USER, DBDriver.PASSWORD)) {
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while(res.next()) {

                return new Order(
                        res.getInt("orderId"),
                        res.getString("status"),
                        res.getString("date"),
                        res.getInt("bikeId"),
                        res.getInt("staffId"),
                        res.getInt("customerId")
                );
            }

            return null;

        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;

    }

    /**
     * To update the order in the database.
     */
    public void updateOrder() {
        String query = "UPDATE order" +
                        "SET date = \"" + this.date + "\", " +
                        "status = \"" + this.status + "\", " +
                        "customerId = \"" + this.customerID +"\", " +
                        "staffId = \"" + this.staffID +"\", " +
                        "bikeId = \"" + this.bikeID +"\"  " +
                        "WHERE orderID = " + this.orderID + ";";
        DBDriver.processQuery(query);
    }

    /**
     * To get all the orders of a given customer
     * @param customerID Customer's ID
     * @return List of orders
     */
    public List<Order> getAllOrderOfACustomer(int customerID) {

        List<Order> orders = new ArrayList<Order>();

        String query = "SELECT * FROM order WHERE customerID = " + customerID + "; ";

        try(Connection con = DriverManager.getConnection(DBDriver.URL + DBDriver.DBNAME, DBDriver.USER, DBDriver.PASSWORD)) {
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while(res.next()) {

                Order ord = new Order(
                        res.getInt("orderId"),
                        res.getString("status"),
                        res.getString("date"),
                        res.getInt("bikeId"),
                        res.getInt("staffId"),
                        res.getInt("customerId")
                );

                orders.add(ord);
            }

            return orders;

        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }





}
