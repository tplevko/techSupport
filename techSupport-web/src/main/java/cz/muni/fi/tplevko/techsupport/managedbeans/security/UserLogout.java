package cz.muni.fi.tplevko.techsupport.managedbeans.security;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * Log out class, used for logging out of system users
 *
 * @author tplevko
 */
@ManagedBean(name = "logout")
@RequestScoped
public class UserLogout {

    public String logout() {
        
//        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "?GLO=true";
    }
}
