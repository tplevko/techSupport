package cz.muni.fi.tplevko.techsupport.managedbeans.controllers.passChange;

import cz.muni.fi.tplevko.techsupport.entity.dto.CustomerDto;
import cz.muni.fi.tplevko.techsupport.services.CustomerService;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author tplevko
 */
@Component
@Scope("request")
public class ForgotPasswordValidator implements Validator {

    @Autowired
    private CustomerService customerService;
 
    @Override
    public void validate(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {

        String emailAddress = value.toString();

        // Let required="true" do its job.
        if (emailAddress == null || emailAddress.isEmpty()) {
            return;
        }

//        CustomerDto customer = new CustomerDto();
        CustomerDto customer = customerService.findCustomerByEmail(emailAddress);

        if (customer == null) {
            throw new ValidatorException(new FacesMessage("The email entered is not present in our records. If you forgot your registration email, contact the administrator please"));
        }
    }
}
