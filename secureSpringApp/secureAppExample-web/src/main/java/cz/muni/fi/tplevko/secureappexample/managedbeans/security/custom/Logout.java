package cz.muni.fi.tplevko.secureappexample.managedbeans.security.custom;

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.apache.shiro.SecurityUtils;

/**
 *
 * @author tplevko
 */
@ManagedBean(name = "logout")
@RequestScoped
public class Logout {
    
    
    public static final String HOME_URL = "login.xhtml";

    public void logout() throws IOException {
        
        
        SecurityUtils.getSubject().logout();
//        Faces.invalidateSession();
//        Faces.redirect(HOME_URL);
    }

}
