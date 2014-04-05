package cz.muni.fi.tplevko.secureappexample.managedbeans.security;

import cz.muni.fi.tplevko.secureappexample.utils.ShaEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author tplevko
 */
@Component("confirmationEmailMessage")
public class ConfirmationEmailMessage {

    // TODO : na toto pouzit asi nejaku systemovu property, ktora bude v konfiguraku
    private String url = "http://localhost:8080/secureAppExample/Email";

    public ConfirmationEmailMessage() {
    }
    
    // TODO : dat to do properties... 
    public String generateMessage(String userName, String emailAddress, String salt) {
        
        String hash = ShaEncoder.hash(emailAddress, salt);
        
        String message = "Dear " + userName + "\n" +
                "you have registered on our site...\n" + 
                "please, confirm by clicking on the link, that you registered to our site : \n \n" +
                " " + url + "?verify=" + hash + "&" + "email=" + emailAddress +" ";
        
        return message;
    }
    
    private String generateUniqueSiteUrl () {
        
        return null;
    }
}