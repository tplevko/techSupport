package cz.muni.fi.tplevko.techsupport.managedbeans.security;

import cz.muni.fi.tplevko.techsupport.entity.dto.CustomerDto;
import cz.muni.fi.tplevko.techsupport.entity.dto.PasswordChangeDto;
import cz.muni.fi.tplevko.techsupport.managedbeans.security.confirm.ConfirmationValidateEmailProducer;
import cz.muni.fi.tplevko.techsupport.services.CustomerService;
import cz.muni.fi.tplevko.techsupport.services.PasswordChangeService;
import cz.muni.fi.tplevko.techsupport.utils.ShaEncoder;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.mail.internet.AddressException;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author tplevko
 */
//@Controller
@Component
@ManagedBean
@Scope("request")
public class RegistrationController implements Serializable {

    private static final long serialVersionUID = 1L;

//    @Autowired
    private FacesContext facesContext = FacesContext.getCurrentInstance();

    @Autowired
    private PasswordChangeService activationController;

    @Autowired
//    @Qualifier("registrationService")
    private ConfirmationValidateEmailProducer confirmationEmailProducer;

    @Autowired(required = true)
//    @Qualifier("accountService")
    private CustomerService customerService;

    public String firstName;
    public String lastName;
    public String email;
    public String password;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
    public List<CustomerDto> getCustomerList() {
        return customerService.getAllCustomers();
    }

    //add a new account data into database
    public String addCustomer() throws AddressException, IOException {

        String salt;
        Sha256Hash passwordHash;

        salt = ShaEncoder.generateSalt();
        passwordHash = ShaEncoder.hash(password, salt);

        try {
            CustomerDto customer = new CustomerDto();
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setPassword(passwordHash.toHex());
            customer.setSalt(salt);
            customer.setActive(false);
            customer.setEmail(email);
            customerService.createCustomer(customer);
            
            String message = "account successfully created";
            facesContext.addMessage(null, new FacesMessage(message));

////////////////////////////
//
//         // TODO : allow this...
//
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correct", "Correct");

            FacesContext.getCurrentInstance().addMessage("sample message", msg);
////////////////////////////
            CustomerDto newAccount = customerService.findCustomerByEmail(email);
            PasswordChangeDto activationRequest = new PasswordChangeDto();
            activationRequest.setRequester(newAccount);
            
            String activationUuid = activationController.createPasswordChange(activationRequest);

            confirmationEmailProducer.generateMessage(activationUuid, email);

        } catch (Exception e) {

            return "/error?faces-redirect=true";

            // TODO : sprava o neuspechu
//            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getLocalizedMessage(), "Registration unsuccessful");
//            facesContext.addMessage(null, m);
        }
        // TODO : presmerovat na inu stranku, s instrukciami pre dokoncenie registracie...
        // este by sa nemal moct lognut do systemu... este len musi dokoncit reg...
        return "/successRedirect?faces-redirect=true";

    }
}
