package assignment.models;

/** Represents a superclass - BikeComponent.
 * @author Vivek V Choradia
 * @version 1.0
 * @lastUpdated 14-11-2022 10:40
 */

public class BikeComponent {

    protected int serialNo;
    protected String brand;
    protected double cost;
    protected static int quantity = 50;

    public BikeComponent(int serialNo, String brand) {
        this.serialNo = serialNo;
        this.brand = brand;
    }



}
