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
    @PostConstruct
    private void init() {

        account = userProfileHandler.getUserProfile();
    }

    public String getUserAccount() {

        account = userProfileGet();

        return "/profile/myProfile?faces-redirect=true";
    }

    public AccountDto userProfileGet() {

        AccountDto userProfile = userProfileHandler.getUserProfile();

        return userProfile;
    }
}
