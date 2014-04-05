package cz.muni.fi.tplevko.secureappexample.managedbeans.security;

import cz.muni.fi.tplevko.secureappexample.entity.Account;
import cz.muni.fi.tplevko.secureappexample.services.AccountService;
import cz.muni.fi.tplevko.secureappexample.utils.ShaEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author tplevko
 */
@Component("emailConfirmation")
public class EmailConfirmation {

    @Autowired
    private AccountService accountService;

    private boolean status = false;

    public boolean verifyAccount(String emailAddress, String randString) {

        // asi try / catch blok, pre pripad, ze ten email neni platny...
        Account accountToVerify;
        accountToVerify = accountService.findAccountByEmail(emailAddress);

        String stringToCompare = ShaEncoder.hash(accountToVerify.getEmail(), accountToVerify.getSalt());

        if (randString.contentEquals(stringToCompare)) {

            accountToVerify.setActive(true);
            accountService.activateAccount(accountToVerify);

            status = true;
        }

        return status;
    }

    public String returnString(String email) {

        Account accountToVerify;
        accountToVerify = accountService.findAccountByEmail(email);

        return "hey man, tou wrote : " + accountToVerify.getName();
    }
}
