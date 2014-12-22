package cz.muni.fi.tplevko.techsupport.utils;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Random salt and password generator
 *
 * @author tplevko
 */
public class GenerateEmployeePassword {

    private static final String ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUM = "0123456789";
    private static final String SPL_CHARS = "!@#$%^&*_=+-/";

    private static final int PASSWORD_LENGTH = 10;
    private static final int noOfCAPSAlpha = 1;
    private static final int noOfDigits = 1;
    private static final int noOfSplChars = 1;

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
     * method used to generate random passwd. This password is only temporary.
     *
     * @return generated alphanumeric passwd, can contain also special symbol
     * its length will be PASSWORD_LENGTH.
     */
    public static String generatePasswd() {

        int len = RANDOM.nextInt(PASSWORD_LENGTH);
        char[] pswd = new char[len];
        int index = 0;
        for (int i = 0; i < noOfCAPSAlpha; i++) {
            index = getNextIndex(RANDOM, len, pswd);
            pswd[index] = ALPHA_CAPS.charAt(RANDOM.nextInt(ALPHA_CAPS.length()));
        }
        for (int i = 0; i < noOfDigits; i++) {
            index = getNextIndex(RANDOM, len, pswd);
            pswd[index] = NUM.charAt(RANDOM.nextInt(NUM.length()));
        }
        for (int i = 0; i < noOfSplChars; i++) {
            index = getNextIndex(RANDOM, len, pswd);
            pswd[index] = SPL_CHARS.charAt(RANDOM.nextInt(SPL_CHARS.length()));
        }
        for (int i = 0; i < len; i++) {
            if (pswd[i] == 0) {
                pswd[i] = ALPHA.charAt(RANDOM.nextInt(ALPHA.length()));
            }
        }
        return new String(pswd);
    }

    private static int getNextIndex(SecureRandom rnd, int len, char[] pswd) {
        int index = rnd.nextInt(len);
        while (pswd[index = rnd.nextInt(len)] != 0);
        return index;
    }
}
