package assignment.models;

/** Represents a Staff.
 * @author Vivek V Choradia
 * @version 1.0
 * @lastUpdated 14-11-2022 10:37
 */

import assignment.dbconnection.DBDriver;
import assignment.models.AES_ENCRYPTION;

import java.sql.*;

public class Staff {
    private int staffId;
    private String username;
    private String password;


//    Constructors
    public Staff(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Staff(int staffId, String username, String password) {
        this.staffId = staffId;
        this.username = username;
        this.password = password;
    }

    public int login() throws Exception, SQLException {

        AES_ENCRYPTION aes_encryption = new AES_ENCRYPTION();
        aes_encryption.init();

        String query = "SELECT * FROM staff WHERE username = \"" + this.username +
                        "\" AND password = \"" + aes_encryption.encrypt(password) +"\";";

        try (Connection con = DriverManager.getConnection(DBDriver.URL + DBDriver.DBNAME, DBDriver.USER, DBDriver.PASSWORD)) {

            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while(res.next()) {
                return res.getInt("staffId");
            }

            return 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
}
