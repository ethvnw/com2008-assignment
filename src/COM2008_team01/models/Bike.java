/** Represents a Bike (Assembled Product).
 * @author Vivek V Choradia, Ethan Watts, Natalie Roberts
 * @version 1.0
 * @lastUpdated 18-11-2022 20:18
 */

package COM2008_team01.models;

import COM2008_team01.utilities.DBDriver;
import COM2008_team01.models.bikeComponents.FrameSet;
import COM2008_team01.models.bikeComponents.Handlebar;
import COM2008_team01.models.bikeComponents.Wheel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Bike {

    private int serialNo;
    private int quantity;
    private String brand;
    private String name;
    private double cost;

    private FrameSet frameSet;
    private Handlebar handlebar;
    private Wheel wheels;

    /**
     * Creates a bike
     * @param serialNo serial number of bike
     * @param name name of bike
     * @param frameSetSerial serial number of frameset
     * @param frameSetBrand brand of frameset
     * @param handlebarSerial serial number of handlebar
     * @param handlebarBrand brand of handlebar
     * @param wheelsSerial serial number of wheel
     * @param wheelsBrand brand of wheel
     */
    public Bike(int serialNo, String name,
                int frameSetSerial, String frameSetBrand,
                int handlebarSerial, String handlebarBrand,
                int wheelsSerial, String wheelsBrand) {

        this.serialNo = serialNo;
        this.name = name;

        this.frameSet = new FrameSet(frameSetSerial, frameSetBrand);
        this.handlebar = new Handlebar(handlebarSerial, handlebarBrand);
        this.wheels = new Wheel(wheelsSerial, wheelsBrand);

        this.brand = frameSetBrand + " " + wheels.getTyre();

        this.cost = calculateCost();
    }

    /**
     * Creates a bike - the object returned from get methods
     * @param serialNo serial number of bike
     * @param brand brand of bike
     * @param name name of bike
     * @param fs frameset of bike
     * @param hb handlebar of bike
     * @param ws wheel of bike
     */
    public Bike(int serialNo, String brand, String name,
                FrameSet fs, Handlebar hb, Wheel ws) {

        this.serialNo = serialNo;
        this.brand = brand;
        this.name = name;

        this.frameSet = fs;
        this.handlebar = hb;
        this.wheels = ws;

        this.cost = calculateCost();
    }

    public void createBike() {
        String query = "INSERT INTO bike(serialNo, brand, name, cost" +
                "frameSetSerial, frameSetBrand," +
                " handlebarSerial,  handlebarBrand, " +
                "wheelsSerial,  wheelsBrand)" +
                " VALUES("+this.serialNo+", \""+this.brand+"\"," +
                "\"" + this.name + "\"," + this.cost + ", " +
                this.frameSet.getSerialNo() + ", \'" + this.frameSet.getBrand() +"\"," +
                this.handlebar.getSerialNo() + ", \'" + this.handlebar.getBrand()+"\"," +
                this.wheels.getSerialNo() + ", \'" + this.wheels.getBrand() +"\"," +");";

        DBDriver.processQuery(query);
        this.frameSet.reduceQuantity(1);
        this.handlebar.reduceQuantity(1);
        this.wheels.reduceQuantity(1);
    }


    /**
     * To calculate the cost of a bike
     *
     * @return total cost of the bike
     */
    public double calculateCost() {
        this.cost = (this.frameSet.cost + this.handlebar.cost + this.wheels.cost) + 10;
        return this.cost;
    }

    /**
     * To get a Bike
     *
     * @param bikeID bikeID
     * @return A Bike Object
     * @throws SQLException to handle database queries
     */
    public static Bike getBike(int bikeID) throws SQLException {
        String query = "SELECT * FROM team001.bike where bikeId = " + bikeID + ";";

        Statement stmt = DBDriver.getConnection().createStatement();
        ResultSet res = stmt.executeQuery(query);

        while (res.next()) {
            FrameSet frameSet = new FrameSet(res.getInt("frameSetSerial"), res.getString("frameSetBrand"));
            Handlebar handlebar = new Handlebar(res.getInt("handlebarSerial"), res.getString("handlebarBrand"));
            Wheel wheels = new Wheel(res.getInt("wheelsSerial"), res.getString("wheelsBrand"));

            return new Bike(res.getInt("serialNo"),
                    res.getString("brand"),
                    res.getString("bikeName"),
                    frameSet, handlebar, wheels);
        }
        return null;
    }

    /**
     * To get a list of all bikes
     * @return List of Bikes
     * @throws SQLException to handle database queries
     */
    public static List<Bike> getAllBikes() throws SQLException {
        List<Bike> bikes = new ArrayList<>();

        String query = "SELECT bikeID FROM bike;";

        Statement stmt = DBDriver.getConnection().createStatement();
        ResultSet res = stmt.executeQuery(query);

        while (res.next()) {
            bikes.add(Bike.getBike(res.getInt("bikeID")));
        }

        res.close();

        return bikes;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getSerialNo() {
        return serialNo;
    }

    public FrameSet getFrameSet() {
        return frameSet;
    }

    public Handlebar getHandlebar() {
        return handlebar;
    }

    public Wheel getWheels() {
        return wheels;
    }

    public String getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }
}
