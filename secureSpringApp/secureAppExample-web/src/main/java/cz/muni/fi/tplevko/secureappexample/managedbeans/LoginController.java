package cz.muni.fi.tplevko.secureappexample.managedbeans;

import cz.muni.fi.tplevko.secureappexample.services.AccountServiceImpl;
import java.io.IOException;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.WebAttributes;

/**
 *
 * @author tplevko
 */
//@ManagedBean(name = "loginController")
//@RequestScoped
public class LoginController {// implements PhaseListener {
//
//    private static final Logger log = Logger.getLogger(LoginController.class.getName());
//    
//    
//    /**
//     *
//     * Redirects the login request directly to spring security check. Leave this
//     * method as it is to properly support spring security.
//     *
//     * @return
//     * @throws ServletException
//     * @throws IOException
//     */
//    public String doLogin() throws ServletException, IOException {
//        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
//
//        RequestDispatcher dispatcher = ((ServletRequest) context.getRequest())
//                .getRequestDispatcher("/j_spring_security_check");
//
//        dispatcher.forward((ServletRequest) context.getRequest(),
//                (ServletResponse) context.getResponse());
//
//        FacesContext.getCurrentInstance().responseComplete();
//
//        return null;
//    }
//
//    public void afterPhase(PhaseEvent event) {
//    }
//
//    /* (non-Javadoc)
//     * @see javax.faces.event.PhaseListener#beforePhase(javax.faces.event.PhaseEvent)
//     * 
//     * Do something before rendering phase.
//     */
//    public void beforePhase(PhaseEvent event) {
//        Exception e = (Exception) FacesContext.getCurrentInstance().
//                getExternalContext().getSessionMap().get(WebAttributes.AUTHENTICATION_EXCEPTION);
//
//        if (e instanceof BadCredentialsException) {
//            log.info("Found exception in session map: "+e);
//            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(
//                    WebAttributes.AUTHENTICATION_EXCEPTION, null);
//            FacesContext.getCurrentInstance().addMessage(null,
//                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
//                            "Username or password not valid.", "Username or password not valid"));
//        }
//    }
//
//    /* (non-Javadoc)
//     * @see javax.faces.event.PhaseListener#getPhaseId()
//     * 
//     * In which phase you want to interfere?
//     */
//    public PhaseId getPhaseId() {
//        return PhaseId.RENDER_RESPONSE;
//    }
}
