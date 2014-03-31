package cz.muni.fi.tplevko.secureappexample.dao;

import cz.muni.fi.tplevko.secureappexample.entity.Account;
import java.util.List;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author tplevko
 */
public interface AccountDao {

    public void createAccount(Account account);
    
    public void updateAccount(Account account);

    public void deleteAccount(Account account);

    public Account findAccountById(Long id);

    public Account findAccountByName(String name);

    public Account findAccountByEmail(String email);

    public List<Account> getAllAccounts();
}
