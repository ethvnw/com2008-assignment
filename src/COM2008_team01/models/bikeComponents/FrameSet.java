package COM2008_team01.models.bikeComponents;

import COM2008_team01.utilities.DBDriver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** Represents a FrameSet component (inherited from BikeComponent).
 * @author Vivek V Choradia, Natalie Roberts
 * @version 1.0
 * @lastUpdated 30-11-2022 18:03
 */

public class FrameSet extends BikeComponent {
    private double size;
    private int shockAbsorbers;
    private int gears;

    /**
     * Creates a frameset
     * @param frameSetSerial serial number of frameset
     * @param frameSetBrand brand of frameset
     */
    public FrameSet(int frameSetSerial, String frameSetBrand) {
        super(frameSetSerial, frameSetBrand);
    }

    /**
     * Creates a frameset
     * @param frameSetSerial serial number of frameset
     * @param frameSetBrand brand of frameset
     * @param size size of frameset
     * @param shockAbsorbers whether frameset has shock absorbers
     * @param gears number of gears
     * @param quantity quantity of this frameset
     * @param cost cost of frameset
     */
    public FrameSet(int frameSetSerial, String frameSetBrand, double size, int shockAbsorbers, int gears, int quantity, double cost) {
        super(frameSetSerial, frameSetBrand, quantity, cost);
        this.size = size;
        this.shockAbsorbers = shockAbsorbers;
        this.gears = gears;
    }

    /**
     * Pushes frameset to database
     */
    public boolean createFrameSet() {
        String query = "INSERT INTO frameSet(serialNo, brand, cost, size, shockAbsorbers, gears, quantity) " +
                "VALUES("+ serialNo +", \"" + brand + "\", " + cost + ", " + size + ", " +
                shockAbsorbers + ", " + gears + ", " + quantity + ");";
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
     * Gets specific frameset
     * @param serialNo serial number of frameset
     * @param brand brand name of frameset
     * @return matching frameset, null if none found
     */
    public static FrameSet getFrameSet(int serialNo, String brand) throws SQLException{
        brand = brand.substring(0,1).toUpperCase() + brand.substring(1);

        String query = "SELECT * FROM frameSet WHERE serialNo = " + serialNo + " AND brand = \"" + brand + "\";";
        Statement stmt = DBDriver.getConnection().createStatement();
        ResultSet res = stmt.executeQuery(query);

        while (res.next()) {

            return new FrameSet(res.getInt("serialNo"),
                    res.getString("brand"),
                    res.getDouble("size"),
                    res.getInt("shockAbsorbers"),
                    res.getInt("gears"),
                    res.getInt("quantity"),
                    res.getDouble("cost"));
        }

        return null;
    }

    /**
     * Generates list of frame-sets in database
     * @return list of all frame-sets
     */
    public static List<FrameSet> getAllFrameSets() throws SQLException {
        List<FrameSet> fms = new ArrayList<>();
        String query = "SELECT serialNo, brand FROM frameSet";

        Statement stmt = DBDriver.getConnection().createStatement();
        ResultSet res = stmt.executeQuery(query);

        while (res.next()) {
            FrameSet fm = getFrameSet(res.getInt("serialNo"),
                                        res.getString("brand"));
            fms.add(fm);
        }

        return fms;
    }

    public double getSize() { return size; }

    public int getShockAbsorbers() { return shockAbsorbers; }

    public int getGears() { return gears; }

    /**
     * Updates quantity of frameset in database
     */
    public void updateQuantity() {
        String component = "frameSet";
        this.updateQuantity(component);
    }

    public int getQuantity() {return quantity;}
    public int getSerialNo() {return serialNo;}
    public String getBrand() {return brand;}
    public double getCost() {return cost;}
}
