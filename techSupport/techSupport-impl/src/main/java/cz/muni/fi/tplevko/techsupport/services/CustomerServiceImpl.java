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
    @Transactional
    public void updateCustomer(CustomerDto customerDto) {

        if (customerDto == null) {
            throw new IllegalArgumentException("Customer to be created is null");
        }

        if (customerDto.getId() == null) {
            throw new IllegalArgumentException("Customer has not set id");
        }

        Customer customer = mapper.map(customerDto, Customer.class);
        customerDao.updateCustomer(customer);
    }

    @Override
    @Transactional
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteCustomer(CustomerDto customerDto) {

        if (customerDto == null) {
            throw new IllegalArgumentException("Customer to be created is null");
        }

        if (customerDto.getId() == null) {
            throw new IllegalArgumentException("CustomerDto has not set id");
        }

        Customer customer = mapper.map(customerDto, Customer.class);
        customerDao.deleteCustomer(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDto findCustomerById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("ID to retrieve can't be null");
        }
        Customer customer = customerDao.findCustomerById(id);

        return mapper.map(customer, CustomerDto.class);

    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDto findCustomerByEmail(String email) {

        if (email == null) {
            throw new IllegalArgumentException("email can't be nulll");
        }
        
        CustomerDto customerDto = null;
        Customer customer = customerDao.findCustomerByEmail(email);

        if (customer != null) {
            customerDto = mapper.map(customer, CustomerDto.class);
        }
        return customerDto;
    }

    @Override
    @Transactional(readOnly = true)
    //    @PreAuthorize("hasRole('ROLE_TECHNICIAN')")
    public List<CustomerDto> getAllCustomers() {

        List<Customer> customers = customerDao.getAllCustomers();

        List<CustomerDto> customersReturn = new ArrayList<>();
        for (Customer c : customers) {
            customersReturn.add(mapper.map(c, CustomerDto.class));
        }
        return customersReturn;
    }

    @Override
    @Transactional
    //    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void activateCustomerAccount(CustomerDto customerDto) {
        Customer customer = mapper.map(customerDto, Customer.class);

        customer.setActive(true);

        customerDao.updateCustomer(customer);
    }
}
