package assignment.models;

/** To process Encryption/Decryption for staff login.
 * @author Vivek V Choradia
 * @version 1.0
 * @lastUpdated 14-11-2022 10:37
 */

// TODO Update decryption (make it work)


///////////////////////////////////////
//CHANGE THIS FILE -- COPIED FROM THE INTERNET
//https://www.section.io/engineering-education/implementing-aes-encryption-and-decryption-in-java/
///////////////////////////////////////

import java.util.Base64;
import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

public class Encryption {
    private static final String UNICODE_FORMAT = "UTF8";
    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    private KeySpec ks;
    private SecretKeyFactory skf;
    private Cipher cipher;
    byte[] arrayBytes;
    private String myEncryptionKey;
    private String myEncryptionScheme;
    SecretKey key;
    public Encryption() throws Exception {
        myEncryptionKey = "ThisIsSpartaThisIsSparta";
        myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
        arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
        ks = new DESedeKeySpec(arrayBytes);
        skf = SecretKeyFactory.getInstance(myEncryptionScheme);
        cipher = Cipher.getInstance(myEncryptionScheme);
        key = skf.generateSecret(ks);
    }
    public String encrypt(String unencryptedString) {
        String encryptedString = null;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plainText);
            encryptedString = new String(Base64.getEncoder().encodeToString(encryptedText));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedString;
    }
    public String decrypt(String encryptedString) {
        String decryptedText=null;
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptedText = Base64.getDecoder().decode(encryptedString);
            byte[] plainText = cipher.doFinal(encryptedText);
            decryptedText= new String(plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedText;
    }
}

//
//public class AES_ENCRYPTION {
//    private SecretKey key;
//    private final int KEY_SIZE = 128;
//    private final int DATA_LENGTH = 128;
//    private Cipher encryptionCipher;
//
//    public void init() throws Exception {
//        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//        keyGenerator.init(KEY_SIZE);
//        key = keyGenerator.generateKey();
//    }
//
//    public String encrypt(String data) throws Exception {
//        byte[] dataInBytes = data.getBytes();
//        encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
//        encryptionCipher.init(Cipher.ENCRYPT_MODE, key);
//        byte[] encryptedBytes = encryptionCipher.doFinal(dataInBytes);
//
//        return encode(encryptedBytes);
//    }
//
//    public String decrypt(String encryptedData) throws Exception {
//        byte[] dataInBytes = decode(encryptedData);
//        Cipher decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
//        GCMParameterSpec spec = new GCMParameterSpec(DATA_LENGTH, decryptionCipher.getIV());
//        decryptionCipher.init(Cipher.DECRYPT_MODE, key, spec);
//        byte[] decryptedBytes = decryptionCipher.doFinal(dataInBytes);
//
//        return new String(decryptedBytes);
//    }
//
//    private String encode(byte[] data) {
//        return Base64.getEncoder().encodeToString(data);
//    }
//
//    private byte[] decode(String data) {
//        return Base64.getDecoder().decode(data);
//    }
//}
