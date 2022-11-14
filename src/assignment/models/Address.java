package assignment.models;

import assignment.dbconnection.DBDriver;

import java.sql.*;

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



    public void createAddress() throws SQLException {

        Address newAdd = findAddress(houseNum, postcode);
        if (newAdd == null) {
            String query = "INSERT INTO address(houseNum, road, city, postcode) " +
                    "VALUES(\"" + houseNum + "\", \"" + road + "\", \"" + city + "\", \"" + postcode + "\");";
            DBDriver.processQuery(query);
        }
    }

    public static Address findAddress(String houseNum, String postcode) throws SQLException {

        try (Connection con = DriverManager.getConnection(DBDriver.URL + DBDriver.DBNAME, DBDriver.USER, DBDriver.PASSWORD)) {

            Statement stmt = con.createStatement();

            String query = "SELECT * FROM address WHERE houseNum  = \"" + houseNum + "\" AND postcode = \"" + postcode + "\";";

            ResultSet res = stmt.executeQuery(query);

            Address add;

            while (res.next()) {
                add = new Address(res.getString("houseNum"),
                        res.getString("road"),
                        res.getString("city"),
                        res.getString("postcode"));
                return add;
            }

            res.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        return null;
    }



}
