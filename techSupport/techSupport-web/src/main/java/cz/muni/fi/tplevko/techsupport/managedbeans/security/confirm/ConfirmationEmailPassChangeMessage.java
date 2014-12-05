package cz.muni.fi.tplevko.techsupport.managedbeans.security.confirm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author tplevko
 */
@Component("confirmationEmailPassChangeMessage")
public class ConfirmationEmailPassChangeMessage {

    @Value("${service.url}")
    private String serviceUrl; // Def value = localhost  
    // will be changed for various PAAS-es

    @Value("${service.port}")
    private String servicePort;     // Def value = 8080

    @Value("${email.reset.password}")
    private String emailMessage;

    @Value("${email.reset.password.subject}")
    private String emailSubject;

    @Autowired
    private EmailProducer emailProducer;

    
    /**
     * This method generates the change email, for the user and uses emailProducer,
     * to send it. The message is generated from the properties im emailMessages.properties
     * 
     * @param generatedPassChangeId
     * @param userEmail 
     */
    public void generateMessage(String generatedPassChangeId, String userEmail) {

        emailProducer.sendMail(generateUniqueSiteUrl(generatedPassChangeId), emailSubject, userEmail);
    }

    private String generateUniqueSiteUrl(String generatedChangeId) {

        
        // TODO : change somehow...
        String serviceAddress = serviceUrl +":"+ servicePort +"/techSupport/Email?verify="+ generatedChangeId;
        
        String fullEmailMessage = emailMessage + serviceAddress;
        
        return fullEmailMessage;
    }
}
