package COM2008_team01.utilities;

/** For enabling connection between database and COM2008_team01.models.
 * @author Vivek V Choradia
 * @version 2.0
 * @lastUpdated 14-11-2022 14:45
 */

import java.sql.*;

public class DBDriver {
    public static final String URL = "jdbc:mysql://stusql.dcs.shef.ac.uk/";
    public static final String DBNAME = "team001";
    public static final String USER = "team001";
    public static final String PASSWORD = "3314b4b3";


    /**
     * Takes a string query to execute only for queries which returns nothing.
     * Example - INSERT, UPDATE
     * @param query Query to be executed.
     */
    public static void processQuery(String query) {
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



