package assignment.models;

import assignment.dbconnection.DBDriver;

import java.util.ArrayList;
import java.util.List;

/** Represents a superclass - BikeComponent.
 * @author Vivek V Choradia, Natalie Roberts
 * @version 1.0
 * @lastUpdated 20-11-2022 20:15
 */

public class BikeComponent {

    protected int serialNo;
    protected String brand;
    protected double cost;
    protected int quantity;

    public BikeComponent(int serialNo, String brand) {
        this.serialNo = serialNo;
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }
    public int getSerialNo() { return serialNo; }
    public int getQuantity() { return quantity; }
    public void increaseQuantity(int amount) {quantity = quantity + amount; }
    public void reduceQuantity(int amount) { quantity = quantity - amount; }

    public void updateQuantity(String component) {
        String query = "UPDATE " + component +
                "SET quantity = " + this.quantity + ", " +
                "WHERE serialNo = " + this.serialNo + "" +
                "AND brand = \"" + this.brand +"\";";
        DBDriver.processQuery(query);
    }

    public List<BikeComponent> getAllBikeComponents() {
        List<BikeComponent> components = new ArrayList<>();
        components.addAll(FrameSet.getAllFrameSets());
        components.addAll(Handlebar.getAllHandlebars());
        components.addAll(Wheel.getAllWheel());

        return components;
    }
}
