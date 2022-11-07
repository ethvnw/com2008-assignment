package assignment;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTables {

    public static boolean createTables(Connection con) {
        try (con) {
            Statement stmt = con.createStatement();
            stmt.execute(createCustomerTable());
            stmt.execute(createStaffTable());
            stmt.execute(createAddressTable());
            stmt.execute(createFrameSetTale());
            stmt.execute(createHandlebarTable());
            stmt.execute(createWheelsTable());
            stmt.execute(createBikeTable());
//            stmt.execute(createOrderTable());

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }

    private static String createCustomerTable() {
        return "CREATE TABLE if not exists customer(" +
                "customerID INT (10) NOT NULL PRIMARY KEY," +
                "forename CHAR (30)  NOT NULL," +
                "surname CHAR (30)  NOT NULL" +
                ");";
    }

    private static String createStaffTable() {
        return "CREATE TABLE if not exists staff(" +
                "staffID INT (10) NOT NULL PRIMARY KEY," +
                "forename CHAR (30)  NOT NULL," +
                "surname CHAR (30)  NOT NULL," +
                "username CHAR (15) NOT NULL," +
                "password CHAR (20) NOT NULL" +
                ");";
    }

    private static String createAddressTable() {
        return "CREATE TABLE if not exists address(" +
                "houseNum CHAR(10) NOT NULL," +
                "road CHAR(15) NOT NULL," +
                "city CHAR(10) NOT NULL," +
                "postcode CHAR(10) NOT NULL," +
                "PRIMARY KEY (houseNum, postcode)" +
                ");";
    }

    private static String createOrderTable() {
        return "CREATE TABLE if not exists order(" +
                "orderID INT(10) NOT NULL PRIMARY KEY," +
                "date CHAR(9) NOT NULL," +
                "cost INT(20) NOT NULL," +
                "bikeID INT(10) NOT NULL," +
                "FOREIGN KEY (bikeID) REFERENCES bike(bikeID)" +
                ");";
    }

    private static String createBikeTable() {
        return "CREATE TABLE if not exists bike(" +
                "bikeID INT(10) NOT NULL PRIMARY KEY," +
                "bikeName CHAR(20) NOT NULL," +
                "brand CHAR(20) NOT NULL," +
                "serialNo INT(15) NOT NULL," +
                "frameSetSerial INT(15) NOT NULL," +
                "frameSetBrand CHAR(20) NOT NULL," +
                "handleBarSerial INT(15) NOT NULL," +
                "handleBarBrand CHAR(20) NOT NULL," +
                "wheelsSerial INT(15) NOT NULL," +
                "wheelsBrand CHAR(20) NOT NULL," +
                "FOREIGN KEY (frameSetSerial, frameSetBrand) REFERENCES frameSet(serialNo, brand)," +
                "FOREIGN KEY (handleBarSerial, handleBarBrand) REFERENCES handleBar(serialNo, brand)," +
                "FOREIGN KEY (wheelsSerial, wheelsBrand) REFERENCES wheels(serialNo, brand)" +
                ");";
    }

    private static String createFrameSetTale() {
        return "CREATE TABLE if not exists frameSet(" +
                "serialNo INT(15) NOT NULL," +
                "brand CHAR(20) NOT NULL," +
                "cost INT NOT NULL," +
                "size DECIMAL(15,2) NOT NULL," +
                "shockAbsorbers TINYINT NOT NULL," +
                "gears INT NOT NULL," +
                "PRIMARY KEY (serialNo, brand)" +
                ");";
    }

    private static String createHandlebarTable() {
        return "CREATE TABLE if not exists handleBar(" +
                "serialNo INT(15) NOT NULL," +
                "brand CHAR(20) NOT NULL," +
                "cost INT NOT NULL," +
                "high TINYINT NOT NULL," +
                "straight TINYINT NOT NULL," +
                "dropped TINYINT NOT NULL," +
                "PRIMARY KEY (serialNo, brand)" +
                ");";
    }

    private static String createWheelsTable() {
        return "CREATE TABLE if not exists wheels(" +
                "serialNo INT(15) NOT NULL," +
                "brand CHAR(20) NOT NULL," +
                "cost INT NOT NULL," +
                "road TINYINT NOT NULL," +
                "mountain TINYINT NOT NULL," +
                "hybrid TINYINT NOT NULL," +
                "rim TINYINT NOT NULL," +
                "disk TINYINT NOT NULL," +
                "PRIMARY KEY (serialNo, brand)" +
                ");";
    }

}

