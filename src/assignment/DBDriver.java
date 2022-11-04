package assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBDriver {
    static String URL = "jdbc:mysql://stusql.dcs.shef.ac.uk/";
    static String DBNAME = "team001";
    static String USER = "team001";
    static String PASSWORD = "3314b4b3";

    public static void main(String[] args) {
        System.out.println("Connecting...");

        try (Connection con = DriverManager.getConnection(URL + DBNAME, USER, PASSWORD)) {

            CreateTables.createTables(con);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}



