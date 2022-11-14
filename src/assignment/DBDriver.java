package assignment;

/** For enabling connection between database and models.
 * @author Vivek V Choradia
 * @version 2.0
 * @lastUpdated 13-11-2022 21:00
 */

import java.sql.*;

public class DBDriver {
    static String URL = "jdbc:mysql://stusql.dcs.shef.ac.uk/";
    static String DBNAME = "team001";
    static String USER = "team001";
    static String PASSWORD = "3314b4b3";

    public static void main(String[] args) {
        System.out.println("Connecting...");

//        try (Connection con = DriverManager.getConnection(URL + DBNAME, USER, PASSWORD)) {
//
////            CreateTables.createTables(con);
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
    }

    public static void processInsertQuery(String query) {
        try (Connection con = DriverManager.getConnection(URL + DBNAME, USER, PASSWORD)) {

            Statement stmt = con.createStatement();
            stmt.execute(query);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static ResultSet processGetOutput(String query) {
        try (Connection con = DriverManager.getConnection(URL + DBNAME, USER, PASSWORD)) {

            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);

            return res;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }


}



