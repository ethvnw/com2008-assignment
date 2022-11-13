package assignment;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer {
    private int customerID;
    private String forename;
    private String surname;
    Address address;


    public Customer(String forename, String surname, Address address) throws SQLException {
        this.forename = forename;
        this.surname = surname;
        this.address = address;
    }


    public Customer(int customerID, String forename, String surname, Address address) throws SQLException {
        this.customerID = customerID;
        this.forename = forename;
        this.surname = surname;
        this.address = address;
    }

    public boolean createCustomer(){

        String query = "INSERT INTO customer(forename, surname, houseNum, postcode)" +
                        "VALUES ("+ this.forename +", "+
                                this.surname +", " + this.address.houseNum + ", " +
                                this.address.postcode + ");";

        DBDriver.processInsertQuery(query);
        return false;
    }

    public Customer getCustomer(int customerID) throws SQLException {
        String query = "SELECT * FROM customer where customerID = " + customerID + ";";
        ResultSet res = DBDriver.processGetOutput(query);
        Customer cus;
        if(res != null) {
            String forename = res.getString("forename");
            String lastname = res.getString("surname");
            Address add = Address.findAddress(res.getString("houseNum"), res.getString("postcode"));
            cus = new Customer(customerID, forename, lastname, add);
            return cus;
        }
        return null;
    }


    public void getAddress() throws SQLException {

//        this.address = Address.findAddress(this.houseNum, this.postcode);

    }
//    public void getAddress(){}






}
