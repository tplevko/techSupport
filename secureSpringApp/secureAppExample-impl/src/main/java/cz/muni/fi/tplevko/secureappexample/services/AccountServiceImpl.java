package cz.muni.fi.tplevko.secureappexample.services;

import cz.muni.fi.tplevko.secureappexample.dao.AccountDao;
import cz.muni.fi.tplevko.secureappexample.entity.Account;
import java.util.List;
import java.util.logging.Logger;
import javax.mail.internet.InternetAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tplevko
 */
@Service(value = "accountServiceImpl")
@Transactional
public class AccountServiceImpl implements AccountService {

    // Use logger
    private static final Logger log = Logger.getLogger(AccountServiceImpl.class.getName());

    @Autowired
//    @Qualifier(value = "accountDao")
    private AccountDao accountDao;

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    @Transactional
    public void createAccount(Account account) {

        if (account == null) {
            throw new IllegalArgumentException("Account to be created is null");
        }

        if (account.getId() != null) {
            throw new IllegalArgumentException("AccountDTO has set id");
        }

//        validateAccount(accountDTO);
//        validateDuplicity(accountDTO);
//        Account account = accountDTOToEntity(accountDTO);
        accountDao.createAccount(account);

//        account = accountDAO.get(account.getId());
//        AccountDetailsAdapter details = new AccountDetailsAdapter(account);
//        String password = details.getPassword();
//        Object salt = saltSource.getSalt(details);
//        account.setPassword(passwordEncoder.encodePassword(password, salt));
//        accountDAO.update(account);
    }

    @Override
    @Transactional
    public void updateAccount(Account account) {

        if (account == null) {
            throw new IllegalArgumentException("Account to be created is null");
        }

        if (account.getId() == null) {
            throw new IllegalArgumentException("Account has not set id");
        }

        accountDao.updateAccount(account);
    }

    @Override
    @Transactional
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteAccount(Account account) {

        if (account == null) {
            throw new IllegalArgumentException("Account to be created is null");
        }

        if (account.getId() == null) {
            throw new IllegalArgumentException("AccountDTO has set id");
        }

        accountDao.deleteAccount(account);
    }

    @Override
    @Transactional(readOnly = true)
    public Account findAccount(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("ID to retrieve can't be null");
        }
        Account account = accountDao.findAccountById(id);
//        Account result = accountEntityToDTO(account);
        return account;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Account> getAllAccounts() {

//        List<Account> result = new ArrayList<>();
        List<Account> accounts = accountDao.getAllAccounts();
//        for (Account account : accounts) {
//            result.add(accountEntityToDTO(account));
//        }
        return accounts;
    }

    @Override
    @Transactional(readOnly = true)
    public Account findAccountByEmail(String email) {

        if (email == null) {
            throw new IllegalArgumentException("email can't be nulll");
        }

        Account account = accountDao.findAccountByEmail(email);

        return account;
    }

    @Override
    @Transactional(readOnly = true)
    public Account findAccountByName(String name) {

        if (name == null) {
            throw new IllegalArgumentException("name can't be nulll");
        }

        Account account = accountDao.findAccountByName(name);

        return account;
    }

    @Override
    @Transactional
    public void activateAccount(Account account) {

        accountDao.confirmRegistration(account);
    }
}
