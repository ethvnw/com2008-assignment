package assignment.dbconnection;

/** For enabling connection between database and models.
 * @author Vivek V Choradia
 * @version 2.0
 * @lastUpdated 14-11-2022 11:15
 */

import java.sql.*;

public class DBDriver {
    public static final String URL = System.getenv("DB_URL");
    public static final String DBNAME = System.getenv("DB_NAME");
    public static final String USER = System.getenv("DB_USER");
    public static final String PASSWORD = System.getenv("DB_PASSWORD");


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



