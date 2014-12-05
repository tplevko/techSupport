package cz.muni.fi.tplevko.techsupport.utils;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 *
 * @author tplevko
 */
public class GenerateEmployeePassword {

    private static SecureRandom random = new SecureRandom();

    public static String generateSalt() {

        String salt = new BigInteger(100, random).toString(15);
        return salt;
    }
}
