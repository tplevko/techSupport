package cz.muni.fi.tplevko.secureappexample.services;

import cz.muni.fi.tplevko.secureappexample.dao.AccountDao;
import cz.muni.fi.tplevko.secureappexample.entity.Account;
import cz.muni.fi.tplevko.secureappexample.entity.dto.AccountDto;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.mail.internet.InternetAddress;
import org.dozer.DozerBeanMapper;
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

    @Autowired
    private DozerBeanMapper mapper;

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    
    @Override
    @Transactional
    public void createAccount(AccountDto accountDto) {

        if (accountDto == null) {
            throw new IllegalArgumentException("Account to be created is null");
        }

        if (accountDto.getId() != null) {
            throw new IllegalArgumentException("AccountDto has set id");
        }

//        validateAccount(accountDTO);
//        validateDuplicity(accountDTO);
//        Account account = accountDTOToEntity(accountDTO);
        Account account = mapper.map(accountDto, Account.class);

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
    public void updateAccount(AccountDto accountDto) {

        if (accountDto == null) {
            throw new IllegalArgumentException("Account to be created is null");
        }

        if (accountDto.getId() == null) {
            throw new IllegalArgumentException("Account has not set id");
        }

        Account account = mapper.map(accountDto, Account.class);
        accountDao.updateAccount(account);
    }

    @Override
    @Transactional
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteAccount(AccountDto accountDto) {

        if (accountDto == null) {
            throw new IllegalArgumentException("Account to be created is null");
        }

        if (accountDto.getId() == null) {
            throw new IllegalArgumentException("AccountDTO has set id");
        }

        Account account = mapper.map(accountDto, Account.class);
        accountDao.deleteAccount(account);
    }

    @Override
    @Transactional(readOnly = true)
    public AccountDto findAccount(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("ID to retrieve can't be null");
        }
        Account account = accountDao.findAccountById(id);
//        Account result = accountEntityToDTO(account);

        return mapper.map(account, AccountDto.class);

    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountDto> getAllAccounts() {

//        List<Account> result = new ArrayList<>();
        List<Account> accounts = accountDao.getAllAccounts();

        List<AccountDto> accountsReturn = new ArrayList<>();
        for (Account a : accounts) {
            accountsReturn.add(mapper.map(a, AccountDto.class));
        }
        return accountsReturn;
    }

    @Override
    @Transactional(readOnly = true)
    public AccountDto findAccountByEmail(String email) {

        if (email == null) {
            throw new IllegalArgumentException("email can't be nulll");
        }

        Account account = accountDao.findAccountByEmail(email);
        
        return mapper.map(account, AccountDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public AccountDto findAccountByName(String name) {

        if (name == null) {
            throw new IllegalArgumentException("name can't be nulll");
        }

        Account account = accountDao.findAccountByName(name);

        return mapper.map(account, AccountDto.class);
    }

    @Override
    @Transactional
    public void activateAccount(AccountDto accountDto) {

        Account account = mapper.map(accountDto, Account.class);
        
        accountDao.confirmRegistration(account);
    }
}
