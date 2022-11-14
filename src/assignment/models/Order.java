package assignment.models;

/** Represents an order.
 * @author Vivek V Choradia
 * @version 1.0
 * @lastUpdated 14-11-2022 10:40
 */

import assignment.dbconnection.DBDriver;

import java.sql.*;

public class Order {

    private int orderID;
    private String date;
    private String status;
    private int staffID;
    private int customerID;
    private int bikeID;


    public Order(String date, int customerID, int bikeID) {
        this.date = date;
        this.customerID = customerID;
        this.bikeID = bikeID;
        this.status = "Pending";
    }

    public Order(int orderID, String status, String date, int bikeID, int staffID, int customerID) {
        this.orderID = orderID;
        this.date = date;
        this.customerID = customerID;
        this.bikeID = bikeID;
        this.status = status;
        this.staffID = staffID;
    }

    public void createOrder() {
        String query = "INSERT INTO order(date, customerID, bikeID, status)" +
                "VALUES(\""+this.date+"\", \"" + this.customerID +"\", \"" + this.bikeID + "\", \"" + this.status +"\")";

        DBDriver.processInsertQuery(query);
    }

    public void assignStaff(int staffID) {
        this.staffID = staffID;
    }

    public void changeStatus(String status) {
        this.status = status;
    }

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

    public String getStatus() {
        return this.status;
    }


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





}
