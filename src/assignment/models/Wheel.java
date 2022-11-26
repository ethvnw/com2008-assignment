package assignment.models;

import assignment.dbconnection.DBDriver;
import assignment.models.BikeComponent;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/** Represents a Wheel component (inherited from BikeComponent).
 * @author Vivek V Choradia
 * @version 1.0
 * @lastUpdated 14-11-2022 10:44
 */

public class Wheel extends BikeComponent {

    private String tyre;
    private String brakes;

    public Wheel(int wheelsSerial, String wheelsBrand) {
        super(wheelsSerial, wheelsBrand);
    }

    public Wheel(int wheelsSerial, String wheelsBrand, double cost, String tyre, String brakes, int quantity) {
        super(wheelsSerial, wheelsBrand, quantity, cost);
        this.tyre = tyre;
        this.brakes = brakes;
    }

    private void createWheel() {
        String query = "INSERT INTO handleBar"
                + "VALUES(" + serialNo +", \"" + brand + "\", " + cost + ", " + tyre + ", " + brakes +
                ", " + quantity + ", " + cost + ");";
        DBDriver.processQuery(query);
    }

    public static Wheel getWheel(int serialNo, String brand) {
        String query = "SELECT * FROM wheels WHERE serialNo = " + serialNo + " AND brand = \"" + brand + "\";";
        try (Connection con = DriverManager.getConnection(DBDriver.URL + DBDriver.DBNAME, DBDriver.USER, DBDriver.PASSWORD)) {
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while (res.next()) {

                return new Wheel(
                        res.getInt("serialNo"),
                        res.getString("brand"),
                        res.getDouble("cost"),
                        res.getString("tyre"),
                        res.getString("brakes"),
                        res.getInt("quantity")
                );
            }

            return null;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static List<Wheel> getAllWheels() {
        String query = "SELECT * FROM wheels;";
        List<Wheel> wheels = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(DBDriver.URL + DBDriver.DBNAME, DBDriver.USER, DBDriver.PASSWORD)) {
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while (res.next()) {
                wheels.add(getWheel(res.getInt("serialNo"), res.getString("brand")));
            }

            return wheels;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public void updateQuantity() {
        String component = "wheels";
        this.updateQuantity(component);
    }
}
