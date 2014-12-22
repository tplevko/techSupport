package cz.muni.fi.tplevko.techsupport.managedbeans.security;

import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * This validator is responsible for the validation of user password strength, 
 * that must match the Password pattern. 
 * It also verifies, whether the password from second input field corresponds
 * with the first field
 * 
 * @author tplevko
 */
@Component
@Scope("request")
@FacesValidator("passwordValidator")
public class PasswordValidator implements Validator {

    private Pattern pattern;

    private static final String PASSWORD_PATTERN="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{10,20})";

    /**
     * validation method, to verify the password strength and also, whether the 
     * confirmation field password is the same as the first one.
     * 
     * @param context
     * @param component
     * @param value
     * @throws ValidatorException 
     */
    @Override
    public void validate(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {

        String password = value.toString();

        UIInput uiInputConfirmPassword = (UIInput) component.getAttributes()
                .get("confirmPassword");
        String confirmPassword = uiInputConfirmPassword.getSubmittedValue()
                .toString();

        // Let required="true" do its job.
        if (password == null || password.isEmpty() || confirmPassword == null
                || confirmPassword.isEmpty()) {
            return;
        }
        
        pattern = Pattern.compile(PASSWORD_PATTERN);
        
        // Validate password with regular expression
        if (!pattern.matcher(password).matches()) {
            uiInputConfirmPassword.setValid(false);
            throw new ValidatorException(new FacesMessage("The password must be 10 chars"
                    + " long, contain small letters, big letters and at least one number"));
        }
        
        // Verify, that the confirmation password is the same as the first one
        if (!password.equals(confirmPassword)) {
            uiInputConfirmPassword.setValid(false);
            throw new ValidatorException(new FacesMessage("Password must match confirm password"));
        }
    }
}
