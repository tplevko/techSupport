package cz.muni.fi.tplevko.secureappexample.managedbeans;

import cz.muni.fi.tplevko.secureappexample.entity.Account;
import cz.muni.fi.tplevko.secureappexample.managedbeans.security.ConfirmationEmailProducer;
import cz.muni.fi.tplevko.secureappexample.services.AccountService;
import cz.muni.fi.tplevko.secureappexample.utils.ShaEncoder;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.internet.AddressException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author tplevko
 */
//@Controller
@Component
@ManagedBean
@SessionScoped
public class RegistrationController implements Serializable {

    private static final long serialVersionUID = 1L;

//    @Autowired
    private FacesContext facesContext = FacesContext.getCurrentInstance();

    @Autowired
//    @Qualifier("registrationService")
    private ConfirmationEmailProducer confirmationEmailProducer;

    @Autowired(required = true)
//    @Qualifier("accountService")
    private AccountService accountService;

    public String name;
    public String email;
    public String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //get all accounts data from database
    public List<Account> getCustomerList() {
        return accountService.getAllAccounts();
    }

    //add a new account data into database
    public void addCustomer() throws AddressException {

        String salt;
        String passwordHash;

        salt = ShaEncoder.generateSalt();
        passwordHash = ShaEncoder.sha256hash(password, salt);

        try {

            Account account = new Account();
            account.setName(name);
            account.setPassword(passwordHash);
            account.setSalt(salt);
            account.setActive(false);
            account.setEmail(email);
            accountService.createAccount(account);

//            userDao.createUser(user);
            String message = "account successfully created";
//            facesContext.addMessage(null, new FacesMessage(message));

////////////////////////////
//
//         // TODO : allow this...
//
//            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correct", "Correct");
//
//            FacesContext.getCurrentInstance().addMessage(null, msg);
////////////////////////////

            Account newAccount = accountService.findAccountByEmail(email);

            // TODO : sprava o uspechu
            confirmationEmailProducer.sendMail(name, newAccount.getSalt(), email);

        } catch (Exception e) {

            // TODO : sprava o neuspechu
//            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getLocalizedMessage(), "Registration unsuccessful");
//            facesContext.addMessage(null, m);
        }
    }

    //clear form values
//    private void clearForm() {
//        setName("");
//        setAddress("");
//    }
}
