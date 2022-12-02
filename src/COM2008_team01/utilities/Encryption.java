/** To process Encryption/Decryption for staff login.
 * @author Vivek V Choradia
 * @version 1.0

 */

package COM2008_team01.utilities;

import java.util.Base64;
import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

public class Encryption {
    private static final String UNICODE_FORMAT = "UTF8";
    public static final String ENCRYPTION_SCHEME = "DESede";
    private final Cipher cipher;
    byte[] arrayBytes;
    SecretKey key;

    public Encryption() throws Exception {
        String encryptionKey = "ThisisSpartaThisisSparta";
        String encryptionScheme = ENCRYPTION_SCHEME;
        arrayBytes = encryptionKey.getBytes(UNICODE_FORMAT);
        KeySpec ks = new DESedeKeySpec(arrayBytes);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(encryptionScheme);
        cipher = Cipher.getInstance(encryptionScheme);
        key = skf.generateSecret(ks);
    }

    /**
     * To encrypt the given password
     * @param unencryptedString password
     * @return encrypted password
     */
    public String encrypt(String unencryptedString) {
        String encryptedString = null;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plainText);
            encryptedString = Base64.getEncoder().encodeToString(encryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedString;
    }

    /**
     * To decrypt the password
     * @param encryptedString password
     * @return decrypted password
     */
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