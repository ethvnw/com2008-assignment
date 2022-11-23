package assignment.models;

import assignment.dbconnection.DBDriver;
import assignment.models.BikeComponent;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/** Represents a FrameSet component (inherited from BikeComponent).
 * @author Vivek V Choradia
 * @version 1.0
 * @lastUpdated 14-11-2022 10:44
 */

public class FrameSet extends BikeComponent {

    private double size;
    private boolean shockAbsorbers;
    private int gears;


    public FrameSet(int frameSetSerial, String frameSetBrand) {
        super(frameSetSerial, frameSetBrand);
    }

    public FrameSet(int frameSetSerial, String frameSetBrand, double size, boolean shockAbsorbers, int gears) {
        super(frameSetSerial, frameSetBrand);
        this.size = size;
        this.shockAbsorbers = shockAbsorbers;
        this.gears = gears;
    }

    public static FrameSet getFrameSet(int serialNo, String brand) {

        String query = "SELECT * FROM frameSet WHERE serialNo = " + serialNo + " AND brand = \"" + brand + "\";";
        try (Connection con = DriverManager.getConnection(DBDriver.URL + DBDriver.DBNAME, DBDriver.USER, DBDriver.PASSWORD)) {
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while (res.next()) {


                return new FrameSet(res.getInt("serialNo"),
                        res.getString("brand"),
                        res.getDouble("size"),
                        res.getBoolean("shockAbsorbers"),
                        res.getInt("gears"));
            }

            return null;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }
    public static List<FrameSet> getAllFrameSets() {
        List<FrameSet> fms = new ArrayList<>();

        String query = "SELECT serialNo, brand FROM frameSet";

        try (Connection con = DriverManager.getConnection(DBDriver.URL + DBDriver.DBNAME, DBDriver.USER, DBDriver.PASSWORD)) {
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while (res.next()) {
                FrameSet fm = getFrameSet(res.getInt("serialNo"),
                                            res.getString("brand"));
                fms.add(fm);
            }

            return fms;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public void updateQuantity() {
        String component = "frameSet";
        this.updateQuantity(component);
    }
}
