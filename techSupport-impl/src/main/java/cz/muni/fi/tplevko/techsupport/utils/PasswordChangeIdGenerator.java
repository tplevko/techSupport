//package cz.muni.fi.tplevko.techsupport.utils;
//
///**
// *
// * @author tplevko
// */
//public class PasswordChangeIdGenerator {
//
//    // Generate unique string - use the email address appended at the end and the generated string...
//    public static String getId(String email) {
//
//        String randomSeed = ShaEncoder.generateSalt();
//
//        String stringToCompare = ShaEncoder.sha256hash(email, randomSeed);
//
//        stringToCompare += email;
//
//        return stringToCompare;
//    }
//
//}
