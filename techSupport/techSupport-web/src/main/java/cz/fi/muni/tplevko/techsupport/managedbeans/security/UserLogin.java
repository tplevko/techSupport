package cz.fi.muni.tplevko.techsupport.managedbeans.security;

import cz.muni.fi.tplevko.techsupport.services.CustomerService;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author tplevko
 */
@ManagedBean(name = "login")
@RequestScoped
public class UserLogin {

//	@Inject
//	private Credentials credentials;
//    @Autowired
//    private AccountService accountService;

    public static final String HOME_URL = "app/index.xhtml";

    private String username;
    private String password;
    private boolean remember;

    public String submit() throws IOException {
        try {
            
            // TODO : zmen to rememberme
            SecurityUtils.getSubject().login(new UsernamePasswordToken(username, password, true));
//            SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(Faces.getRequest());
//            Faces.redirect(savedRequest != null ? savedRequest.getRequestUrl() : HOME_URL);
       
        return "/security/login?faces-redirect=true";
        
        } catch (AuthenticationException e) {

            // TODO : nejak to inac vypisat, nie pomocou tych omnyfacov...
//            Messages.addGlobalError("Unknown user, please try again");
            e.printStackTrace(); // TODO: logger.
            
        return "/error?faces-redirect=true";
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
//
    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

}
