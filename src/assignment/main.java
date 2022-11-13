package assignment;

import java.sql.SQLException;

public class main {

    public static void main(String[] args) throws SQLException {
        Address add = new Address("B7A", "Hoyle Street", "Sheffield", "S37LG");
        add.createAddress();
        Customer cus = new Customer("Vivek","Choradia",add);
        cus.createCustomer();

    }
}
