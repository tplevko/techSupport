package cz.muni.fi.tplevko.techsupport.managedbeans.security.confirm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author tplevko
 */
@Component("confirmationValidateEmailProducer")
public class ConfirmationValidateEmailProducer {

    @Value("${service.url}")
    private String serviceUrl; // Def value = localhost  
    // will be changed for various PAAS-es

    @Value("${service.port}")
    private String servicePort;     // Def value = 8080

    @Value("${service.name}")
    private String serviceName;     // Def value = /techSupport

    @Value("${service.protocol}")
    private String serviceProtocol;     // Def value = http://

    @Value("${email.user.registered}")
    private String emailMessage;

    @Value("${email.user.register.subject}")
    private String emailSubject;

    @Autowired
    private EmailProducer emailProducer;

    /**
     * This method generates the verification email, for the user and uses
     * emailProducer, to send it. The message is generated from the properties
     * im emailMessages.properties
     *
     * @param generatedPassChangeId
     * @param userEmail
     */
    public void generateMessage(String generatedPassChangeId, String userEmail) {

        emailProducer.sendMail(generateUniqueSiteUrl(generatedPassChangeId), emailSubject, userEmail);
    }

    private String generateUniqueSiteUrl(String generatedChangeId) {

        // TODO : change somehow...
//        localhost:8080/techSupport/registration/finishRegistration.xhtml?id=ff8081814a1bad54014a1badc8900000
        String serviceAddress = serviceProtocol + serviceUrl + servicePort + serviceName + "/registration/finishRegistration.xhtml?id=" + generatedChangeId;

        String fullEmailMessage = emailMessage + serviceAddress;

        return fullEmailMessage;
    }

}
