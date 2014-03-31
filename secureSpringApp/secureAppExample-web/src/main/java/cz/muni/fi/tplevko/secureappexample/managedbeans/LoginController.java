package cz.muni.fi.tplevko.secureappexample.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.springframework.stereotype.Component;
import sun.security.krb5.internal.ccache.Credentials;

/**
 *
 * @author tplevko
 */
@Component
@ManagedBean
@SessionScoped
public class LoginController {

//    private final Credentials credentials = new Credentials(null);
    
    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void authenticate() {
        
    }
    
}
