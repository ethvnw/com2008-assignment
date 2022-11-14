package assignment.models;

/** Represents a Staff.
 * @author Vivek V Choradia
 * @version 1.0
 * @lastUpdated 14-11-2022 10:37
 */

import assignment.dbconnection.DBDriver;
import assignment.models.AES_ENCRYPTION;

import java.sql.*;
import java.util.Objects;

public class Staff {
    private String username;
    private String password;


//    Constructors
    public Staff(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void createStaff() throws Exception {
        AES_ENCRYPTION aes_encryption = new AES_ENCRYPTION();
        aes_encryption.init();

        String query = "INSERT INTO staff(username, password)" +
                        "VALUES(\""+ this.username + "\", \"" + aes_encryption.encrypt(password) + "\");";

        DBDriver.processQuery(query);
    }
    public boolean login() throws Exception {

        AES_ENCRYPTION aes_encryption = new AES_ENCRYPTION();
        aes_encryption.init();

        String query = "SELECT * FROM staff WHERE username = \"" + this.username + "\";";

        try (Connection con = DriverManager.getConnection(DBDriver.URL + DBDriver.DBNAME, DBDriver.USER, DBDriver.PASSWORD)) {

            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while(res.next()) {
                String password = aes_encryption.decrypt(res.getString("password"));
                return Objects.equals(password, this.password);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }


}
