package assignment;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTables {

    public static boolean createTables(Connection con) {
        try(con) {
            Statement stmt = con.createStatement();
            stmt.execute(createCustomerTable());
            stmt.execute(createOrderTable());
            stmt.execute(createStaffTable());
            stmt.execute(createAddressTable());
            stmt.execute(createProductTable());
            stmt.execute(createFrameSetTale());
            stmt.execute(createHandlebarTable());
            stmt.execute(createWheelTable());

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
        return "";
    }

    private static String createProductTable() {
        return "";
    }

    private static String createFrameSetTale() {
        return "";
    }

    private static String createHandlebarTable() {
        return "";
    }

    private static String createWheelTable() {
        return "";
    }

}

