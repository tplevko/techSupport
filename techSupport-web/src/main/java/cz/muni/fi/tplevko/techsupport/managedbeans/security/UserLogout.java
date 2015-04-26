package cz.muni.fi.tplevko.techsupport.managedbeans.security;

import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.springframework.context.annotation.Scope;

/**
 * Log out class, used for logging out of system users
 *
 * @author tplevko
 */
@ManagedBean(name = "logout")
@Scope("request")
public class UserLogout {

    private static final Logger LOG = Logger.getLogger(UserLogout.class.getName());

    public String logout() {
        
        LOG.info("*********************");
        LOG.info("logging out user");
        LOG.info("*********************");

        // We need to end the session after the user was logged out on IDP side
        FacesContext.getCurrentInstance().getExternalContext().setSessionMaxInactiveInterval(2);
        return "/index.xhtml?GLO=true&faces-redirect=true";

    }
}
