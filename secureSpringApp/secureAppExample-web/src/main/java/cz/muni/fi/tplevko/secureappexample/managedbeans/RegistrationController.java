package cz.muni.fi.tplevko.secureappexample.managedbeans;

import cz.muni.fi.tplevko.secureappexample.entity.Account;
import cz.muni.fi.tplevko.secureappexample.services.AccountService;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

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

    @Autowired(required = true)
//    @Qualifier("accountService")
    AccountService accountService;

    public String name;
    public String email;
    public String password;
    public String seccondPassword;

    public String getSeccondPassword() {
        return seccondPassword;
    }

    public void setSeccondPassword(String seccondPassword) {
        this.seccondPassword = seccondPassword;
    }

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

        Account account = new Account();
        account.setName(name);
        account.setPassword(password);
        account.setSalt("salt");
        account.setActive(false);
        account.setEmail(email);

        accountService.createAccount(account);

//        return "yolo";
    }

    //clear form values
//    private void clearForm() {
//        setName("");
//        setAddress("");
//    }
}
