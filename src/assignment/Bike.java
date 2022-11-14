package assignment;

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
}
