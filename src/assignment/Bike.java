package assignment;

import java.sql.*;

public class Bike {

    private int serialNo;
    private String brand;
    private String name;
    private double cost;

    private FrameSet frameSet;
    private Handlebar handlebar;
    private Wheel wheels;

    public Bike(int serialNo, String brand,String name,
                int frameSetSerial, String frameSetBrand,
                int handlebarSerial, String handlebarBrand,
                int wheelsSerial, String wheelsBrand) {

        this.serialNo = serialNo;
        this.brand = brand;
        this.name = name;

        this.frameSet = new FrameSet(frameSetSerial, frameSetBrand);
        this.handlebar = new Handlebar(handlebarSerial, handlebarBrand);
        this.wheels = new Wheel(wheelsSerial, wheelsBrand);
    }

    public Bike(int serialNo, String brand,String name,
                FrameSet fs, Handlebar hb, Wheel ws) {

        this.serialNo = serialNo;
        this.brand = brand;
        this.name = name;

        this.frameSet = fs;
        this.handlebar = hb;
        this.wheels = ws;
    }

    public double calculateCost() {
        this.cost = (this.frameSet.cost + this.handlebar.cost + this.wheels.cost);
        return this.cost;
    }

    public Bike getBike(int bikeID) throws SQLException {
        String query = "SELECT * FROM bike where bikeId = " + bikeID + ";";

        try(Connection con = DriverManager.getConnection(DBDriver.URL + DBDriver.DBNAME, DBDriver.USER, DBDriver.PASSWORD)) {
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while(res.next()) {
                FrameSet frameSet = new FrameSet(res.getInt("frameSetSerial"), res.getString("frameSetBrand"));
                Handlebar handlebar = new Handlebar(res.getInt("handlebarSerial"), res.getString("handlebarBrand"));
                Wheel wheels = new Wheel(res.getInt("wheelsSerial"), res.getString("wheelsBrand"));

                return new Bike(res.getInt("serialNo"),
                                    res.getString("brand"),
                                    res.getString("name"),
                                    frameSet, handlebar, wheels);
            }

            return null;

        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }


}
