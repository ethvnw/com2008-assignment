package assignment.models;

import assignment.dbconnection.DBDriver;
import assignment.models.BikeComponent;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/** Represents a Handlebar component (inherited from BikeComponent).
 * @author Vivek V Choradia
 * @version 1.0
 * @lastUpdated 14-11-2022 10:44
 */

public class Handlebar extends BikeComponent {

    private String type;
    public Handlebar(int handlebarSerial, String handlebarBrand) {
        super(handlebarSerial, handlebarBrand);
    }

    public Handlebar(int handlebarSerial, String handlebarBrand, String type, int quantity, double cost) {
        super(handlebarSerial, handlebarBrand,quantity, cost);
        this.type = type;
    }

    private void createHandleBar() {
        String query = "INSERT INTO handleBar"
                + "VALUES(" + serialNo +", \"" + brand + "\", " + type + ", " + quantity + ", " + cost + ") ;";
        DBDriver.processQuery(query);
    }
    public static Handlebar getHandlebar(int serialNo, String brand) {
        String query = "SELECT * FROM handleBar WHERE serialNo = " + serialNo + " AND brand = \"" + brand + "\";";
        try (Connection con = DriverManager.getConnection(DBDriver.URL + DBDriver.DBNAME, DBDriver.USER, DBDriver.PASSWORD)) {
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while (res.next()) {

                return new Handlebar(
                        res.getInt("serialNo"),
                        res.getString("brand"),
                        res.getString("type"),
                        res.getInt("quantity"),
                        res.getDouble("cost")
                );
            }

            return null;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static List<Handlebar> getAllHandlebars() {

        String query = "SELECT * FROM handleBar;";
        List<Handlebar> handlebars = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(DBDriver.URL + DBDriver.DBNAME, DBDriver.USER, DBDriver.PASSWORD)) {
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while (res.next()) {
                handlebars.add(getHandlebar(res.getInt("serialNo"), res.getString("brand")));
            }

            return handlebars;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public void updateQuantity() {
        String component = "handleBar";
        this.updateQuantity(component);
    }
}
