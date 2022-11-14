package assignment;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;

import javax.crypto.Cipher;

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
}
