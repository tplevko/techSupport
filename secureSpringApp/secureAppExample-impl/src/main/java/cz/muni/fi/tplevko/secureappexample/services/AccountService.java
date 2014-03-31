package cz.muni.fi.tplevko.secureappexample.services;

import cz.muni.fi.tplevko.secureappexample.entity.Account;
import cz.muni.fi.tplevko.secureappexample.entity.dto.AccountDTO;
import java.util.List;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author tplevko
 */
public interface AccountService {

    public void createAccount(Account account);

    public Account updateAccount(Account account);

    public Account deleteAccount(Account account);

    public Account findAccount(Long id);

    public Account findAccountByName(String name);

    public Account findAccountByEmail(String email);

    public List<Account> getAllAccounts();
}
