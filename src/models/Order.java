package models;

/** Represents an order.
 * @author Vivek V Choradia
 * @version 2.0
 * @lastUpdated 14-11-2022 15:33
 */

import utilities.DBDriver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Order {

    private int orderID;
    private String date;
    private String status;
    private String assigned_Staff;
    private int customerID;
    private int bikeID;


    /**
     * To create an order object
     * @param customerID Customer ID
     * @param bikeID Bike ID
     */
    public Order( int customerID, int bikeID) {
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
     * @param assigned_Staff Assigned staff's ID
     * @param customerID Customer ID
     */
    public Order(int orderID, String status, String date, int bikeID, String assigned_Staff, int customerID) {
        this.orderID = orderID;
        this.date = date;
        this.customerID = customerID;
        this.bikeID = bikeID;
        this.status = status;
        this.assigned_Staff = assigned_Staff;
    }

    /**
     * Creates an order and pushed that order to the database
     */
    public void createOrder() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
        Date date = new Date();
        this.date = formatter.format(date);
        String query = "INSERT INTO order (date, customerID, bikeID, status)" +
                " VALUES (\""+this.date+"\", " + this.customerID + ", " + this.bikeID + ", \"" + this.status +"\");";

        DBDriver.processQuery(query);
    }

    public boolean deleteOrder() {
        Bike bike = Bike.getBike(this.bikeID);
        if (status == "Pending") {
            assert bike != null;
            bike.getFrameSet().increaseQuantity(1);
            bike.getFrameSet().updateQuantity();
            bike.getHandlebar().increaseQuantity(1);
            bike.getFrameSet().updateQuantity();
            bike.getWheels().increaseQuantity(2);
            bike.getFrameSet().updateQuantity();

            String query = "DELETE FROM order WHERE orderID = " + this.orderID + ";";
            DBDriver.processQuery(query);

            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Assigns a staff to complete the order
     * @param assigned_Staff Staff's ID who is to be assigned.
     */
    public void assignStaff(String assigned_Staff) {
        this.assigned_Staff = assigned_Staff;
    }

    /**
     * To change the current status of the Order
     * @param status new status to be assigned.
     */
    public void changeStatus(String status) {
        this.status = status;
    }

    /**
     * Returns the Order object.
     * @param orderID Order's ID
     * @return An order object of the given orderID
     */
    public static Order getOrder(int orderID) {

        String query = "SELECT * FROM team001.order WHERE orderID = " + orderID + ";";

        try(Connection con = DriverManager.getConnection(DBDriver.URL + DBDriver.DBNAME, DBDriver.USER, DBDriver.PASSWORD)) {
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while(res.next()) {

                return new Order(
                        res.getInt("orderID"),
                        res.getString("status"),
                        res.getString("date"),
                        res.getInt("bikeID"),
                        res.getString("assigned_Staff"),
                        res.getInt("customerID")
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
     * To get a list of orders
     * @param query Get orders based on the giving query
     * @return A list of orders
     */
    public static List<Order> getOrders(String query) {

        List<Order> orders = new ArrayList<Order>();

        try (Connection con = DriverManager.getConnection(DBDriver.URL + DBDriver.DBNAME, DBDriver.USER, DBDriver.PASSWORD)) {

            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while(res.next()) {
                Order ord = Order.getOrder(res.getInt("orderID"));
                orders.add(ord);
            }

            return orders;

        } catch (SQLException ex) {
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
                        "customerID = \"" + this.customerID +"\", " +
                        "assigned_Staff = \"" + this.assigned_Staff +"\", " +
                        "bikeID = \"" + this.bikeID +"\"  " +
                        "WHERE orderID = " + this.orderID + ";";
        DBDriver.processQuery(query);
    }

    /**
     * To get all the orders of a given customer
     * @param customerID Customer's ID
     * @return List of orders
     */
    public static List<Order> getAllOrderOfACustomer(int customerID) {

        String query = "SELECT * FROM team001.order WHERE customerID = " + customerID + ";";

        return getOrders(query);
    }

    public int getOrderID() {
        return orderID;
    }

    public String getDate() {
        return date;
    }

    public int getBikeID() {
        return bikeID;
    }

    /**
     *  To return the current status of the order.
     * @return current status of the order.
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * Returns the current status of the given order (using orderID)
     * @param orderID Order's ID
     * @return current status of the order
     */
    public static String getStatus(int orderID) {

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

    public String getAssigned_Staff() {
        return this.assigned_Staff;
    }
}
