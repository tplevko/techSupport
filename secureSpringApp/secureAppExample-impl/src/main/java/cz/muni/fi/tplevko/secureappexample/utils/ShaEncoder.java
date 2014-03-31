package cz.muni.fi.tplevko.secureappexample.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author tplevko
 */
public class ShaEncoder {

    private static SecureRandom random = new SecureRandom();
    private static final int ITERATIONS = 100;
    private static final int KEY_LENGTH = 256;

    public static String generateSalt() {

        String salt = new BigInteger(64, random).toString(64);
        return salt;
    }

    public static Map<String, String> generatePassword(String passwd) {

        Map<String, String> passMap = new HashMap<>();

        String salt = generateSalt();
        // TODO : validate if passwd is strong enough
        String passHash = hash(passwd, salt);

        passMap.put("salt", salt);
        passMap.put("passHash", passHash);

        return passMap;
    }

    public static String hash(String passwd, String salt) {
       
        if (passwd != null && salt != null) {
            String saltedPasswd = passwd + salt;

            try {

                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(saltedPasswd.getBytes("UTF-8"));

                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < hash.length; i++) {
                    sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
                }

                return sb.toString();

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    
    
    
    
    
    
    
    
    public static byte[] hash(char[] password, byte[] salt) {
//        char[] pwd = cloneArrayAndEraseOriginal(password);
        KeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        try {
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            return secretKeyFactory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        }
    }
}
