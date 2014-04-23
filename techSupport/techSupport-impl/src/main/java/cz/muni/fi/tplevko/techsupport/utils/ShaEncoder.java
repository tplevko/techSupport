package cz.muni.fi.tplevko.techsupport.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.util.SimpleByteSource;

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
    
    public static int getIterations() {
        return ITERATIONS;
    }

    public static String sha256hash(String passwd, String salt) {

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

    public static Sha256Hash hash(String passwd, String salt) {
        
        Sha256Hash hash = null;
        if (passwd != null && salt != null) {
            hash = new Sha256Hash(passwd, new SimpleByteSource(salt).getBytes(), ITERATIONS);

        }
        return hash;
    }

    
//    // TODO : not working properly... repair it... maybe also not, because shiro does not support it...
//    public static String generateStorngPasswordHash(String password, String salt) {
//
//        if (password != null && salt != null) {
////            String saltedPasswd = passwd + salt;
//            try {
//
//                char[] chars = password.toCharArray();
//                byte[] saltCahrs = salt.getBytes();
//
//                PBEKeySpec spec = new PBEKeySpec(chars, saltCahrs, ITERATIONS, 64 * 8);
//                SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
//                String hash = skf.generateSecret(spec).getEncoded().toString();
//
//                return hash;
//
//            } catch (NoSuchAlgorithmException e) {
//                e.printStackTrace();
//            } catch (InvalidKeySpecException e) {
//                e.printStackTrace();
//            }
//        }
//
////        PBEKeySpec spec = new PBEKeySpec(chars, saltCahrs, ITERATIONS, 64 * 8);
////        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
////        String hash = skf.generateSecret(spec).getEncoded().toString();
//        return null;
//    }
//
//    public boolean authenticate(String attemptedPassword, String encryptedPassword, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
//
//        String encryptedAttemptedPassword = generateStorngPasswordHash(attemptedPassword, salt);
//        return encryptedPassword.equals(encryptedAttemptedPassword);
//    }

//    public static byte[] hash(char[] password, byte[] salt) {
//        KeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
//        try {
//            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
//            return secretKeyFactory.generateSecret(spec).getEncoded();
//        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
//            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
//        }
//    }
}
