package cz.muni.fi.tplevko.techsupport.managedbeans.security.confirm;

import cz.muni.fi.tplevko.techsupport.entity.dto.CustomerDto;
import cz.muni.fi.tplevko.techsupport.services.CustomerService;
import cz.muni.fi.tplevko.techsupport.utils.ShaEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author tplevko
 */
@Component("emailConfirmation")
public class EmailConfirmation {

    @Autowired
    private CustomerService customerService;
   
    private boolean status = false;

    public boolean verifyAccount(String emailAddress, String randString) {

        // asi try / catch blok, pre pripad, ze ten email neni platny...
        CustomerDto accountToVerify;
        accountToVerify = customerService.findCustomerByEmail(emailAddress);

        String stringToCompare = ShaEncoder.sha256hash(accountToVerify.getEmail(), accountToVerify.getSalt());

        if (randString.contentEquals(stringToCompare)) {

            accountToVerify.setActive(true);
            customerService.activateCustomerAccount(accountToVerify);

            status = true;
        }

        return status;
    }

    public String returnString(String email) {

        CustomerDto accountToVerify;
        accountToVerify = customerService.findCustomerByEmail(email);

        return "hey man, tou wrote : " + accountToVerify.getEmail();
    }
}
