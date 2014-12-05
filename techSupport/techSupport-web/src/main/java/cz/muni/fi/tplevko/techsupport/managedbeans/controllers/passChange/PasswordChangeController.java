package cz.muni.fi.tplevko.techsupport.managedbeans.controllers.passChange;

import cz.muni.fi.tplevko.techsupport.entity.dto.CustomerDto;
import cz.muni.fi.tplevko.techsupport.entity.dto.PasswordChangeDto;
import cz.muni.fi.tplevko.techsupport.services.CustomerService;
import cz.muni.fi.tplevko.techsupport.services.PasswordChangeService;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author tplevko
 */
@Component(value = "passwordChangeController")
@ManagedBean
@Scope("request")
public class PasswordChangeController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PasswordChangeService passChangeService;

    private static final Logger LOG = Logger.getLogger(PasswordChangeController.class.getName());

    private String userEmail;

    @PostConstruct
    public void init() {
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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
    public String resetUserPassword() {

        PasswordChangeDto passwordChangeDto = new PasswordChangeDto();

        LOG.info("the user filled passChange request, email is : " + userEmail);

        CustomerDto passChangeRequester = customerService.findCustomerByEmail(userEmail);
        passwordChangeDto.setRequester(passChangeRequester);

        passChangeService.createPasswordChange(passwordChangeDto);
        
        return "/registration/forgotPassRedirect?faces-redirect=true";
    }

}
