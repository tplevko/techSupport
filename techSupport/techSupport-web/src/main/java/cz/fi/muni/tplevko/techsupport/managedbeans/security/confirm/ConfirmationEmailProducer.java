package cz.fi.muni.tplevko.techsupport.managedbeans.security.confirm;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author tplevko
 */
@Component("confirmationEmailProducer")
public class ConfirmationEmailProducer {

    // TODO :
    /**
     * Classa, ktora vyprodukuje pre cerstvo zargistrovaneho uzivatela hash,
     * ktory bude vytvoreny na zaklade salt, ktora mu bola priradena a jeho
     * uzivatelskeho nicku...
     *
     * posle sa mu na maila vyzva, aby klikol na odkaz a pokial klikne, nastavi
     * sa mu flag na active...
     *
     */
    
    @Autowired
    private ConfirmationEmailMessage confirmationEmailMessage;
    

    public static void sendConfirmationEmail() {
    }

    public void sendMail(String userName, String salt, String userEmailAddress) {

        final String email = "xplevko@gmail.com";
        final String password = "xplevko@xplevko";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(email, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("tomasplevko@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(userEmailAddress));
            message.setSubject("Dear " + userName);
            
            String messageToSend = confirmationEmailMessage.generateMessage(userName, userEmailAddress, salt);
            message.setText(messageToSend);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
