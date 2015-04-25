package cz.muni.fi.tplevko.techsupport.managedbeans.converter;

import cz.muni.fi.tplevko.techsupport.entity.dto.AccountDto;
import cz.muni.fi.tplevko.techsupport.services.AccountService;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author tplevko
 */
@Component("accuntConverter")
public class AccountConverter implements Converter {

    private static final Logger LOG = Logger.getLogger(AccountConverter.class.getName());

    @Autowired(required = true)
    private AccountService accountService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
    
        if (value == null || value.equals("")) {
            return null;
        }
        Long id = Long.parseLong(value);

        
        LOG.info("***** converting the value : " + id + " *****");
                
        AccountDto account = accountService.findAccountById(id);
        
        return account;
    }

    String getStringKey(Long value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value);
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        LOG.info("***** converting the value : *****");

        if (value == null) {
            return null;
        }
        
        if (value instanceof AccountDto) {

            AccountDto account = (AccountDto) value;
                 
            LOG.info("***** accountempl : " + account.getEmail() + " *****");

            return getStringKey(account.getId());

        } else {
            throw new IllegalArgumentException("object " + value + " is of type " + value.getClass().getName() + "; expected type: " + AccountDto.class.getName());
        }
    }

}
