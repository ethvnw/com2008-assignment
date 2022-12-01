package COM2008_team01.models.bikeComponents;

import COM2008_team01.utilities.DBDriver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** Represents a Handlebar component (inherited from BikeComponent).
 * @author Vivek V Choradia, Natalie Roberts
 * @version 1.0
 * @lastUpdated 30-11-2022 18:03
 */

public class Handlebar extends BikeComponent {
    private String type;

    /**
     * Creates a handlebar
     * @param handlebarSerial serial number of handlebar
     * @param handlebarBrand brand of handlebar
     */
    public Handlebar(int handlebarSerial, String handlebarBrand) {
        super(handlebarSerial, handlebarBrand);
    }

    /**
     * Creates a handlebar
     * @param handlebarSerial serial number of handlebar
     * @param handlebarBrand brand of handlebar
     * @param type type of handlebar (Straight/Dropped/High)
     * @param quantity quantity of handlebar
     * @param cost cost of the handlebar
     */
    public Handlebar(int handlebarSerial, String handlebarBrand, String type, int quantity, double cost) {
        super(handlebarSerial, handlebarBrand,quantity, cost);
        type = type.substring(0,1).toUpperCase() + type.substring(1);
        this.type = type;
    }

    /**
     * Pushes handlebar to database
     */
    public boolean createHandleBar() {
        String query = "INSERT INTO handleBar(serialNo, brand, type, quantity, cost)"
                + "VALUES(" + serialNo +", \"" + brand + "\", " + type + ", " + quantity + ", " + cost + ") ;";
        try {
            Statement stmt = DBDriver.getConnection().createStatement();
            stmt.execute(query);
            return true;
        }
        catch (SQLException ex) {
            return false;
        }
    }

    /**
     * Gets specific handlebar
     * @param serialNo serial number of handlebar
     * @param brand brand of handlebar
     * @return matching handlebar, null if none found
     */
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


    /**
     * Generates list of handlebars in database
     * @return list of all handlebars
     */
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

    public String getType() { return type; }

    /**
     * Updates quantity of handlebar in database
     */
    public void updateQuantity() {
        String component = "handleBar";
        this.updateQuantity(component);
    }

    public int getQuantity() {return quantity;}
    public int getSerialNo() {return serialNo;}
    public String getBrand() {return brand;}
    public double getCost() {return cost;}
}
