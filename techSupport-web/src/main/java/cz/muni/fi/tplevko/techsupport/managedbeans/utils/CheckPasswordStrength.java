package cz.muni.fi.tplevko.techsupport.managedbeans.utils;

/**
 * The class is used, for password strength validation.
 *
 * @author tplevko
 */
public class CheckPasswordStrength {

    private String checkPasswordStrength(String password) {

        String passMessage = "the password must contain at least one Upper case letter, one special symbol and one digit"
                + " and be 8 letters long.";
        String okMessage = "password strength OK";

        String[] partialRegexChecks = {".*[a-z]+.*", // lower
            ".*[A-Z]+.*", // upper
            ".*[\\d]+.*", // digits
            ".*[@#$%]+.*" // symbols
                
//                !,@,#,$,%,^,&,*,?,_,~
    };

        if (!password.matches(partialRegexChecks[0])) {
            return passMessage;
        }
        if (!password.matches(partialRegexChecks[1])) {
            return passMessage;
        }
        if (!password.matches(partialRegexChecks[2])) {
            return passMessage;
        }
        if (!password.matches(partialRegexChecks[3])) {
            return passMessage;
        }
        if (!(password.length() > 7)) {
            return passMessage;
        }

        return okMessage;
    }

}
