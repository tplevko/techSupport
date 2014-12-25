package cz.muni.fi.tplevko.techsupport.services;

import cz.muni.fi.tplevko.techsupport.dao.AccountDao;
import cz.muni.fi.tplevko.techsupport.entity.Account;
import cz.muni.fi.tplevko.techsupport.entity.dto.AccountDto;
import java.util.List;
import java.util.logging.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tplevko
 */
@Service(value = "AccountServiceImpl")
@Transactional
public class AccountServiceImpl implements AccountService {

    private static final Logger log = Logger.getLogger(CustomerServiceImpl.class.getName());

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    @Transactional
    public void createAccount(AccountDto accountDto) {

        if (accountDto == null) {
            throw new IllegalArgumentException("Account to be created is null");
        }

        if (accountDto.getId() != null) {
            throw new IllegalArgumentException("Account has have id set");
        }

        Account account = mapper.map(accountDto, Account.class);

        accountDao.createAccount(account);
    }

    @Override
    public void updateAccount(AccountDto accountDto) {

        if (accountDto == null) {
            throw new IllegalArgumentException("Account to be created is null");
        }

        if (accountDto.getId() == null) {
            throw new IllegalArgumentException("Account has not set id");

        }

        Account account = mapper.map(accountDto, Account.class
        );
        accountDao.updateAccount(account);
    }

    @Override
    public void deleteAccount(AccountDto account) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AccountDto findAccountById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("ID to retrieve can't be null");
        }
        Account account = accountDao.findAccountById(id);

        return mapper.map(account, AccountDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public AccountDto findAccountByEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("email can't be nulll");
        }

        AccountDto accountDto = null;
        Account account = accountDao.findAccountByEmail(email);

        if (account != null) {
            accountDto = mapper.map(account, AccountDto.class);
        }
        return accountDto;
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void activateCustomerAccount(AccountDto account) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//
//    @Override
//    @Transactional(readOnly = true)
//    //    @PreAuthorize("hasRole('ROLE_TECHNICIAN')")
//    public List<CustomerDto> getAllCustomers() {
//
//        List<Customer> customers = customerDao.getAllCustomers();
//
//        List<CustomerDto> customersReturn = new ArrayList<>();
//        for (Customer c : customers) {
//            customersReturn.add(mapper.map(c, CustomerDto.class));
//        }
//        return customersReturn;
//    }
//
//    @Override
//    @Transactional
//    //    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public void activateCustomerAccount(CustomerDto customerDto) {
//        Customer customer = mapper.map(customerDto, Customer.class);
//
//        customer.setActive(true);
//
//        customerDao.updateCustomer(customer);
//    }
//}
}
