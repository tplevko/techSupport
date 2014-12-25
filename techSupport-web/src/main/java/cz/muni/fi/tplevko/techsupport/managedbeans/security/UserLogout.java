package cz.muni.fi.tplevko.techsupport.managedbeans.security;

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.apache.shiro.SecurityUtils;

/**
 *
 * @author tplevko
 */
@ManagedBean(name = "logout")
@RequestScoped
public class UserLogout {
    
    
    public static final String HOME_URL = "/security/login.xhtml";

    public String logout() throws IOException {
        
        
        SecurityUtils.getSubject().logout();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        return "/security/login?faces-redirect=true";
    }
    
//    public void logout(HttpServletResponse response) throws ServletException, IOException {
//    request.getSession().invalidate();
//    response.sendRedirect(request.getContextPath()); 
//}

}
