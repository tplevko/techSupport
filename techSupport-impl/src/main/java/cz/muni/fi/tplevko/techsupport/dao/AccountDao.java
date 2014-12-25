package cz.muni.fi.tplevko.techsupport.dao;

import cz.muni.fi.tplevko.techsupport.entity.Account;
import java.util.List;

/**
 *
 * @author tplevko
 */
public interface AccountDao {

    public void createAccount(Account account);

    public void updateAccount(Account account);

    public void deleteAccount(Account account);

    public Account findAccountById(Long id);

    public Account findAccountByEmail(String email);

    public List<Account> getAllAccounts();
}

