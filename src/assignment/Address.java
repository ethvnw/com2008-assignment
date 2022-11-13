package assignment;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Address {

    String houseNum;
    private String road;
    private String city;
    String postcode;

    public Address(String houseNum, String road, String city, String postcode){
        this.houseNum = houseNum;
        this.road = road;
        this.city = city;
        this.postcode = postcode;
    }



    public void createAddress() {
        String query = "INSERT INTO address" +
                        "VALUES("+houseNum + ", "+ road + ", "+ city + ", "+ postcode + ");";

        DBDriver.processInsertQuery(query);
    }

    public static Address findAddress(String houseNum, String postcode) throws SQLException {

        String query = "SELECT FROM address WHERE houseNum  = " + houseNum + "AND postcode = " + postcode + ";";
        ResultSet res = DBDriver.processGetOutput(query);

        Address add;

        if (res != null) {
            add = new Address(res.getString("houseNum"),
                    res.getString("road"),
                    res.getString("city"),
                    res.getString("postcode"));
            return add;
        }

        return null;
    }



}
