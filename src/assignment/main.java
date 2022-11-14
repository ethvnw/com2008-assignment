package assignment;

import assignment.models.Staff;

public class main {
    public static void main(String[] args) throws Exception {
        Staff s1 = new Staff("vivek", "choradia");
        Staff s2 = new Staff("ray", "han");
        Staff s3 = new Staff("ethan","watts");
        Staff s4 = new Staff("natalie", "roberts");
        Staff s5 = new Staff("vivek2", "choradia");
//        s5.createStaff();

        System.out.println(s1.login());
        System.out.println(s2.login());
        System.out.println(s3.login());
        System.out.println(s4.login());
    }
}
