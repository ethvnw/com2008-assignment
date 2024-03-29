/** Represents a superclass - BikeComponent.
 * @author Vivek V Choradia, Natalie Roberts
 * @version 1.2
 */

package COM2008_team01.models.bikeComponents;

import COM2008_team01.utilities.DBDriver;


public class BikeComponent {
    protected int serialNo;
    protected String brand;
    public double cost;
    protected int quantity;

    /**
     * Creates basic component with no type
     * @param serialNo serial number of component
     * @param brand brand of component
     */
    public BikeComponent(int serialNo, String brand) {
        brand = brand.substring(0,1).toUpperCase() + brand.substring(1);

        this.serialNo = serialNo;
        this.brand = brand;
    }

    /**
     * Creates basic component with no type
     * @param serialNo serial number of component
     * @param brand brand of component
     * @param quantity quantity of component
     * @param cost cost of component
     */
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
    public double getCost() { return cost; }
    public void increaseQuantity(int amount) {quantity = quantity + amount; }
    public void reduceQuantity(int amount) { quantity = quantity - amount; }


    /**
     * Changes quantity of component in database
     * @param component component to change quantity of
     */
    public void updateQuantity(String component) {
        String query = "UPDATE " + "team001." + component +
                " SET quantity = " + this.quantity + " " +
                "WHERE serialNo = " + this.serialNo + " " +
                "AND brand = \"" + this.brand +"\";";
        DBDriver.processQuery(query);
    }

}
