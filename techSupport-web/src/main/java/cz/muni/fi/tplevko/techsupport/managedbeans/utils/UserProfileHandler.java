package cz.muni.fi.tplevko.techsupport.managedbeans.utils;

import cz.muni.fi.tplevko.techsupport.entity.dto.AccountDto;
import cz.muni.fi.tplevko.techsupport.services.AccountService;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.picketlink.common.constants.GeneralConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 *
 * @author tplevko
 */
//@Component(value = "userProfileHandler")
@Service
@Scope("request")
public class UserProfileHandler {

    @Autowired
    private AccountService accountService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private HttpServletRequest request;

    private static final Logger LOG = Logger.getLogger(UserProfileHandler.class.getName());

    private String firstName;
    private String surname;
    private String userEmail;

    private AccountDto userProfile;

    @PostConstruct
    private void init() {
        Map<String, List<Object>> sessionMap
                = (Map<String, List<Object>>) httpSession.getAttribute(GeneralConstants.SESSION_ATTRIBUTE_MAP);

        Set<String> keySet = sessionMap.keySet();

        userEmail = (String) sessionMap.get("emailAddress").get(0);
        surname = (String) sessionMap.get("givenName").get(0);
        firstName = (String) sessionMap.get("givenName").get(0);
//        userEmail = "aaa";
//        surname = "aaa";
//        firstName = "aaa@gmail.com";

        LOG.info("***************************");
        LOG.info("***************************");
        LOG.info(keySet.toString());
        LOG.info("emailAddress : " + userEmail);
        LOG.info("givenName : " + firstName);
        LOG.info("surname : " + surname);
        LOG.info("userid : " + (String) sessionMap.get("userid").get(0));
        LOG.info("***************************");
        LOG.info("***************************");
        userProfile = new AccountDto();
        userProfile = userProfileGet();

    }

    private AccountDto userProfileGet() {

        AccountDto userProfile = accountService.findAccountByEmail(userEmail);

        if (userProfile == null) {

            userProfile = createUserProfile();
        }

        return userProfile;
    }

    public AccountDto getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(AccountDto userProfile) {
        this.userProfile = userProfile;
    }

    private AccountDto createUserProfile() {

        AccountDto userProfile = new AccountDto();

        userProfile.setActive(true);
        userProfile.setEmail(userEmail);
        userProfile.setFirstName(firstName);
        userProfile.setLastName(surname);

        accountService.createAccount(userProfile);

        return accountService.findAccountByEmail(userEmail);
    }

    public void showSessionInfo() {

        request.getUserPrincipal().getName();
        
//        HttpSession thisSession = request.getSession();
        
        Map<String, List<Object>> sessionMap = (Map<String, List<Object>>) httpSession.getAttribute(GeneralConstants.SESSION_ATTRIBUTE_MAP);

        Set<String> keySet = new HashSet<>();
        keySet = sessionMap.keySet();
        userEmail = (String) sessionMap.get("emailAddress").get(0);
        surname = (String) sessionMap.get("givenName").get(0);
        firstName = (String) sessionMap.get("surname").get(0);

        LOG.info("***************************");
        LOG.info("***************************");
        LOG.info(keySet.toString());
        LOG.info("user email : " + userEmail);
        LOG.info("user surname : " + surname);
        LOG.info("first name " + firstName);
        LOG.info("***************************");
        LOG.info("***************************");
    }
}
