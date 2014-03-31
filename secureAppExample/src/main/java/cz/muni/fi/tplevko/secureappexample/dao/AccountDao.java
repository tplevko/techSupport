package cz.muni.fi.tplevko.secureappexample.dao;

import cz.muni.fi.tplevko.secureappexample.entity.Account;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author tplevko
 */
public interface AccountDao {

    public Account createAccount(Account account);

    public void deleteAccount(Account account);

    public Account updateAccount(Account account);

    public Account findAccountAccrodToId(Long id);

    public Account findAccountAccordToName(String name);

    public Account findAccountAccordToEmail(InternetAddress email);

}
