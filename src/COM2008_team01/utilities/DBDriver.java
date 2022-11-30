package COM2008_team01.utilities;

import java.sql.*;

/** For enabling connection between database and COM2008_team01.models.
 * @author Vivek V Choradia
 * @version 2.0
 * @lastUpdated 14-11-2022 14:45
 */
public class DBDriver {
    public static final String URL = "jdbc:mysql://stusql.dcs.shef.ac.uk/";
    public static final String DBNAME = "team001";
    public static final String USER = "team001";
    public static final String PASSWORD = "3314b4b3";

    public static Connection con;

    /**
     * Takes a string query to execute only for queries which returns nothing.
     * Example - INSERT, UPDATE
     * @param query Query to be executed.
     */
    public static void processQuery(String query) {
        try {

            Statement stmt = DBDriver.getConnection().createStatement();
            stmt.execute(query);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * To get the current connection
     * @return Connection object
     */
    public static Connection getConnection() {
        if (DBDriver.con == null) {
            try  {
                DBDriver.con = DriverManager.getConnection(URL + DBNAME, USER, PASSWORD);

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return DBDriver.con;
    }


}



