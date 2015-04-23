//package cz.muni.fi.tplevko.techsupport.utils;
//
//import java.io.UnsupportedEncodingException;
//import java.math.BigInteger;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.security.SecureRandom;
//import org.apache.shiro.crypto.hash.Sha256Hash;
//import org.apache.shiro.util.SimpleByteSource;
//
///**
// * The SHA256 encoder class, used for generating the password hashes, which will
// * be stored in the database.
// * 
// * @author tplevko
// */
//public class ShaEncoder {
//
//    private static SecureRandom random = new SecureRandom();
//
//    private static final int ITERATIONS = 100;
//    private static final int KEY_LENGTH = 256;
//    
//    /**
//     * method uses SecureRandom, for generating of the salt 
//     * 
//     * @return salt
//     */
//    public static String generateSalt() {
//
//        String salt = new BigInteger(64, random).toString(64);
//        return salt;
//    }
//    
//    public static int getIterations() {
//        return ITERATIONS;
//    }
//
//    /**
//     * method used for hashing of the input password in cleartext. 
//     * 
//     * @param passwd - the password in cleartext
//     * @param salt   - the salt, that is used with particular password, and is stored 
//     *                 in the DB with the passwd.
//     * @return       - hashed password
//     */
//    public static String sha256hash(String passwd, String salt) {
//
//        if (passwd != null && salt != null) {
//            String saltedPasswd = passwd + salt;
//
//            try {
//
//                MessageDigest digest = MessageDigest.getInstance("SHA-256");
//                byte[] hash = digest.digest(saltedPasswd.getBytes("UTF-8"));
//
//                StringBuffer sb = new StringBuffer();
//                for (int i = 0; i < hash.length; i++) {
//                    sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
//                }
//
//                return sb.toString();
//
//            } catch (NoSuchAlgorithmException e) {
//                e.printStackTrace();
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
//
//    public static Sha256Hash hash(String passwd, String salt) {
//        
//        Sha256Hash hash = null;
//        if (passwd != null && salt != null) {
//            hash = new Sha256Hash(passwd, new SimpleByteSource(salt).getBytes(), ITERATIONS);
//
//        }
//        return hash;
//    }
//}
