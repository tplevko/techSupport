package cz.muni.fi.tplevko.techsupport.managedbeans.security;

import cz.muni.fi.tplevko.techsupport.entity.dto.CustomerDto;
import cz.muni.fi.tplevko.techsupport.entity.dto.PasswordChangeDto;
import cz.muni.fi.tplevko.techsupport.services.CustomerService;
import cz.muni.fi.tplevko.techsupport.services.PasswordChangeService;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author tplevko
 */
@Component(value = "customerAccountActivation")
@ManagedBean
@Scope("request")
public class CustomerAccountActivation {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PasswordChangeService passChangeService;

    private static final Logger LOG = Logger.getLogger(CustomerAccountActivation.class.getName());

    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * method used for activation of the new user, is fired after accessing the
     * activation page with the uuid provided
     *
     * @return
     */
    public void validateCustomerAccount() throws IOException {

        LOG.info("user activation started, UUID : " + uuid);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

        try {

            PasswordChangeDto passChange = passChangeService.findPasswordChangeById(uuid);

            if (passChange.isExecuted()) {
                ec.redirect(ec.getRequestContextPath() + "/error.xhtml");
            } else {

                Long requesterId = passChange.getRequester().getId();
                CustomerDto customer = customerService.findCustomerById(requesterId);

                customer.setActive(true);
                customerService.updateCustomer(customer);

                passChange.setExecuted(true);
                passChange.setFinished(new Date());

                passChangeService.updatePasswordChange(passChange);

                ec.redirect(ec.getRequestContextPath() + "/security/login.xhtml");
            }

        } catch (Exception e) {

            e.printStackTrace();
            LOG.info(e.toString());
            ec.redirect(ec.getRequestContextPath() + "/error.xhtml");

        }
    }
}
