package cz.muni.fi.tplevko.techsupport.managedbeans.security.confirm;

import java.util.Properties;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author tplevko
 */
@Component("emailProducer")
public class EmailProducer {

    private static final Logger LOG = Logger.getLogger(EmailProducer.class.getName());

    // Def value = xplevko@gmail.com
    @Value("${info.def.email}")
    private String adminEmail;

    @Value("${info.def.emailpassword}")
    private String adminEmailPasswd;

    @Value("${mail.smtp.host}")
    private String smtpHost;     // Def value = smtp.gmail.com

    @Value("${mail.smtp.socketFactory.port}")
    private String smtpSocketFactoryPort;     // Def value = 465

    @Value("${mail.smtp.socketFactory}")
    private String smtpSocketFactory;     // Def value = ssl

    @Value("${mail.smtp.auth}")
    private String smtpAuth;     // Def value = true

    @Value("${mail.smtp.port}")
    private String smtpPort;     // Def value = 465

    public static void sendConfirmationEmail() {
    }

    public void sendMail(String emailText, String emailSubject, String userEmailAddress) {

        Properties props = new Properties();

        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.socketFactory.port", smtpSocketFactoryPort);
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", smtpAuth);
        props.put("mail.smtp.port", smtpPort);

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(adminEmail, adminEmailPasswd);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(userEmailAddress));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(userEmailAddress));
            message.setSubject(emailSubject);

            message.setText(emailText);
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
