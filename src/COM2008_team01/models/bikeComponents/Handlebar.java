package COM2008_team01.models.bikeComponents;

import COM2008_team01.utilities.DBDriver;

import java.sql.*;
import java.util.ArrayList;
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
        type = type.substring(0,1).toUpperCase() + type.substring(1);
        this.type = type;
    }

    private void createHandleBar() {
        String query = "INSERT INTO handleBar"
                + "VALUES(" + serialNo +", \"" + brand + "\", " + type + ", " + quantity + ", " + cost + ") ;";
        DBDriver.processQuery(query);
    }
    public static Handlebar getHandlebar(int serialNo, String brand) throws SQLException {
        brand = brand.substring(0,1).toUpperCase() + brand.substring(1);

        String query = "SELECT * FROM handleBar WHERE serialNo = " + serialNo + " AND brand = \"" + brand + "\";";
        Statement stmt = DBDriver.getConnection().createStatement();
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
    }

    public static List<Handlebar> getAllHandlebars() throws SQLException {

        String query = "SELECT * FROM handleBar;";
        List<Handlebar> handlebars = new ArrayList<>();

        Statement stmt = DBDriver.getConnection().createStatement();
        ResultSet res = stmt.executeQuery(query);

        while (res.next()) {
            handlebars.add(getHandlebar(res.getInt("serialNo"), res.getString("brand")));
        }

        return handlebars;
    }

    public void updateQuantity() {
        String component = "handleBar";
        this.updateQuantity(component);
    }
}
