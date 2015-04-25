package cz.muni.fi.tplevko.techsupport.managedbeans.converter;

import cz.muni.fi.tplevko.techsupport.entity.dto.AccountDto;
import cz.muni.fi.tplevko.techsupport.services.AccountService;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * converter used with request editing, when the account is assigned to
 * particullar problem.
 *
 * @author tplevko
 */
@Component(value = "accountNameConverter")
@Scope("request")
public class AccountNameConverter implements Converter {

    private static final Logger LOG = Logger.getLogger(AccountNameConverter.class.getName());

    @Autowired
    private AccountService accountService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if (value == null || value.equals("")) {
            return null;
        }

        AccountDto account = accountService.findAccountByEmail(value);
        return account;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (value == null) {
            return null;
        }

        AccountDto account = new AccountDto();
        account = (AccountDto) value;
        return account.getEmail();
    }
}
