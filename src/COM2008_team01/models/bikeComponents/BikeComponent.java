package COM2008_team01.models.bikeComponents;

import COM2008_team01.utilities.DBDriver;

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
    public double cost;
    protected int quantity;

    public BikeComponent(int serialNo, String brand) {
        brand = brand.substring(0,1).toUpperCase() + brand.substring(1);

        this.serialNo = serialNo;
        this.brand = brand;
    }
    public BikeComponent(int serialNo, String brand, int quantity, double cost) {
        brand = brand.substring(0,1).toUpperCase() + brand.substring(1);

        this.serialNo = serialNo;
        this.brand = brand;
        this.quantity = quantity;
        this.cost = cost;
    }

    public String getBrand() {
        return brand;
    }
    public int getSerialNo() { return serialNo; }
    public int getQuantity() { return quantity; }
    public void increaseQuantity(int amount) {quantity = quantity + amount; }
    public void reduceQuantity(int amount) { quantity = quantity - amount; }

    public void updateQuantity(String component) {
        String query = "UPDATE " + "team001." + component +
                " SET quantity = " + this.quantity + " " +
                "WHERE serialNo = " + this.serialNo + " " +
                "AND brand = \"" + this.brand +"\";";
        DBDriver.processQuery(query);
    }

    public List<BikeComponent> getAllBikeComponents() {
        List<BikeComponent> components = new ArrayList<>();
        components.addAll(FrameSet.getAllFrameSets());
        components.addAll(Handlebar.getAllHandlebars());
        components.addAll(Wheel.getAllWheels());

        return components;
    }
}
