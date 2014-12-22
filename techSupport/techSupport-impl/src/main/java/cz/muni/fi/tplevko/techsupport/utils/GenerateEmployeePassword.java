package cz.muni.fi.tplevko.techsupport.utils;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Random salt and password generator
 *
 * @author tplevko
 */
public class GenerateEmployeePassword {
    
    public static final int PASSWORD_LENGTH = 10;
    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * method used to generate salt
     *
     * @return
     */
    public static String generateSalt() {

        String salt = new BigInteger(100, RANDOM).toString(15);
        return salt;
    }

    /**
     * method used to generate random passwd. This password is only temporary, 
     * so it has no special politics, it should meet.
     * 
     * @return generated alphanumeric passwd, can contain also special symbol
     *         its length will be PASSWORD_LENGTH.
     */
    public static String generatePasswd() {
        // Pick from some letters that won't be easily mistaken for each
        // other. So, for example, omit o O and 0, 1 l and L.
        String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789+@#/-!?";

        String pw = "";
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int index = (int) (RANDOM.nextDouble() * letters.length());
            pw += letters.substring(index, index + 1);
        }
        return pw;
    }

}
