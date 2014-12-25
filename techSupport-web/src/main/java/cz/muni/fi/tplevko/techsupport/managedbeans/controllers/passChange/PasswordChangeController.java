package cz.muni.fi.tplevko.techsupport.managedbeans.controllers.passChange;

import cz.muni.fi.tplevko.techsupport.entity.dto.CustomerDto;
import cz.muni.fi.tplevko.techsupport.entity.dto.PasswordChangeDto;
import cz.muni.fi.tplevko.techsupport.managedbeans.security.confirm.ConfirmationEmailPassChangeMessage;
import cz.muni.fi.tplevko.techsupport.services.CustomerService;
import cz.muni.fi.tplevko.techsupport.services.PasswordChangeService;
import cz.muni.fi.tplevko.techsupport.utils.ShaEncoder;
import java.util.Date;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author tplevko
 */
@Component(value = "passwordChangeController")
@ManagedBean
@Scope("session")
public class PasswordChangeController {
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private PasswordChangeService passChangeService;
    
    @Autowired
    private ConfirmationEmailPassChangeMessage confirmationEmailPassChangeMessage;
    
    private static final Logger LOG = Logger.getLogger(PasswordChangeController.class.getName());
    
    private String userEmail;
    private String uuid;
    private String password;
        
    public String getUserEmail() {
        return userEmail;
    }
    
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    
    public String getUuid() {
        return uuid;
    }
    
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * the method is used by users, to reset their passwords. The inserted user
     * email is first validated, and only if the required user exists in the
     * database, the email is sent, and the new change request is filled.
     *
     * ForgotPasswordValidator is used first to validate the request.
     *
     * @return
     */
    public String requestResetUserPassword() {
        
        PasswordChangeDto passwordChangeDto = new PasswordChangeDto();
        
        LOG.info("the user filled passChange request, email is : " + userEmail);
        
        CustomerDto passChangeRequester = customerService.findCustomerByEmail(userEmail);
        passwordChangeDto.setRequester(passChangeRequester);
        
        String id = passChangeService.createPasswordChange(passwordChangeDto);
        
        LOG.info("The user password request ID is : " + id);
        
        confirmationEmailPassChangeMessage.generateMessage(id, userEmail);
        
        return "/registration/forgotPassRedirect?faces-redirect=true";
    }
    
    
    /**
     * the method is used for reseting of the customer password.
     * it creates hash, using the hash function using the new password and the
     * old salt. The salt does not need to be changed I think... 
     * 
     * @return 
     */
    public String resetPassword() {
        
        // TODO : Najskor zistit, ci ten passChange nie je starsi ako jeden den... 
        
        PasswordChangeDto passChange = passChangeService.findPasswordChangeById(uuid);
        Long requesterId = passChange.getRequester().getId();
        CustomerDto customer = customerService.findCustomerById(requesterId);
        
        String salt = customer.getSalt();
        Sha256Hash passwordHash;
        
        passwordHash = ShaEncoder.hash(password, salt);
        customer.setPassword(passwordHash.toHex());
        
        customerService.updateCustomer(customer);
        
        passChange.setExecuted(true);
        passChange.setFinished(new Date());
        passChangeService.updatePasswordChange(passChange);

        return "/security/login?faces-redirect=true";
        
    }
    
}
