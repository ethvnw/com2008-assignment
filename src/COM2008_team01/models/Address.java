/**
 * Represents an address.
 * @author Vivek V Choradia
 * @version 1.1
 */

package COM2008_team01.models;

import COM2008_team01.utilities.DBDriver;

import java.sql.*;

public class Address {

    String houseNum;
    private String road;
    private String city;
    String postcode;

    /**
     * Address constructor. Takes all the details of an address.
     * @param houseNum house number
     * @param road road
     * @param city city
     * @param postcode postcode
     */
    public Address(String houseNum, String road, String city, String postcode) {
        houseNum = houseNum.toUpperCase();
        road = road.substring(0,1).toUpperCase() + road.substring(1);
        city = city.substring(0,1).toUpperCase() + city.substring(1);
        postcode = postcode.replaceAll("\\s+","").toUpperCase();

        this.houseNum = houseNum;
        this.road = road;
        this.city = city;
        this.postcode = postcode;
    }

    /**
     * To insert an address in the database
     * @throws SQLException throws an SQL exception is an error is found while processing the query.
     */
    public void createAddress() throws SQLException {

        Address newAdd = findAddress(houseNum, postcode);
        if (newAdd == null) {
            String query = "INSERT INTO address(houseNum, road, city, postcode) " +
                    "VALUES(\"" + houseNum + "\", \"" + road + "\", \"" + city + "\", \"" + postcode + "\");";
            DBDriver.processQuery(query);
        }
    }

    /**
     * To find any existing address
     * @param houseNum house number
     * @param postcode postcode
     * @return an Address object
     * @throws SQLException throws an SQL exception is an error is found while processing the query.
     */
    public static Address findAddress(String houseNum, String postcode) throws SQLException {
        houseNum = houseNum.toUpperCase();
        postcode = postcode.replaceAll("\\s+","").toUpperCase();

        Statement stmt = DBDriver.getConnection().createStatement();

        String query = "SELECT * FROM address WHERE houseNum  = \"" + houseNum + "\" AND postcode = \"" + postcode + "\";";

        ResultSet res = stmt.executeQuery(query);

        while (res.next()) {
            return new Address(res.getString("houseNum"),
                    res.getString("road"),
                    res.getString("city"),
                    res.getString("postcode"));
        }

        res.close();

        return null;
    }

    /**
     * To update the address of the customer.
     * @param houseNum house number
     * @param road road
     * @param city city
     * @param postcode postcode
     */
    public void updateAddress(String houseNum, String road, String city, String postcode) throws SQLException {
        houseNum = houseNum.toUpperCase();
        road = road.substring(0,1).toUpperCase() + road.substring(1);
        city = city.substring(0,1).toUpperCase() + city.substring(1);
        postcode = postcode.replaceAll("\\s+","").toUpperCase();

        String query = "UPDATE address " +
                "SET houseNum = \"" + houseNum + "\"," +
                "road  = \"" + road + "\", " +
                "city = \"" + city + "\", " +
                "postcode = \"" + postcode + "\" " +
                " WHERE houseNum = \"" + this.houseNum + "\" AND postcode = \"" + this.postcode + "\" ; ";

        DBDriver.processQuery(query);

        this.houseNum = houseNum;
        this.road = road;
        this.city = city;
        this.postcode = postcode;
    }

    public String getHouseNum() {
        return houseNum;
    }

    public String getRoad() {
        return road;
    }

    public String getCity() {
        return city;
    }

    public String getPostcode() {
        return postcode;
    }
}
