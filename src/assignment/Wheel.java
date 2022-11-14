package assignment;

/** Represents a Wheel component (inherited from BikeComponent).
 * @author Vivek V Choradia
 * @version 1.0
 * @lastUpdated 14-11-2022 10:44
 */

public class Wheel extends BikeComponent{

    private String tyre;
    private String brakes;
    public Wheel(int wheelsSerial, String wheelsBrand) {
        super(wheelsSerial, wheelsBrand);
    }
}
