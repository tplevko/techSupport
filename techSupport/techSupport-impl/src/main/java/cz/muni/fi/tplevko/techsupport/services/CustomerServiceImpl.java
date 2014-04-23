package cz.muni.fi.tplevko.techsupport.services;

import cz.muni.fi.tplevko.techsupport.dao.CustomerDao;
import cz.muni.fi.tplevko.techsupport.entity.Customer;
import cz.muni.fi.tplevko.techsupport.entity.dto.CustomerDto;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.transaction.annotation.Transactional;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tplevko
 */
@Service(value = "CustomerServiceImpl")
@Transactional
public class CustomerServiceImpl implements CustomerService {

    // Use logger
    private static final Logger log = Logger.getLogger(CustomerServiceImpl.class.getName());

    @Autowired
//    @Qualifier(value = "customerDao")
    private CustomerDao customerDao;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    @Transactional
    public void createCustomer(CustomerDto customerDto) {

        if (customerDto == null) {
            throw new IllegalArgumentException("Customer to be created is null");
        }

        if (customerDto.getId() != null) {
            throw new IllegalArgumentException("Customer has have id set");
        }

        Customer customer = mapper.map(customerDto, Customer.class);

        customerDao.createCustomer(customer);
    }

    @Override
    public void updateCustomer(CustomerDto customer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteCustomer(CustomerDto customer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CustomerDto findCustomerById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CustomerDto findCustomerByEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDto> getAllCustomers() {

        List<Customer> customers = customerDao.getAllCustomers();

        List<CustomerDto> customersReturn = new ArrayList<>();
        for (Customer c : customers) {
            customersReturn.add(mapper.map(c, CustomerDto.class));
        }
        return customersReturn;
    }

    @Override
    public void activateCustomerAccount(CustomerDto customer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
//    
//    
//        @Override
//    @Transactional
//    public void updateAccount(AccountDto accountDto) {
//
//        if (accountDto == null) {
//            throw new IllegalArgumentException("Account to be created is null");
//        }
//
//        if (accountDto.getId() == null) {
//            throw new IllegalArgumentException("Account has not set id");
//        }
//
//        Account account = mapper.map(accountDto, Account.class);
//        accountDao.updateAccount(account);
//    }
//
//    @Override
//    @Transactional
////    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public void deleteAccount(AccountDto accountDto) {
//
//        if (accountDto == null) {
//            throw new IllegalArgumentException("Account to be created is null");
//        }
//
//        if (accountDto.getId() == null) {
//            throw new IllegalArgumentException("AccountDTO has set id");
//        }
//
//        Account account = mapper.map(accountDto, Account.class);
//        accountDao.deleteAccount(account);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public AccountDto findAccount(Long id) {
//
//        if (id == null) {
//            throw new IllegalArgumentException("ID to retrieve can't be null");
//        }
//        Account account = accountDao.findAccountById(id);
////        Account result = accountEntityToDTO(account);
//
//        return mapper.map(account, AccountDto.class);
//
//    }
//
//
//    @Override
//    @Transactional(readOnly = true)
//    public AccountDto findAccountByEmail(String email) {
//
//        if (email == null) {
//            throw new IllegalArgumentException("email can't be nulll");
//        }
//
//        Account account = accountDao.findAccountByEmail(email);
//        
//        return mapper.map(account, AccountDto.class);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public AccountDto findAccountByName(String name) {
//
//        if (name == null) {
//            throw new IllegalArgumentException("name can't be nulll");
//        }
//
//        Account account = accountDao.findAccountByName(name);
//
//        return mapper.map(account, AccountDto.class);
//    }
//
//    @Override
//    @Transactional
//    public void activateAccount(AccountDto accountDto) {
//
//        Account account = mapper.map(accountDto, Account.class);
//        
//        accountDao.confirmRegistration(account);
//    }

}
