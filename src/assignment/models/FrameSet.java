package assignment.models;

import assignment.models.BikeComponent;

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
}
