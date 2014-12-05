package cz.muni.fi.tplevko.techsupport.managedbeans.security.confirm;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 *
 * @author tplevko
 */
public class ResetPasswordServlet extends HttpServlet {

    private static final long serialVersionUID = 1;

    @Autowired
    private EmailConfirmation emailConfirmation;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        handleRequest(req, res);
    }

    public void handleRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {

        PrintWriter out = res.getWriter();
        res.setContentType("text/plain");
//
//        Enumeration<String> parameterNames = req.getParameterNames();
//        
        try {

            Enumeration<String> parameterNames = req.getParameterNames();
            if (parameterNames.hasMoreElements()) {
                String paramName = parameterNames.nextElement();
                if (paramName.equals("verify")) {
                    out.write(paramName + "\n");
                }
            }
            if (parameterNames.hasMoreElements()) {
                String paramName = parameterNames.nextElement();
                if (paramName.equals("email")) {
                    out.write(paramName + "\n");
                }
            }

            String verify = req.getParameter("verify");
            String email = req.getParameter("email");

            out.write(verify + "\n");
            out.write(email);

            // TODO : este spravit to presmerovanie
            boolean status = emailConfirmation.verifyAccount(email, verify);

            out.write(status ? "true" : "false");

            if (status) {
                res.sendRedirect("/techSupport/security/login.xhtml");
            } else {
                res.sendRedirect("/techSupport/registration/createAccount.xhtml");

            }

            // TODO : toto je na hovno chytanie vs. vynimiek
        } catch (Exception e) {

        } finally {
            out.close();
        }

    }

}
