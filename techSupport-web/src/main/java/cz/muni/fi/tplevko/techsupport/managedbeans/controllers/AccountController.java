package cz.muni.fi.tplevko.techsupport.managedbeans.controllers;

import cz.muni.fi.tplevko.techsupport.entity.dto.AccountDto;
import cz.muni.fi.tplevko.techsupport.managedbeans.utils.UserProfileHandler;
import cz.muni.fi.tplevko.techsupport.services.AccountService;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import org.picketlink.common.constants.GeneralConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author tplevko
 */
@Component(value = "accountController")
//@ManagedBean
@Scope("request")
public class AccountController {

    private static final Logger LOG = Logger.getLogger(AccountController.class.getName());

    @Autowired
    private UserProfileHandler userProfileHandler;

    @Autowired
    private AccountService accountService;

    @Autowired
    private HttpSession httpSession;

    private AccountDto account = new AccountDto();

    public AccountDto getAccount() {
        return account;
    }

    public void setAccount(AccountDto account) {
        this.account = account;
    }

    private String firstName;
    private String surname;
    private String userEmail;

    @PostConstruct
    private void init() {

        account = userProfileHandler.getUserProfile();
        
        LOG.info("********************");
        LOG.info("********************");
        LOG.info("emailAddress : " + account.getEmail());
        LOG.info("givenName : " + account.getFirstName());
        LOG.info("surname : " + account.getLastName());
        LOG.info("***************************");
        LOG.info("***************************");

    }
//
//    public String editAccountBefore() {
////        Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
////        Long customerId = Long.valueOf(parameterMap.get("customerId"));
////        customer = customerService.findCustomerById(customerId);
//
//        return "/profile/editProfile?faces-redirect=true";
//    }
//
//    public String editAccountInfo() {
////
////        userProfileHandler.userProfileGet();
//
//        accountService.updateAccount(account);
//        return "/profile/myProfile?faces-redirect=true";
//    }

    public String getUserAccount() {

//        userProfileHandler.getCurrentUser();
        account = userProfileGet();
//
//        userProfileHandler.userProfileGet();

        return "/profile/myProfile?faces-redirect=true";
    }

    public AccountDto userProfileGet() {

        AccountDto userProfile = accountService.findAccountByEmail(userEmail);
//
//        if (userProfile == null) {
//
//            userProfile = createUserProfile();
//        }

        return userProfile;
    }

//    private AccountDto createUserProfile() {
//
//        AccountDto userProfile = new AccountDto();
//
//        userProfile.setActive(true);
//        userProfile.setEmail(userEmail);
//        userProfile.setFirstName(firstName);
//        userProfile.setLastName(surname);
//
//        accountService.createAccount(userProfile);
//
//        return accountService.findAccountByEmail(userEmail);
//    }

}
