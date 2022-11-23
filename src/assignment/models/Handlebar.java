package assignment.models;

import assignment.models.BikeComponent;

import java.util.Collection;
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

    public static List<Handlebar> getAllHandlebars() {

        return null;
    }

    public void updateQuantity() {
        String component = "handleBar";
        this.updateQuantity(component);
    }
}
